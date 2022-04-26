package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.CompanyDTO;
import com.example.demo.domain.param.CompanySearchParam;
import com.example.demo.service.RemoteCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/company")
@Api("公司接口")
public class CompanyController {

    @Resource
    RemoteCompanyService companyService;

    @ApiOperation("公司列表")
    @PostMapping("/list")
    public BaseModel<List<CompanyDTO>> getCompanyList(@RequestBody CompanySearchParam param) {
        List<CompanyDTO> data = companyService.getCompanyList(param);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("保存公司")
    @PostMapping("/save")
    public BaseModel<Long> saveCompany(@RequestBody CompanyDTO company) {
        Long id = companyService.save(company);
        return BaseModel.buildSuccess(id);
    }

    @ApiOperation("删除公司")
    @GetMapping("delete")
    public BaseModel<CompanyDTO> delete(@RequestParam Long id) {
        Boolean data = companyService.delete(id);
        return data ? BaseModel.buildSuccess() : BaseModel.buildError();
    }

    @ApiOperation("根据公司id获取指定公司信息")
    @GetMapping("getById")
    public BaseModel<CompanyDTO> getById(@RequestParam Long id) {
        CompanyDTO data = companyService.getCompanyById(id);
        return BaseModel.buildSuccess(data);
    }

}
