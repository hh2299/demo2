package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.Admin;
import com.example.demo.orm.mapper.AdminMapper;
import com.example.demo.orm.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 后台管理员 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-05-04
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
