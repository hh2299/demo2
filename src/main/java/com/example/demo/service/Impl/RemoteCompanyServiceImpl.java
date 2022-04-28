package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.mp.page.annotation.EnablePage;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.common.util.StringUtil;
import com.example.demo.domain.dto.ApplicantDTO;
import com.example.demo.domain.dto.CompanyDTO;
import com.example.demo.domain.param.CompanySearchParam;
import com.example.demo.orm.entity.*;
import com.example.demo.orm.mapper.*;
import com.example.demo.orm.service.impl.ApplicantServiceImpl;
import com.example.demo.service.RemoteApplicantService;
import com.example.demo.service.RemoteCompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemoteCompanyServiceImpl extends BaseService implements RemoteCompanyService {

    @Resource
    CompanyMapper companyMapper;
    @Resource
    HrMapper hrMapper;
    @Resource
    RecruitMapper recruitMapper;
    @Resource
    HireMapper hireMapper;
    @Resource
    PositionMapper positionMapper;


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
        wrapper.orderByDesc(Company::getUpdateTime);
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
        }
        return id;
    }

    @Override
    public Boolean delete(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }
        //TODO 删除其下的相关表内容
        super.deleteRelationList(hrMapper, Hr::getCompanyId, id);
        super.deleteRelationList(hireMapper, Hire::getCompanyId, id);
        super.deleteRelationList(positionMapper, Position::getCompanyId, id);
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
        Company company = companyMapper.selectById(id);
        if (company == null) {
            throw new MyException("不存在该公司");
        }
        return ConverterUtils.convert(company, CompanyDTO.class);
    }


}
