package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.ApplicantWeight;
import com.example.demo.orm.mapper.ApplicantWeightMapper;
import com.example.demo.orm.service.IApplicantWeightService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应聘人（员工）-》权重关系表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-05-05
 */
@Service
public class ApplicantWeightServiceImpl extends ServiceImpl<ApplicantWeightMapper, ApplicantWeight> implements IApplicantWeightService {

}
