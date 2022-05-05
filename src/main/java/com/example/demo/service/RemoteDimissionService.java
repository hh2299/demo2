package com.example.demo.service;

import com.example.demo.domain.dto.DimissionDTO;
import com.example.demo.domain.param.DimissionSearchParam;
import com.example.demo.domain.vo.DimissionVo;

import java.util.List;

public interface RemoteDimissionService {
    List<DimissionVo> getList(DimissionSearchParam param);

    Long save(DimissionDTO dimissionDTO);
}
