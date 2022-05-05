package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.mp.page.annotation.EnablePage;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.common.util.MD5Util;
import com.example.demo.common.util.StringUtil;
import com.example.demo.domain.dto.AdminDTO;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.param.UserSearchParam;
import com.example.demo.orm.entity.Admin;
import com.example.demo.orm.entity.User;
import com.example.demo.orm.mapper.UserMapper;
import com.example.demo.service.RemoteUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RemoteUserServiceImpl extends BaseService implements RemoteUserService {

    @Resource
    UserMapper userMapper;

    @Override
    @EnablePage
    public List<UserDTO> getUserList(UserSearchParam param) {
        if (param == null) {
            throw new MyException("没有查询参数");
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>();
        if (param.getCompanyId() != null) {
            wrapper.eq(User::getCompanyId, param.getCompanyId());
        }
        List<User> users = userMapper.selectList(wrapper);
        List<UserDTO> userDTOS = ConverterUtils.convertList(users, UserDTO.class);
        return userDTOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(UserDTO userDTO) {
        if (userDTO == null) {
            throw new MyException("没有保存信息");
        }

        Long id = userDTO.getId();

        User user = ConverterUtils.convert(userDTO, User.class);
        if (StringUtil.isNotNull(user.getPassword())) {
            user.setPassword(MD5Util.md5(user.getPassword()));
        }
        if (id == null) {
            //注册
            if(isDuplicateUserName(userDTO.getUsername())) {
                throw new MyException("用户名重复");
            }
            userMapper.insert(user);
            id = user.getId();
        } else {
            oldPasswordIsWrong(userDTO);
            if (userDTO.getPassword() != null) {
                //修改密码
                userMapper.updateById(user);
            } else {
                //修改个人信息
                User user1 = new User();
                user1.setName(user.getName());
                user1.setSex(user.getSex());
                user1.setPosition(user.getPosition());
                user1.setUsername(user.getUsername());
                user1.setPhone(user.getPhone());
                user1.setWechat(user.getWechat());
                user1.setNote(user.getNote());
                LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
                wrapper.eq(User::getId, id);
                userMapper.update(user1, wrapper);
            }
        }

        return id;
    }



    @Override
    public Boolean delete(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }

        int count = userMapper.deleteById(id);

        return count == 1;
    }

    @Override
    public UserDTO getUserById(Long id) {
        if (id == null) {
            throw new MyException("未传入必须的id");
        }

        User user = userMapper.selectById(id);
        user.setPassword(null);
        UserDTO userDTO = ConverterUtils.convert(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        if (StringUtil.isNull(userDTO.getUsername()) || StringUtil.isNull(userDTO.getPassword())) {
            throw new MyException("请输入用户名和密码");
        }
        String password = MD5Util.md5(userDTO.getPassword());
        String userName = userDTO.getUsername();

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, userName);
        wrapper.eq(User::getPassword, password);
        List<User> userList = userMapper.selectList(wrapper);
        if (userList.size() == 1) {
            UserDTO returnUserDTO = ConverterUtils.convert(userList.get(0), UserDTO.class);
            returnUserDTO.setPassword(null);
            return returnUserDTO;
        }
        return null;
    }

    private boolean isDuplicateUserName(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        List<User> adminList = userMapper.selectList(wrapper);
        return adminList.size() > 0;
    }

    private boolean oldPasswordIsWrong(UserDTO userDTO) {
        if (StringUtil.isNotNull(userDTO.getOldPassword())) {
            //修改密码
            UserDTO tempUser = new UserDTO();
            tempUser.setUsername(userDTO.getUsername());
            tempUser.setPassword(userDTO.getOldPassword());
            UserDTO userDTO1 = login(tempUser);
            if (userDTO1 == null) {
                throw new MyException("原密码错误");
            }
        }
        return true;
    }
}
