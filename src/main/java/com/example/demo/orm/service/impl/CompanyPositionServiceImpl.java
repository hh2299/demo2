package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.CompanyPosition;
import com.example.demo.orm.mapper.CompanyPositionMapper;
import com.example.demo.orm.service.ICompanyPositionService;
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
public class CompanyPositionServiceImpl extends ServiceImpl<CompanyPositionMapper, CompanyPosition> implements ICompanyPositionService {

}
