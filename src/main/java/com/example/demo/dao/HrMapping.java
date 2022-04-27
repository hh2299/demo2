package com.example.demo.dao;

import com.example.demo.domain.dto.HrDTO;
import com.example.demo.domain.param.HrSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface HrMapping{


    List<HrDTO> getHrList(@Param("param") HrSearchParam param,@Param("id") Long id);


}
