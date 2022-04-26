package com.example.demo.service;

import com.example.demo.domain.dto.HrDTO;
import com.example.demo.domain.param.HrSearchParam;

import java.util.List;

public interface RemoteHrService {

//    查询Hr列表
    List<HrDTO> getHrList(HrSearchParam param);

//    保存Hr
    Long save(HrDTO hrDTO);

//    根据指定id删除Hr
    Boolean delete(Long id);

    //    根据指定id获取Hr
    HrDTO getHrById(Long id);
}
