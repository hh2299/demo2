package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Hr;
import com.example.demo.orm.mapper.HrMapper;
import com.example.demo.orm.service.IHrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-04-26
 */
@Service
public class HrServiceImpl extends ServiceImpl<HrMapper, Hr> implements IHrService {

}
