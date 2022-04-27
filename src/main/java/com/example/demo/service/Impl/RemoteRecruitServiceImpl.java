package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.domain.dto.RecruitDTO;
import com.example.demo.domain.param.RecruitSearchParam;
import com.example.demo.orm.entity.Recruit;
import com.example.demo.orm.mapper.RecruitMapper;
import com.example.demo.service.RemotePerformWeightService;
import com.example.demo.service.RemoteRecruitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemoteRecruitServiceImpl extends BaseService implements RemoteRecruitService {

    @Resource
    RemotePerformWeightService performWeightService;
    @Resource
    RecruitMapper recruitMapper;

    @Override
    public List<RecruitDTO> getRecruitList(RecruitSearchParam param) {
        //TODO 暂时没有查询要求
        LambdaQueryWrapper<Recruit> wrapper = new LambdaQueryWrapper<>();
        if (param != null && param.getCompanyId() != null) {
            wrapper.eq(Recruit::getCompanyId, param.getCompanyId());
        }
        List<Recruit>  recruits = recruitMapper.selectList(wrapper);
        return ConverterUtils.convertList(recruits, RecruitDTO.class);
    }

    @Override
    public Long save(RecruitDTO recruitDTO) {

        if (recruitDTO == null) {
            throw new MyException("请完善招聘信息");
        }
        if (recruitDTO.getPerformWeight() == null) {
            throw new MyException("请完善权重信息");
        }

        Long weight_id = performWeightService.save(recruitDTO.getPerformWeight());

        Recruit recruit = ConverterUtils.convert(recruitDTO, Recruit.class);
        recruit.setWeightId(weight_id);
        Long id = recruit.getId();
        if (id == null) {
            recruitMapper.insert(recruit);
            id = recruit.getId();
        } else {
            recruitMapper.updateById(recruit);
        }

        return id;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public RecruitDTO getRecruitById(Long id) {
        return null;
    }
}
