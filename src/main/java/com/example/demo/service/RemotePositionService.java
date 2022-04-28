package com.example.demo.service;

import com.example.demo.domain.dto.PositionDTO;
import com.example.demo.domain.param.PositionSearchParam;

import java.util.List;

public interface RemotePositionService {
    /**
     * 获取岗位列表
     * @param param
     * @return
     */
    List<PositionDTO> getPositionList(PositionSearchParam param);

    /***
     * 保存职位
     * @param positionDTO
     * @return
     */
    Long save(PositionDTO positionDTO);
}
