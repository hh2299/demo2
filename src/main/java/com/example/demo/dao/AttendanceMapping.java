package com.example.demo.dao;

import com.example.demo.domain.param.LeaveSearchParam;
import com.example.demo.orm.entity.Attendance;
import com.example.demo.orm.entity.LeaveRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AttendanceMapping {
    List<Attendance> selectCount(@Param("start") Date start, @Param("end") Date end, @Param("applicantId") Long id);

    List<LeaveRecord> selectLeaveList(@Param("param") LeaveSearchParam param);
}
