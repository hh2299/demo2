package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import com.example.demo.orm.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CompanyDTO extends BaseDTO {

    private String name;

    private String address;

    private String legalPerson;

    private String legalIcid;

    private String legalPhone;

    private String province;

    private String city;

    private String area;

    private String content;

    private String industryCategory;

    private Integer isAgreed;

    private UserDTO user;

    private Date joinDate;
}
