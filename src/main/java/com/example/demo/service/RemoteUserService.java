package com.example.demo.service;

import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.param.UserSearchParam;

import java.util.List;

public interface RemoteUserService {

//    查询Hr列表
    List<UserDTO> getUserList(UserSearchParam param);

//    保存Hr
    Long save(UserDTO hrDTO);

//    根据指定id删除Hr
    Boolean delete(Long id);

    //    根据指定id获取Hr
    UserDTO getUserById(Long id);

    UserDTO login(UserDTO userDTO);
}
