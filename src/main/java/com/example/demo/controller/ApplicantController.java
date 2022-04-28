package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.ApplicantDTO;
import com.example.demo.domain.dto.CompanyDTO;
import com.example.demo.domain.param.ApplicantSearchParam;
import com.example.demo.domain.param.CompanySearchParam;
import com.example.demo.service.RemoteApplicantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/applicant")
@Api("应聘人接口")
public class ApplicantController {

    @Resource
    RemoteApplicantService applicantService;

    @ApiOperation("应聘人列表")
    @PostMapping("/list")
    public BaseModel<List<ApplicantDTO>> getApplicantList(@RequestBody ApplicantSearchParam param) {
        List<ApplicantDTO> data = applicantService.getList(param);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("根据应聘人id获取指定应聘人信息")
    @GetMapping("getById")
    public BaseModel<ApplicantDTO> getById(@RequestParam Long id) {
        ApplicantDTO data = applicantService.getById(id);
        return BaseModel.buildSuccess(data);
    }


}
