package com.example.demo.service;

import com.example.demo.domain.dto.ReportDTO;
import com.example.demo.domain.param.ReportSearchParam;

import java.util.List;

public interface RemoteReportService {

    /**
     * 获取报告列表
     * @param param
     * @return
     */
    List<ReportDTO> getList(ReportSearchParam param);

    /**
     * 爆粗报告
     * @param reportDTO
     * @return
     */
    Long save(ReportDTO reportDTO);

    /**
     * 删除指定报告
     * @param id
     * @return
     */
    Boolean delete(Long id);
}
