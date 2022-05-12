package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.mp.page.annotation.EnablePage;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.dao.ApplicantMapping;
import com.example.demo.domain.dto.*;
import com.example.demo.domain.param.ApplicantSearchParam;
import com.example.demo.orm.entity.*;
import com.example.demo.orm.mapper.*;
import com.example.demo.orm.service.impl.CompanyServiceImpl;
import com.example.demo.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RemoteApplicantServiceImpl extends BaseService implements RemoteApplicantService {

    @Resource
    ApplicantMapper applicantMapper;
    @Resource
    ApplicantMapping applicantMapping;
    @Resource
    ApplicantCvMapper applicantCvMapper;
    @Resource
    RecruitMapper recruitMapper;
    @Resource
    HireMapper hireMapper;
    @Resource
    ApplicantWeightMapper applicantWeightMapper;
    @Resource
    RemoteCompanyService companyService;
    @Resource
    RemoteRecruitService recruitService;
    @Resource
    RemoteSalaryService salaryService;
    @Resource
    RemotePerformWeightService performWeightService;
    @Resource
    RemoteInsuranceService insuranceService;



    @Override
    @EnablePage
    public List<ApplicantDTO> getList(ApplicantSearchParam param) {
        if (param == null) {
            throw new MyException("没有查询参数");
        }
        List<ApplicantDTO> applicantDTOS = applicantMapping.getList(param);
        return applicantDTOS;
    }

    @Override
    public ApplicantDTO getById(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }
        Applicant applicant = applicantMapper.selectById(id);
        ApplicantDTO applicantDTO = ConverterUtils.convert(applicant, ApplicantDTO.class);
        List<ApplicantCv> applicantCvs = new ArrayList<>();
        if (applicant != null) {
            applicantCvs  = super.getRelationList(applicantCvMapper, ApplicantCv::getApplicantId, applicant.getId());
        }
        if (!CollectionUtils.isEmpty(applicantCvs)) {
            applicantDTO.setApplicantCvList(ConverterUtils.convertList(applicantCvs, ApplicantCvDTO.class));
        }
        return applicantDTO;
    }


    @Override
    public Long save(ApplicantDTO applicantDTO) {
        if (applicantDTO == null) {
            throw new MyException("请完善应聘人信息");
        }

        Long id = applicantDTO.getId();
        Applicant applicant = ConverterUtils.convert(applicantDTO, Applicant.class);

        if (id == null) {
            applicantMapper.insert(applicant);
            id = applicant.getId();
        } else {
            applicantMapper.updateById(applicant);
        }

        return id;
    }

    @Override
    @Transactional
    public Boolean hire(HireDTO hireDTO) {
        if (hireDTO == null || hireDTO.getApplicantId() == null || hireDTO.getRecruitId() == null) {
            throw new MyException("请完善信息");
        }

        // 更新招聘信息
        RecruitDTO recruitDTO = recruitService.getRecruitById(hireDTO.getRecruitId());
        Recruit recruit = ConverterUtils.convert(recruitDTO, Recruit.class);
        int num = recruit.getNum();
        if (num == 0) {
            throw new MyException("该岗位已招满");
        }
        if (num==1){
            recruit.setEndDate(new Date());
        }
        recruit.setNum(--num);
        recruitMapper.updateById(recruit);
        Long weightId = recruitDTO.getWeightId();

        // 更新应聘人信息
        ApplicantDTO applicant = getById(hireDTO.getApplicantId());
        if (applicant == null) {
            throw new MyException("应聘人信息有误");
        }
        applicant.setIsHired(1);
        applicant.setSalary(recruit.getSalary());
        Long id = save(applicant);
        //关联应聘人和权重
        ApplicantWeight applicantWeight = new ApplicantWeight();
        applicantWeight.setApplicantId(hireDTO.getApplicantId());
        applicantWeight.setWeightId(weightId);
        applicantWeightMapper.insert(applicantWeight);

        //添加履历
        ApplicantCv applicantCv = new ApplicantCv();
        Long applicantId = applicant.getId();
        Long companyId = recruit.getCompanyId();
        CompanyDTO company = companyService.getCompanyById(companyId);
        String companyName = company.getName();
        Long positionId = recruit.getPositionId();
        String positionName = recruit.getPositionName();
        Date startDate = new Date();
        applicantCv = setApplicantCvInfo(applicantCv, applicantId, companyId, companyName, positionId, positionName, startDate);
        applicantCvMapper.insert(applicantCv);

        //添加雇佣信息
        Hire hire = new Hire();
        hire.setApplicantId(id);
        hire.setRecruitId(recruit.getId());
        hire.setCompanyId(recruit.getCompanyId());
        hire.setHireDate(new Date());
        hireMapper.insert(hire);

        //添加一条薪资 (妈见打代码)
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.DAY_OF_MONTH) < 15) {
            now.set(Calendar.DAY_OF_MONTH,15);
            Salary salary = new Salary();
            salary.setApplicantId(applicantId);
            salary.setApplicant(applicant.getName());
            salary.setPayableWage(recruit.getSalary());
            salary.setTax(recruit.getSalary().divide(new BigDecimal(10)));
            salary.setInsurancePay(new BigDecimal(200));
            salary.setRealWage(salary.getPayableWage().subtract(salary.getInsurancePay()).subtract(salary.getTax()));
            salary.setIsFinished(0);
            salary.setPayDate(now.getTime());
            salaryService.save(ConverterUtils.convert(salary, SalaryDTO.class));
        }
        //添加当月的保险
        now = Calendar.getInstance();
        if (now.get(Calendar.DAY_OF_MONTH)<19){
            Insurance insurance = new Insurance();
            insurance.setApplicant(applicant.getName());
            insurance.setApplicantId(applicantId);
            insurance.setMedical(new BigDecimal(200));
            insurance.setEndowment(new BigDecimal(200));
            insurance.setFertility(new BigDecimal(200));
            insurance.setHousingFund(new BigDecimal(200));
            insurance.setInductrialInjury(new BigDecimal(200));
            insurance.setUnemployment(new BigDecimal(200));
            now.set(Calendar.DAY_OF_MONTH, 18);
            insurance.setPayDate(now.getTime());
            insuranceService.save(ConverterUtils.convert(insurance, InsuranceDTO.class));
        }

        return hire.getId() != null;
    }

    @Override
    public Boolean delete(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }
        int count = applicantMapper.deleteById(id);
        return count == 1;
    }

    @Override
    public List<ApplicantCvDTO> getCvList(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }
        List<ApplicantCv> applicantCvs = super.getRelationList(applicantCvMapper, ApplicantCv::getApplicantId, id);

        return ConverterUtils.convertList(applicantCvs, ApplicantCvDTO.class);
    }

    @Override
    public Boolean termination(Long id) {
        if (id == null) {
            throw new MyException("请完善辞职人信息");
        }
        ApplicantDTO applicantDTO = getById(id);
        if (applicantDTO.getIsHired() == 0) {
            throw new MyException("没有雇佣关系");
        }
        //删除Hire信息
        super.deleteRelationList(hireMapper, Hire::getApplicantId, applicantDTO.getId());
        //更新履历信息
        LambdaQueryWrapper<ApplicantCv> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ApplicantCv::getApplicantId, applicantDTO.getId());
        wrapper.isNull(ApplicantCv::getEndDate);
        ApplicantCv applicantCv = new ApplicantCv();
        applicantCv.setEndDate(new Date());
        applicantCvMapper.update(applicantCv, wrapper);
        //更新应聘人信息
        Applicant applicant = ConverterUtils.convert(applicantDTO, Applicant.class);
        applicant.setIsHired(0);
        applicant.setSalary(BigDecimal.ZERO);
        applicantMapper.updateById(applicant);
        return true;
    }


    @Override
    public Boolean changePosition(ChangePositionDTO changePositionDTO) {
        if (changePositionDTO == null || changePositionDTO.getApplicantId() == null
                || changePositionDTO.getNewPositionId() == null || changePositionDTO.getOldPositionId() == null
                || changePositionDTO.getPerformWeight() == null) {
            throw new MyException("请完善转职信息");
        }

        if (changePositionDTO.getPerformWeight().getWeightSum() != 100) {
            throw new MyException("权重和不等于100");
        }

        Long applicantId = changePositionDTO.getApplicantId();
        //新增perform_weight
        Long weight_id = performWeightService.save(changePositionDTO.getPerformWeight());

        //更新applicant_weight
        ApplicantWeight applicantWeight = new ApplicantWeight();
        applicantWeight.setWeightId(weight_id);
        LambdaQueryWrapper<ApplicantWeight> weightLambdaQueryWrapper = new LambdaQueryWrapper<>();
        weightLambdaQueryWrapper.eq(ApplicantWeight::getApplicantId, applicantId);
        applicantWeightMapper.update(applicantWeight, weightLambdaQueryWrapper);

        //更新applicant
        Applicant applicant = new Applicant();
        applicant.setIntentionPosition(changePositionDTO.getNewPositionName());
        applicant.setIntentionPositionId(changePositionDTO.getNewPositionId());
        LambdaQueryWrapper<Applicant> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Applicant::getId, changePositionDTO.getApplicantId());
        applicantMapper.update(applicant, wrapper);

        return true;
    }

    private ApplicantCv setApplicantCvInfo(ApplicantCv applicantCv, Long applicantId, Long companyId, String companyName, Long positionId, String positionName, Date startDate) {
        applicantCv.setApplicantId(applicantId);
        applicantCv.setCompanyId(companyId);
        applicantCv.setCompany(companyName);
        applicantCv.setPosition(positionName);
        applicantCv.setPositionId(positionId);
        applicantCv.setStartDate(startDate);
        return applicantCv;
    }


}
