package com.example.demo.service.Impl;

import com.example.demo.common.exception.MyException;
import com.example.demo.common.mp.page.annotation.EnablePage;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.dao.ApplicantMapping;
import com.example.demo.domain.dto.ApplicantCvDTO;
import com.example.demo.domain.dto.ApplicantDTO;
import com.example.demo.domain.param.ApplicantSearchParam;
import com.example.demo.orm.entity.Applicant;
import com.example.demo.orm.entity.ApplicantCv;
import com.example.demo.orm.mapper.ApplicantCvMapper;
import com.example.demo.orm.mapper.ApplicantMapper;
import com.example.demo.service.RemoteApplicantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RemoteApplicantServiceImpl extends BaseService implements RemoteApplicantService {

    @Resource
    ApplicantMapper applicantMapper;
    @Resource
    ApplicantMapping applicantMapping;
    @Resource
    ApplicantCvMapper applicantCvMapper;

    @Override
    @EnablePage
    public List<ApplicantDTO> getList(ApplicantSearchParam param) {
        if (param == null) {
            throw new MyException("没有查询参数");
        }
        List<ApplicantDTO> applicantDTOS = applicantMapping.getList(param);
        return applicantDTOS;
    }

    @Override
    public ApplicantDTO getById(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }
        Applicant applicant = applicantMapper.selectById(id);
        ApplicantDTO applicantDTO = ConverterUtils.convert(applicant, ApplicantDTO.class);
        List<ApplicantCv> applicantCvs = new ArrayList<>();
        if (applicant != null) {
            applicantCvs  = super.getRelationList(applicantCvMapper, ApplicantCv::getApplicantId, applicant.getId());
        }
        applicantDTO.setApplicantCvList(ConverterUtils.convertList(applicantCvs, ApplicantCvDTO.class));
        return applicantDTO;
    }


    @Override
    public Long save(ApplicantDTO applicantDTO) {
        if (applicantDTO == null) {
            throw new MyException("请完善应聘人信息");
        }

        Long id = applicantDTO.getId();
        Applicant applicant = ConverterUtils.convert(applicantDTO, Applicant.class);

        if (id == null) {
            applicantMapper.insert(applicant);
            id = applicant.getId();
        } else {
            applicantMapper.updateById(applicant);
            //TODO 签约另找接口
        }

        return id;
    }
}
