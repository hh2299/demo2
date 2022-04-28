package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.mp.page.annotation.EnablePage;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.domain.dto.PositionDTO;
import com.example.demo.domain.param.PositionSearchParam;
import com.example.demo.orm.entity.Position;
import com.example.demo.orm.mapper.PositionMapper;
import com.example.demo.service.RemotePositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemotePositionServiceImpl extends BaseService implements RemotePositionService {

    @Resource
    PositionMapper positionMapper;

    @Override
    @EnablePage
    public List<PositionDTO> getPositionList(PositionSearchParam param) {
        LambdaQueryWrapper<Position> wrapper = new LambdaQueryWrapper<>();
        if (param.getCompanyId() != null) {
            wrapper.eq(Position::getCompanyId, param.getCompanyId());
        }
        List<Position> positions = positionMapper.selectList(wrapper);
        return ConverterUtils.convertList(positions, PositionDTO.class);
    }

    @Override
    public Long save(PositionDTO positionDTO) {
        if (positionDTO == null || positionDTO.getCompanyId() == null) {
            throw new MyException("请完善职位信息");
        }
        Position position = ConverterUtils.convert(positionDTO, Position.class);
        Long id = position.getId();
        if (id == null) {
            positionMapper.insert(position);
            id = position.getId();
        } else {
            positionMapper.updateById(position);
        }

        return id;
    }
}
