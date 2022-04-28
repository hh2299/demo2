package com.example.demo.dao;

import com.example.demo.domain.dto.RecruitDTO;
import com.example.demo.domain.param.RecruitSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecruitMapping {

    List<RecruitDTO> getList(@Param("param") RecruitSearchParam param);
}
