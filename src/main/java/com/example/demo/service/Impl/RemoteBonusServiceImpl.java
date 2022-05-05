package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.dao.SalaryMapping;
import com.example.demo.domain.dto.BonusDTO;
import com.example.demo.domain.param.BonusSearchParam;
import com.example.demo.domain.param.SalarySearchParam;
import com.example.demo.orm.entity.Bonus;
import com.example.demo.orm.mapper.BonusMapper;
import com.example.demo.orm.mapper.SalaryMapper;
import com.example.demo.service.RemoteBonusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemoteBonusServiceImpl extends BaseService implements RemoteBonusService {

    @Resource
    SalaryMapping salaryMapping;
    @Resource
    BonusMapper bonusMapper;

    @Override
    public List<BonusDTO> getList(BonusSearchParam param) {
        if (param == null) {
            throw new MyException("请完善查询参数");
        }

        return salaryMapping.getBonusList(param);
    }

    @Override
    public Long save(BonusDTO bonusDTO) {
        if (bonusDTO == null) {
            throw new MyException("请完善奖励信息");
        }

        Long id = bonusDTO.getId();
        Bonus bonus = ConverterUtils.convert(bonusDTO, Bonus.class);
        if (id == null) {
            bonusMapper.insert(bonus);
            id = bonus.getId();
        } else {
            bonusMapper.updateById(bonus);
        }

        return id;
    }
}
