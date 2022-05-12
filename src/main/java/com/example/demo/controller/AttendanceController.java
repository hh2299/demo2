package com.example.demo.controller;

import com.example.demo.common.vo.BaseModel;
import com.example.demo.domain.dto.AttendanceDTO;
import com.example.demo.domain.dto.AttendanceStatisticDTO;
import com.example.demo.domain.param.AttendanceSearchParam;
import com.example.demo.domain.param.StatisticSearchParam;
import com.example.demo.domain.vo.AttendanceVo;
import com.example.demo.service.RemoteAttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/attendance")
@Api(tags="考勤接口")
public class AttendanceController {
    @Resource
    RemoteAttendanceService attendanceService;

    @ApiOperation("签到列表")
    @PostMapping("/list")
    public BaseModel<List<AttendanceVo>> getList(@RequestBody AttendanceSearchParam param) {
        List<AttendanceVo> data = attendanceService.getList(param);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("新增签到记录")
    @PostMapping("/save")
    public BaseModel<Long> getList(@RequestBody AttendanceDTO attendance) {
        Long data = attendanceService.save(attendance);
        return BaseModel.buildSuccess(data);
    }

    @ApiOperation("统计打卡信息")
    @PostMapping("/statistic")
    public BaseModel<List<AttendanceStatisticDTO>> statistic(@RequestBody StatisticSearchParam param) {
        List<AttendanceStatisticDTO> data = attendanceService.statistic(param);
        return BaseModel.buildSuccess(data);
    }
}
