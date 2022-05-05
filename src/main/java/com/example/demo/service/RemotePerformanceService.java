package com.example.demo.service;

import com.example.demo.domain.dto.PerformanceDTO;
import com.example.demo.domain.param.PerformanceSearchParam;

import java.util.List;

public interface RemotePerformanceService {

    /**
     * 获取在职表现列表
     * @param param
     * @return
     */
    List<PerformanceDTO> getList(PerformanceSearchParam param);

    /**
     * 评价
     * @param performance
     * @return
     */
    Long save(PerformanceDTO performance);

    PerformanceDTO getNewestPerform(Long id);
}
