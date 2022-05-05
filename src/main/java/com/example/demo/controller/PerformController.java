package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.CompanyDTO;
import com.example.demo.domain.dto.PerformanceDTO;
import com.example.demo.domain.param.CompanySearchParam;
import com.example.demo.domain.param.PerformanceSearchParam;
import com.example.demo.service.RemoteCompanyService;
import com.example.demo.service.RemotePerformanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/performance")
@Api("在职表现接口")
public class PerformController {
    @Resource
    RemotePerformanceService performanceService;

    @ApiOperation("表现列表")
    @PostMapping("/list")
    public BaseModel<List<PerformanceDTO>> getPerformerList(@RequestBody PerformanceSearchParam param) {
        List<PerformanceDTO> data = performanceService.getList(param);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("评价")
    @PostMapping("/eval")
    public BaseModel<Long> eval(@RequestBody PerformanceDTO performance) {
        Long data = performanceService.save(performance);
        return BaseModel.buildSuccess(data);
    }


    @ApiOperation("根据applicantId获取当月评价")
    @GetMapping("/getNewestPerform")
    public BaseModel<PerformanceDTO> getNewestPerform(@RequestParam Long id) {
        PerformanceDTO data = performanceService.getNewestPerform(id);
        return BaseModel.buildSuccess(data);
    }
}
