package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Performance;
import com.example.demo.orm.mapper.PerformanceMapper;
import com.example.demo.orm.service.IPerformanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-04-27
 */
@Service
public class PerformanceServiceImpl extends ServiceImpl<PerformanceMapper, Performance> implements IPerformanceService {

}
