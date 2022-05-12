package com.example.demo.service;

import com.example.demo.domain.dto.InsuranceDTO;
import com.example.demo.domain.param.InsuranceSearchParam;
import com.example.demo.orm.entity.Insurance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RemoteInsuranceService {

    List<InsuranceDTO> getList(@Param("param") InsuranceSearchParam param);

    Long save(InsuranceDTO convert);
}
