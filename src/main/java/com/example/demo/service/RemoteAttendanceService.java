package com.example.demo.service;

import com.example.demo.domain.dto.AttendanceDTO;
import com.example.demo.domain.dto.AttendanceStatisticDTO;
import com.example.demo.domain.param.AttendanceSearchParam;
import com.example.demo.domain.param.StatisticSearchParam;
import com.example.demo.domain.vo.AttendanceVo;

import java.util.List;

public interface RemoteAttendanceService {

    List<AttendanceVo> getList(AttendanceSearchParam param);

    Long save(AttendanceDTO attendance);

    List<AttendanceStatisticDTO> statistic(StatisticSearchParam param);
}
