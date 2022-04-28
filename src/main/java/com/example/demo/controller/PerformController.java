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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}