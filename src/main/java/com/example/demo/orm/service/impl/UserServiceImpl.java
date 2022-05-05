package com.example.demo.orm.service.impl;

import com.example.demo.orm.entity.User;
import com.example.demo.orm.mapper.UserMapper;
import com.example.demo.orm.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
