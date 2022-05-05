package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Company;
import com.example.demo.orm.mapper.CompanyMapper;
import com.example.demo.orm.service.ICompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-05-04
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {

}
