package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.CompanyDTO;
import com.example.demo.domain.dto.SalaryDTO;
import com.example.demo.domain.dto.TaskDTO;
import com.example.demo.domain.param.SalarySearchParam;
import com.example.demo.domain.param.TaskSearchParam;
import com.example.demo.service.RemoteSalaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/salary")
@Api(tags="薪水接口")
public class SalaryController {

    @Resource
    RemoteSalaryService salaryService;

    @ApiOperation("薪资列表")
    @PostMapping("/list")
    public BaseModel<List<SalaryDTO>> getList(@RequestBody SalarySearchParam param) {
        List<SalaryDTO> data = salaryService.getList(param);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public BaseModel<Long> save(@RequestBody SalaryDTO salaryDTO) {
        Long id = salaryService.save(salaryDTO);
        return BaseModel.buildSuccess(id);
    }

    @ApiOperation("生成默认Salary")
    @PostMapping("/addDefaultSalary")
    public BaseModel<Long> addDefaultSalary(@RequestBody SalarySearchParam param) {
        Long count = salaryService.addDefaultSalary(param);
        return BaseModel.buildSuccess(count);
    }
}
