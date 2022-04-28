package com.example.demo.dao;

import com.example.demo.domain.dto.ApplicantDTO;
import com.example.demo.domain.param.ApplicantSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApplicantMapping {

    List<ApplicantDTO> getList(@Param("param") ApplicantSearchParam param);
}
