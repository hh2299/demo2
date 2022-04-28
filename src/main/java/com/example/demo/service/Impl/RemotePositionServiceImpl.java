package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.mp.page.annotation.EnablePage;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.common.util.StringUtil;
import com.example.demo.domain.dto.PositionDTO;
import com.example.demo.domain.param.PositionSearchParam;
import com.example.demo.orm.entity.CompanyPosition;
import com.example.demo.orm.entity.Position;
import com.example.demo.orm.mapper.CompanyPositionMapper;
import com.example.demo.orm.mapper.PositionMapper;
import com.example.demo.service.RemotePositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RemotePositionServiceImpl extends BaseService implements RemotePositionService {

    @Resource
    PositionMapper positionMapper;
    @Resource
    CompanyPositionMapper companyPositionMapper;

    @Override
    @EnablePage
    public List<PositionDTO> getPositionList(PositionSearchParam param) {
        if (param == null) {
            throw new MyException("没有查询参数");
        }

        LambdaQueryWrapper<Position> wrapper = new LambdaQueryWrapper<>();

        List<CompanyPosition> companyPositions = new ArrayList<>();
        if (param.getCompanyId() != null) {
            companyPositions = super.getRelationList(companyPositionMapper, CompanyPosition::getCompanyId, param.getCompanyId());
            List<Long> positionIds = companyPositions.stream().map(e -> e.getPositionId()).collect(Collectors.toList());
            wrapper.in(Position::getId, positionIds);
        }

        if (StringUtil.isNotNull(param.getName())) {
            wrapper.like(Position::getName, param.getName());
        }

        List<Position> positions = positionMapper.selectList(wrapper);
        List<PositionDTO> positionDTOS = ConverterUtils.convertList(positions, PositionDTO.class);
        positionDTOS.stream().forEach(e -> e.setCompanyId(param.getCompanyId()));
        return positionDTOS;
    }
    @Override
    public Long save(PositionDTO positionDTO) {
        if (positionDTO == null || positionDTO.getCompanyId() == null) {
            throw new MyException("请完善职位信息");
        }
        Position position = ConverterUtils.convert(positionDTO, Position.class);

        Long id = position.getId();
        CompanyPosition companyPosition = new CompanyPosition();
        companyPosition.setCompanyId(positionDTO.getCompanyId());
        if (id == null) {
            positionMapper.insert(position);
            id = position.getId();
            companyPosition.setPositionId(id);
            companyPositionMapper.insert(companyPosition);
        } else {
            positionMapper.updateById(position);
        }




        return id;
    }
}
