package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Insurance;
import com.example.demo.orm.mapper.InsuranceMapper;
import com.example.demo.orm.service.IInsuranceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-04-29
 */
@Service
public class InsuranceServiceImpl extends ServiceImpl<InsuranceMapper, Insurance> implements IInsuranceService {

}
