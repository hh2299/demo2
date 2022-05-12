package com.example.demo.service.Impl;

import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.dao.SalaryMapping;
import com.example.demo.domain.dto.InsuranceDTO;
import com.example.demo.domain.dto.SalaryDTO;
import com.example.demo.domain.param.InsuranceSearchParam;
import com.example.demo.orm.entity.Insurance;
import com.example.demo.orm.entity.Salary;
import com.example.demo.orm.mapper.InsuranceMapper;
import com.example.demo.service.RemoteInsuranceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemoteInsuranceServiceImpl extends BaseService implements RemoteInsuranceService {

    @Resource
    SalaryMapping salaryMapping;
    @Resource
    InsuranceMapper insuranceMapper;


    @Override
    public List<InsuranceDTO> getList(InsuranceSearchParam param) {
        if (param == null) {
            throw new MyException("请完善查询参数");
        }

        return salaryMapping.getInsuranceList(param);
    }

    @Override
    public Long save(InsuranceDTO insuranceDTO) {
        if (insuranceDTO == null) {
            throw new MyException("请完善薪水信息");
        }

        Long id = insuranceDTO.getId();
        Insurance insurance = ConverterUtils.convert(insuranceDTO, Insurance.class);
        if (id == null) {
            insuranceMapper.insert(insurance);
            id = insurance.getId();
        } else {
            insuranceMapper.updateById(insurance);
        }
        return id;
    }

}
