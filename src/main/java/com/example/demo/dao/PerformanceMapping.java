package com.example.demo.dao;

import com.example.demo.domain.dto.PerformanceDTO;
import com.example.demo.domain.param.PerformanceSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PerformanceMapping {

    /**
     * 查询在职表现
     * @param param
     * @return
     */
    List<PerformanceDTO> getList(@Param("param") PerformanceSearchParam param);

}
