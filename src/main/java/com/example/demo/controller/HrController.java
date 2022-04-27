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
import org.springframework.web.bind.annotation.*;

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


    @ApiOperation("保存Hr")
    @PostMapping("/save")
    public BaseModel<Long> saveHr(@RequestBody HrDTO company) {
        Long id = hrService.save(company);
        return BaseModel.buildSuccess(id);
    }

    @ApiOperation("删除Hr")
    @GetMapping("delete")
    public BaseModel<HrDTO> delete(@RequestParam Long id) {
        Boolean data = hrService.delete(id);
        return data ? BaseModel.buildSuccess() : BaseModel.buildError();
    }

    @ApiOperation("根据Hr的id获取指定Hr信息")
    @GetMapping("getById")
    public BaseModel<HrDTO> getById(@RequestParam Long id) {
        HrDTO data = hrService.getHrById(id);
        return BaseModel.buildSuccess(data);
    }

}
