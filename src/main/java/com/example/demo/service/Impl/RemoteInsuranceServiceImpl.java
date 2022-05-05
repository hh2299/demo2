package com.example.demo.service.Impl;

import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.dao.SalaryMapping;
import com.example.demo.domain.dto.InsuranceDTO;
import com.example.demo.domain.param.InsuranceSearchParam;
import com.example.demo.orm.mapper.InsuranceMapper;
import com.example.demo.service.RemoteInsuranceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemoteInsuranceServiceImpl extends BaseService implements RemoteInsuranceService {

    @Resource
    SalaryMapping salaryMapping;


    @Override
    public List<InsuranceDTO> getList(InsuranceSearchParam param) {
        if (param == null) {
            throw new MyException("请完善查询参数");
        }

        return salaryMapping.getInsuranceList(param);
    }
}
