package com.example.demo.dao;

import com.example.demo.domain.dto.ReportDTO;
import com.example.demo.domain.dto.TaskDTO;
import com.example.demo.domain.dto.TaskRecordDTO;
import com.example.demo.domain.param.ReportSearchParam;
import com.example.demo.domain.param.TaskSearchParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskMapping {

    List<TaskDTO> getList(@Param("param") TaskSearchParam param);

    List<TaskRecordDTO> getRecordListByTaskId(@Param("id") Long id);

    List<ReportDTO> getReportList(@Param("param") ReportSearchParam param);
}
