package com.example.demo.service;

import com.example.demo.domain.dto.SalaryDTO;
import com.example.demo.domain.param.SalarySearchParam;

import java.util.List;

public interface RemoteSalaryService {

    List<SalaryDTO> getList(SalarySearchParam param);


    Long save(SalaryDTO salaryDTO);

    Long addDefaultSalary(SalarySearchParam param);
}
