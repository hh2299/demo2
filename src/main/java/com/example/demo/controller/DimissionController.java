package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.DimissionDTO;
import com.example.demo.domain.dto.PositionDTO;
import com.example.demo.domain.param.DimissionSearchParam;
import com.example.demo.domain.param.PositionSearchParam;
import com.example.demo.domain.vo.DimissionVo;
import com.example.demo.service.Impl.RemoteDimissionServiceImpl;
import com.example.demo.service.RemoteDimissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dimission")
@Api("离职接口")
public class DimissionController {

    @Resource
    RemoteDimissionService dimissionService;

    @ApiOperation("离职申请列表")
    @PostMapping("/list")
    public BaseModel<List<DimissionVo>> getList(@RequestBody DimissionSearchParam param) {
        List<DimissionVo> data = dimissionService.getList(param);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("保存离职申请")
    @PostMapping("/save")
    public BaseModel<Long> save(@RequestBody DimissionDTO dimissionDTO) {
        Long id = dimissionService.save(dimissionDTO);
        return BaseModel.buildSuccess(id);
    }
}
