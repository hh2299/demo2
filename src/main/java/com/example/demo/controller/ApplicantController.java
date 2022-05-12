package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.*;
import com.example.demo.domain.param.ApplicantSearchParam;
import com.example.demo.domain.param.CompanySearchParam;
import com.example.demo.domain.param.PositionSearchParam;
import com.example.demo.service.RemoteApplicantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/applicant")
@Api(tags="应聘人接口")
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
    @GetMapping("/getById")
    public BaseModel<ApplicantDTO> getById(@RequestParam Long id) {
        ApplicantDTO data = applicantService.getById(id);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("保存应聘人")
    @PostMapping("/save")
    public BaseModel<Long> save(@RequestBody ApplicantDTO applicantDTO) {
        Long id = applicantService.save(applicantDTO);
        return BaseModel.buildSuccess(id);
    }

    @ApiOperation("签约")
    @PostMapping("/hire")
    public BaseModel<Boolean> hire(@RequestBody HireDTO hireDTO) {
        Boolean data = applicantService.hire(hireDTO);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("解约")
    @GetMapping("/termination")
    public BaseModel<Boolean> termination(@RequestParam Long id) {
        Boolean data = applicantService.termination(id);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("删除应聘人")
    @GetMapping("/delete")
    public BaseModel<Boolean> delete(@RequestParam Long id) {
        Boolean data = applicantService.delete(id);
        return data ? BaseModel.buildSuccess() : BaseModel.buildError();
    }


    @ApiOperation("获取应聘人履历")
    @GetMapping("/getCvList")
    public BaseModel<List<ApplicantCvDTO>> getCvList(@RequestParam Long id) {
        List<ApplicantCvDTO> data = applicantService.getCvList(id);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("修改职业")
    @PostMapping("/changePosition")
    public BaseModel<Boolean> changePosition(@RequestBody ChangePositionDTO changePositionDTO) {
       Boolean data = applicantService.changePosition(changePositionDTO);
        return BaseModel.buildSuccess(data);
    }
}
