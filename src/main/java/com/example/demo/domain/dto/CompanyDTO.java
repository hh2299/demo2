package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import com.example.demo.orm.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CompanyDTO extends BaseDTO {

    private String name;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("法人")
    private String legalPerson;
    @ApiModelProperty("法人身份证")
    private String legalIcid;
    @ApiModelProperty("法人电话号码")
    private String legalPhone;
    @ApiModelProperty("省份")
    private String province;

    private String city;

    private String area;

    private String content;
    @ApiModelProperty("行业类别")
    private String industryCategory;

    private Integer isAgreed;

    private UserDTO user;

    private Date joinDate;

    private Long userId;
}
