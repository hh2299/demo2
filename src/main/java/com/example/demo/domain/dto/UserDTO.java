package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import lombok.Data;

@Data
public class UserDTO extends BaseDTO {


    private static final long serialVersionUID = -5587238900359192826L;
    private String name;

    private String photoPath;

    private String sex;

    private String position;

    private String username;

    private String password;

    private String phone;

    private String wechat;

    private String note;

    private Long companyId;

    private String companyName;

    private String oldPassword;
}
