package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.LeaveDTO;
import com.example.demo.domain.dto.LeaveStaticDTO;
import com.example.demo.domain.param.LeaveSearchParam;
import com.example.demo.domain.vo.LeaveVo;
import com.example.demo.service.RemoteLeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/leave")
@Api(tags="请假接口")
public class LeaveController {

    @Resource
    RemoteLeaveService leaveService;

    @ApiOperation("请假列表")
    @PostMapping("/list")
    public BaseModel<List<LeaveVo>> getList(@RequestBody LeaveSearchParam param) {
        List<LeaveVo> data = leaveService.getList(param);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("保存请假")
    @PostMapping("/save")
    public BaseModel<Long> save(@RequestBody LeaveDTO leaveDTO) {
        Long id = leaveService.save(leaveDTO);
        return BaseModel.buildSuccess(id);
    }

    @ApiOperation("请假统计")
    @PostMapping("/statistic")
    public BaseModel<List<LeaveStaticDTO>> statistic(@RequestBody LeaveSearchParam param) {
        List<LeaveStaticDTO> data = leaveService.statistic(param);
        return BaseModel.buildSuccess(data);
    }

}
