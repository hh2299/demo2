package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AdminDTO extends BaseDTO {


    @ApiModelProperty("uid")
    private String uid;

    private String username;

    private String password;

    private String oldPassword;

}
