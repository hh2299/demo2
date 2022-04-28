package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.dao.PerformanceMapping;
import com.example.demo.domain.dto.PerformanceDTO;
import com.example.demo.domain.param.PerformanceSearchParam;
import com.example.demo.orm.entity.*;
import com.example.demo.orm.mapper.*;
import com.example.demo.service.RemotePerformanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemotePerformanceServiceImpl extends BaseService implements RemotePerformanceService {

    @Resource
    PerformanceMapping performanceMapping;
    @Resource
    PerformanceMapper performanceMapper;
    @Resource
    HireMapper hireMapper;
    @Resource
    RecruitMapper recruitMapper;
    @Resource
    PerformWeightMapper performWeightMapper;
    @Resource
    ApplicantCvMapper applicantCvMapper;

    @Override
    public List<PerformanceDTO> getList(PerformanceSearchParam param) {
        if (param == null) {
            throw new MyException("没有查询参数");
        }

        List<PerformanceDTO> performanceList = performanceMapping.getList(param);

        return performanceList;
    }

    @Override
    public Long save(PerformanceDTO performanceDTO) {
        if (performanceDTO == null) {
            throw new MyException("请完善表现信息");
        }

        //根据应聘人id获取他当时的招聘信息
        Long applicantId = performanceDTO.getApplicantId();
        Performance performance = ConverterUtils.convert(performanceDTO, Performance.class);
        List<Hire> hireList = super.getRelationList(hireMapper, Hire::getApplicantId, applicantId);
        if (hireList.size() != 1) {
            throw new MyException("信息有误");
        }
        Recruit recruit = recruitMapper.selectById(hireList.get(0).getRecruitId());
        Long weight_id = recruit.getWeightId();
        PerformWeight performWeight = performWeightMapper.selectById(weight_id);
        if (performWeight == null) {
            throw new MyException("信息有误");
        }
        //计算总评
        String level = countLevel(performWeight, performanceDTO);
        performance.setLevel(level);

        Long id = performanceDTO.getId();
        if (id == null) {
            LambdaQueryWrapper<ApplicantCv> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ApplicantCv::getApplicantId, applicantId).isNull(ApplicantCv::getEndDate);
            List<ApplicantCv> applicantCvs = applicantCvMapper.selectList(wrapper);
            if (applicantCvs.size() != 1) {
                throw new MyException("信息有误");
            }
            performance.setApplicantCvId(applicantCvs.get(0).getId());
            performanceMapper.insert(performance);
            id = performance.getId();
        } else {
            performanceMapper.updateById(performance);
        }
        return id;
    }

    private String countLevel(PerformWeight performWeight, PerformanceDTO performance) {
        if (performance == null || performWeight == null) {
            throw new MyException("信息有误");
        }
        int sum = 0;
        String s = new String();
        sum = performance.getCompetitive() * performWeight.getCompetitive()
                + performance.getComputer() * performWeight.getComputer()
                + performance.getExpertise() * performWeight.getExpertise()
                + performance.getLearn() * performWeight.getLearn()
                + performance.getLiability() * performWeight.getLiability()
                + performance.getManage() * performWeight.getManage()
                + performance.getSchedule() * performWeight.getSchedule()
                + performance.getSocial() * performWeight.getSocial();
        sum /= 200;
        switch (sum) {
            case 5:
            case 4:
                s = "A";
                break;
            case 3:
                s = "B";
                break;
            case 2:
                s = "C";
                break;
            case 1:
                s = "D";
                break;
            case 0:
                s = "E";
                break;
            default:
                break;
        }
        return s;
       }
}
