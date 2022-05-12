package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.BonusDTO;
import com.example.demo.domain.dto.InsuranceDTO;
import com.example.demo.domain.param.BonusSearchParam;
import com.example.demo.domain.param.InsuranceSearchParam;
import com.example.demo.service.RemoteBonusService;
import com.example.demo.service.RemoteInsuranceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/insurance")
@Api(tags="保险接口")
public class InsuranceController {

    @Resource
    RemoteInsuranceService insuranceService;

    @ApiOperation("保险列表")
    @PostMapping("/list")
    public BaseModel<List<InsuranceDTO>> getList(@RequestBody InsuranceSearchParam param) {
        List<InsuranceDTO> data = insuranceService.getList(param);
        return BaseModel.buildSuccess(data);
    }
//
//    @ApiOperation("保存")
//    @PostMapping("/save")
//    public BaseModel<Long> save(@RequestBody SalaryDTO salaryDTO) {
//        Long id = salaryService.save(salaryDTO);
//        return BaseModel.buildSuccess(id);
//    }

}
