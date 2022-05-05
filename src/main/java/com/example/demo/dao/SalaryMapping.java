package com.example.demo.dao;

import com.example.demo.domain.dto.BonusDTO;
import com.example.demo.domain.dto.InsuranceDTO;
import com.example.demo.domain.dto.SalaryDTO;
import com.example.demo.domain.param.BonusSearchParam;
import com.example.demo.domain.param.InsuranceSearchParam;
import com.example.demo.domain.param.SalarySearchParam;
import com.example.demo.orm.entity.Salary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalaryMapping {

    List<SalaryDTO> getSalaryList(@Param("param") SalarySearchParam param);

    List<BonusDTO> getBonusList(@Param("param") BonusSearchParam param);

    List<InsuranceDTO> getInsuranceList(@Param("param") InsuranceSearchParam param);

}
