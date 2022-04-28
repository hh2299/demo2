package com.example.demo.service.Impl;

import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.domain.dto.PerformWeightDTO;
import com.example.demo.orm.entity.PerformWeight;
import com.example.demo.orm.mapper.PerformWeightMapper;
import com.example.demo.service.RemotePerformWeightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RemotePerformWeightServiceImpl extends BaseService implements RemotePerformWeightService {

    @Resource
    PerformWeightMapper performWeightMapper;

    @Override
    public Long save(PerformWeightDTO performWeightDTO) {
        if (performWeightDTO == null) {
            throw new MyException("请完善权重信息");
        }
        if (performWeightDTO.getWeightSum() != 100) {
            throw new MyException("请确保权重和为100%");
        }
        Long id = performWeightDTO.getId();
        PerformWeight performWeight = ConverterUtils.convert(performWeightDTO, PerformWeight.class);
        if (id == null) {
            performWeightMapper.insert(performWeight);
            id = performWeight.getId();
        } else {
            performWeightMapper.updateById(performWeight);
        }
        return id;
    }

    @Override
    public PerformWeightDTO getById(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }

        PerformWeight performWeight = performWeightMapper.selectById(id);

        return ConverterUtils.convert(performWeight, PerformWeightDTO.class);
    }
}
