package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Applicant;
import com.example.demo.orm.mapper.ApplicantMapper;
import com.example.demo.orm.service.IApplicantService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-05-05
 */
@Service
public class ApplicantServiceImpl extends ServiceImpl<ApplicantMapper, Applicant> implements IApplicantService {

}
