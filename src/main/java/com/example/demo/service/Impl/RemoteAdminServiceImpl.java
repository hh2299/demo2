package com.example.demo.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.exception.MyException;
import com.example.demo.common.service.BaseService;
import com.example.demo.common.util.ConverterUtils;
import com.example.demo.common.util.MD5Util;
import com.example.demo.common.util.StringUtil;
import com.example.demo.domain.dto.AdminDTO;
import com.example.demo.domain.dto.AttendanceDTO;
import com.example.demo.orm.entity.Admin;
import com.example.demo.orm.mapper.AdminMapper;
import com.example.demo.service.RemoteAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class RemoteAdminServiceImpl extends BaseService implements RemoteAdminService {

    @Resource
    AdminMapper adminMapper;

    @Override
    public Long save(AdminDTO adminDTO) {
        if (adminDTO == null) {
            throw new MyException("请完善管理员信息");
        }
        Long id = adminDTO.getId();
        Admin admin = ConverterUtils.convert(adminDTO, Admin.class);
        if (id == null) {
            //注册
            if(isDuplicateUserName(adminDTO.getUsername())) {
                throw new MyException("用户名重复");
            }
            String uid = UUID.randomUUID().toString();
            admin.setUid(uid);
            admin.setPassword(MD5Util.md5(adminDTO.getPassword()));
            adminMapper.insert(admin);
            id = admin.getId();
        }else {
            if (StringUtil.isNotNull(adminDTO.getOldPassword())){
                //修改密码
                AdminDTO tempAdmin = new AdminDTO();
                tempAdmin.setUsername(adminDTO.getUsername());
                tempAdmin.setPassword(adminDTO.getOldPassword());
                Long loginId = loginAdmin(tempAdmin);
                if (loginId == 0L) {
                    throw new MyException("原密码错误");
                }
                admin.setPassword(MD5Util.md5(admin.getPassword()));
            }
            adminMapper.updateById(admin);
        }

        return id;
    }


    @Override
    public Long loginAdmin(AdminDTO adminDTO) {
        if (StringUtil.isNull(adminDTO.getUsername()) || StringUtil.isNull(adminDTO.getPassword())) {
            throw new MyException("请输入用户名和密码");
        }
        String password = MD5Util.md5(adminDTO.getPassword());
        String userName = adminDTO.getUsername();

        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUsername, userName);
        wrapper.eq(Admin::getPassword, password);
        List<Admin> adminList = adminMapper.selectList(wrapper);
        if (adminList.size() == 1) {
            return adminList.get(0).getId();
        }
        return 0L;
    }


    @Override
    public AdminDTO getById(Long id) {
        Admin admin = adminMapper.selectById(id);
        admin.setPassword(null);
        return ConverterUtils.convert(admin, AdminDTO.class);
    }

    private boolean isDuplicateUserName(String username) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Admin::getUsername, username);
        List<Admin> adminList = adminMapper.selectList(wrapper);
        return adminList.size() > 0;
    }

}
