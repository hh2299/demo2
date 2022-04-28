package com.example.demo.service;

import com.example.demo.domain.dto.ApplicantDTO;
import com.example.demo.domain.param.ApplicantSearchParam;

import java.util.List;

public interface RemoteApplicantService {

    /**
     * 获取应聘者列表
     * @param param
     * @return
     */
    List<ApplicantDTO> getList(ApplicantSearchParam param);

    /**
     * 根据id获取应聘者信息
     * @param id
     * @return
     */
    ApplicantDTO getById(Long id);

    /**
     * 保存应聘者信息
     * @param applicantDTO
     * @return
     */
    Long save(ApplicantDTO applicantDTO);
}
