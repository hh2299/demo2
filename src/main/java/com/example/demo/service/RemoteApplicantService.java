package com.example.demo.service;

import com.example.demo.domain.dto.ApplicantCvDTO;
import com.example.demo.domain.dto.ApplicantDTO;
import com.example.demo.domain.dto.ChangePositionDTO;
import com.example.demo.domain.dto.HireDTO;
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

    /**
     * 录用
     *
     * @param hireDTO
     * @return
     */
    Boolean hire(HireDTO hireDTO);

    /**
     * 删除应聘人
     * @param id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 获取应聘人履历
     * @param id
     * @return
     */
    List<ApplicantCvDTO> getCvList(Long id);

    /**
     * 解约
     * @param id
     * @return
     */
    Boolean termination(Long id);

    /**
     * 转职业
     * @param changePositionDTO
     * @return
     */
    Boolean changePosition(ChangePositionDTO changePositionDTO);
}
