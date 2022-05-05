package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Salary;
import com.example.demo.orm.mapper.SalaryMapper;
import com.example.demo.orm.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-05-02
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

}
