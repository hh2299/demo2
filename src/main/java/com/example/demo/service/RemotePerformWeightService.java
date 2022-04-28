package com.example.demo.service;

import com.example.demo.domain.dto.PerformWeightDTO;
import com.example.demo.domain.dto.PerformanceDTO;
import com.example.demo.orm.entity.PerformWeight;

public interface RemotePerformWeightService {
    /**
     * 保存在职表现权重
     *
     * @param performWeight
     * @return
     */
    Long save(PerformWeightDTO performWeight);

    /**
     * 根据id获取在职表现权重
     * @param id
     * @return
     */
    PerformWeightDTO getById(Long id);
}
