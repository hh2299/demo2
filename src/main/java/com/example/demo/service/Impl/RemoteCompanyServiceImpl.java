package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.mp.page.annotation.EnablePage;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.common.util.StringUtil;
import com.example.demo.domain.dto.*;
import com.example.demo.domain.param.CompanySearchParam;
import com.example.demo.domain.vo.CompanyCategoryStatisticVo;
import com.example.demo.domain.vo.CompanyCityStatisticVo;
import com.example.demo.domain.vo.CompanyStatisticVo;
import com.example.demo.orm.entity.*;
import com.example.demo.orm.mapper.*;
import com.example.demo.service.RemoteCompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RemoteCompanyServiceImpl extends BaseService implements RemoteCompanyService {

    @Resource
    CompanyMapper companyMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    RecruitMapper recruitMapper;
    @Resource
    HireMapper hireMapper;
    @Resource
    PositionMapper positionMapper;
    @Resource
    ApplicantMapper applicantMapper;



    @Override
    @EnablePage
    public List<CompanyDTO> getCompanyList(CompanySearchParam param) {
        if (param == null) {
            throw new MyException("没有查询参数");
        }

        List<CompanyDTO> companyDTOList ;

        LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<>();
        if (StringUtil.isNotNull(param.getName())) {
            wrapper.like(Company::getName, param.getName());
        }
        if (StringUtil.isNotNull(param.getIndustryCategory())) {
            wrapper.eq(Company::getIndustryCategory, param.getIndustryCategory());
        }
        if (param.getIsAgreed() != null) {
            wrapper.eq(Company::getIsAgreed, param.getIsAgreed());
        }
        wrapper.orderByAsc(Company::getIsAgreed).orderByDesc(Company::getUpdateTime);
        List<Company> companyList = companyMapper.selectList(wrapper);
        companyDTOList = ConverterUtils.convertList(companyList, CompanyDTO.class);
        return companyDTOList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(CompanyDTO companyDTO) {
        if (companyDTO == null) {
            throw new MyException("没有保存信息");
        }
        Long id = companyDTO.getId();
        Company company = ConverterUtils.convert(companyDTO, Company.class);
        if (id != null) {//更新
            companyMapper.updateById(company);
        } else {
            companyMapper.insert(company);
            id = company.getId();
            if (companyDTO.getUserId() != null) {
                User user = new User();
                user.setCompanyId(id);
                user.setCompanyName(company.getName());
                LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
                userLambdaQueryWrapper.eq(User::getId, companyDTO.getUserId());
                userMapper.update(user, userLambdaQueryWrapper);
            }
        }
        return id;
    }

    @Override
    public Boolean delete(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }
        //TODO 删除其下的相关表内容
        super.deleteRelationList(userMapper, User::getCompanyId, id);
        super.deleteRelationList(hireMapper, Hire::getCompanyId, id);
        // super.deleteRelationList(positionMapper, Position::getCompanyId, id);
        LambdaQueryWrapper<Recruit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Recruit::getCompanyId, id);
        Recruit recruit = new Recruit();
        recruit.setIsFinished(1);
        recruitMapper.update(recruit, wrapper);


        int count = companyMapper.deleteById(id);
        return count == 1;
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }
        if (id == 0L) {
            return null;
        }
        Company company = companyMapper.selectById(id);
        if (company == null) {
            throw new MyException("不存在该公司");
        }
        CompanyDTO companyDTO = ConverterUtils.convert(company, CompanyDTO.class);

        List<User> users = super.getRelationList(userMapper, User::getCompanyId, companyDTO.getId());
        if (users.size() > 0) {
            companyDTO.setUser(ConverterUtils.convert(users.get(0), UserDTO.class));
        }
        return companyDTO;
    }

    @Override
    public List<ApplicantDTO> getEmployeeList(Long id) {

        List<Hire> hires = super.getRelationList(hireMapper, Hire::getCompanyId, id);

        List<Long> applicantIds = hires.stream().map(e -> e.getApplicantId()).collect(Collectors.toList());

        if (applicantIds.size() == 0) {
            return new ArrayList<ApplicantDTO>();
        }
        List<Applicant> applicants = applicantMapper.selectBatchIds(applicantIds);

        return ConverterUtils.convertList(applicants, ApplicantDTO.class);
    }

    @Override
    public CompanyStatisticVo statistic() {

        CompanyStatisticVo companyStatistic= new CompanyStatisticVo();
        LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<>();
        wrapper.isNotNull(Company::getIndustryCategory);
        wrapper.eq(Company::getIsAgreed, 1);
        List<Company> companyList = companyMapper.selectList(wrapper);

        //根据行业分类
        Map<String,List<Company>> map = companyList.stream().collect(Collectors.groupingBy(Company::getIndustryCategory));

        List<CompanyCategoryStatisticVo> companyCategoryStatisticVo = new ArrayList<>();
        for (Map.Entry<String, List<Company>> entry : map.entrySet()) {
            String mapKey = entry.getKey();
            List<Company> mapValue = entry.getValue();
            CompanyCategoryStatisticVo companyCategoryStatistic = new CompanyCategoryStatisticVo();
            companyCategoryStatistic.setIndustryCategory(mapKey);
            companyCategoryStatistic.setNum(mapValue.size());
            companyCategoryStatisticVo.add(companyCategoryStatistic);
        }

        companyStatistic.setCompanyCategoryStatistic(companyCategoryStatisticVo);

        //根据入会时间
        List<Integer> months = companyList.stream().map(e->{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(e.getJoinDate());
            return calendar.get(Calendar.MONTH)+1;
        }).collect(Collectors.toList());
        int[] monthNum = new int[12];
        for (Integer month : months) {
            for (int i = 11; i >= month-1; i--) {
                monthNum[i]++;
            }
        }
        List<Integer> monthNumStatistic = Arrays.stream(monthNum).boxed().collect(Collectors.toList());
        companyStatistic.setMonthNum(monthNumStatistic);

        //根据地址
        Map<String, List<Company>> map2 = companyList.stream().collect(Collectors.groupingBy(Company::getCity));

        List<CompanyCityStatisticVo> companyCityStatisticDTOS = new ArrayList<>();
        for (Map.Entry<String, List<Company>> entry : map2.entrySet()) {
            String mapKey = entry.getKey();
            if ("市辖区".equals(mapKey)) {
                Map<String, List<Company>> map3 = entry.getValue().stream().collect(Collectors.groupingBy(Company::getProvince));
                for (Map.Entry<String, List<Company>> entry3 : map3.entrySet()) {
                    String mapKey3 = entry3.getKey();
                    List<Company> mapValue = entry3.getValue();
                    CompanyCityStatisticVo companyCityStatistic = new CompanyCityStatisticVo();
                    companyCityStatistic.setCity(mapKey3);
                    companyCityStatistic.setNum(mapValue.size());
                    companyCityStatisticDTOS.add(companyCityStatistic);
                }
            }else {
                List<Company> mapValue = entry.getValue();
                CompanyCityStatisticVo companyCityStatistic = new CompanyCityStatisticVo();
                companyCityStatistic.setCity(mapKey);
                companyCityStatistic.setNum(mapValue.size());
                companyCityStatisticDTOS.add(companyCityStatistic);
            }
        }

        companyStatistic.setCompanyCityStatistic(companyCityStatisticDTOS);

        return companyStatistic;
    }
}
