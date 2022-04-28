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
import com.example.demo.service.RemoteApplicantService;
import com.example.demo.service.RemoteCompanyService;
import com.example.demo.service.RemoteRecruitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    RemoteCompanyService companyService;
    @Resource
    RemoteRecruitService recruitService;


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
        recruit.setNum(--num);
        recruitMapper.updateById(recruit);

        // 更新应聘人信息
        ApplicantDTO applicant = getById(hireDTO.getApplicantId());
        if (applicant == null) {
            throw new MyException("应聘人信息有误");
        }
        applicant.setIsHired(1);
        Long id = save(applicant);
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
        hireMapper.insert(hire);




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
        applicantMapper.updateById(applicant);
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
