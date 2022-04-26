package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HrDTO extends BaseDTO {

    private static final long serialVersionUID = -5367304801708064455L;

    @ApiModelProperty("Hr姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("个人信息")
    private String info;

    @ApiModelProperty("所属公司Id")
    private Long companyId;

    @ApiModelProperty("所属公司名称")
    private String company;
}
