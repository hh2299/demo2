package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.HrDTO;
import com.example.demo.domain.dto.PositionDTO;
import com.example.demo.domain.dto.RecruitDTO;
import com.example.demo.domain.param.HrSearchParam;
import com.example.demo.domain.param.PositionSearchParam;
import com.example.demo.orm.entity.Position;
import com.example.demo.service.RemotePositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/position")
@Api("岗位接口")
public class PositionController {

    @Resource
    RemotePositionService positionService;

    @ApiOperation("职业列表")
    @PostMapping("/list")
    public BaseModel<List<PositionDTO>> getPositionList(@RequestBody PositionSearchParam param) {
        List<PositionDTO> data = positionService.getPositionList(param);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("保存职位")
    @PostMapping("/save")
    public BaseModel<Long> savePosition(@RequestBody PositionDTO position) {
        Long id = positionService.save(position);
        return BaseModel.buildSuccess(id);
    }
}
