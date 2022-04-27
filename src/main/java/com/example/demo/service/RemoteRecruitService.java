package com.example.demo.service;

import com.example.demo.domain.dto.RecruitDTO;
import com.example.demo.domain.param.RecruitSearchParam;

import java.util.List;

public interface RemoteRecruitService {
    //    查询招聘列表
    List<RecruitDTO> getRecruitList(RecruitSearchParam param);

    //    保存招聘
    Long save(RecruitDTO recruitDTO);

    //    根据指定id删除招聘
    Boolean delete(Long id);

    //    根据指定id获取招聘
    RecruitDTO getRecruitById(Long id);
}
