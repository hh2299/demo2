package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.mp.page.annotation.EnablePage;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.dao.SalaryMapping;
import com.example.demo.domain.dto.ApplicantDTO;
import com.example.demo.domain.dto.SalaryDTO;
import com.example.demo.domain.param.SalarySearchParam;
import com.example.demo.orm.entity.Salary;
import com.example.demo.orm.mapper.SalaryMapper;
import com.example.demo.service.RemoteCompanyService;
import com.example.demo.service.RemoteSalaryService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RemoteSalaryServiceImpl extends BaseService implements RemoteSalaryService {

    @Resource
    SalaryMapping salaryMapping;
    @Resource
    SalaryMapper salaryMapper;
    @Resource
    RemoteCompanyService companyService;


    @Override
    @EnablePage
    public List<SalaryDTO> getList(SalarySearchParam param) {

        if (param == null) {
            throw new MyException("请完善查询参数");
        }
        List<SalaryDTO> salaryDTOList = salaryMapping.getSalaryList(param);
//
        return salaryDTOList;
    }


    @Override
    public Long save(SalaryDTO salaryDTO) {
        if (salaryDTO == null) {
            throw new MyException("请完善薪水信息");
        }

        Long id = salaryDTO.getId();
        Salary salary = ConverterUtils.convert(salaryDTO, Salary.class);
        if (id == null) {
            salaryMapper.insert(salary);
            id = salary.getId();
        } else {
            salaryMapper.updateById(salary);
        }
        return id;
    }

    @Override
    public Long addDefaultSalary(SalarySearchParam param) {
        if (param == null) {
            throw new MyException("请完善参数");
        }
        List<ApplicantDTO> applicants = companyService.getEmployeeList(param.getCompanyId());
        if (CollectionUtils.isEmpty(applicants)) {
            throw new MyException("还没有员工");
        }
        for (ApplicantDTO a:applicants) {
            Salary salary = new Salary();
            salary.setPayableWage(a.getSalary());
            salary.setTax(a.getSalary().multiply(new BigDecimal(0.1)));
            salary.setInsurancePay(new BigDecimal(200));
            BigDecimal real = salary.getPayableWage().subtract(salary.getTax()).subtract(salary.getInsurancePay());
            salary.setRealWage(real);
            salary.setIsFinished(0);
            salary.setApplicantId(a.getId());
            salary.setApplicant(a.getName());
            salary.setPayDate(param.getSearchMonth());
            salaryMapper.insert(salary);
        }
        return Long.valueOf(applicants.size());

    }
}
