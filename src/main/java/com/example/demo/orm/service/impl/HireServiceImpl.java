package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Hire;
import com.example.demo.orm.mapper.HireMapper;
import com.example.demo.orm.service.IHireService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-05-03
 */
@Service
public class HireServiceImpl extends ServiceImpl<HireMapper, Hire> implements IHireService {

}
