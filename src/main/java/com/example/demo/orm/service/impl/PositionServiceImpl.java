package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Position;
import com.example.demo.orm.mapper.PositionMapper;
import com.example.demo.orm.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-04-28
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

}
