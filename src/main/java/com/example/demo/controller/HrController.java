package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.CompanyDTO;
import com.example.demo.domain.dto.HrDTO;
import com.example.demo.domain.param.CompanySearchParam;
import com.example.demo.domain.param.HrSearchParam;
import com.example.demo.service.RemoteCompanyService;
import com.example.demo.service.RemoteHrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/hr")
@Api("Hr接口")
public class HrController {

    @Resource
    RemoteHrService hrService;


    @ApiOperation("Hr列表")
    @PostMapping("/list")
    public BaseModel<List<HrDTO>> getHrList(@RequestBody HrSearchParam param) {
        List<HrDTO> data = hrService.getHrList(param);
        return BaseModel.buildSuccess(data);
    }

}
