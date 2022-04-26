package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CompanyDTO extends BaseDTO {

    @ApiModelProperty("公司名称")
    private String name;

    @ApiModelProperty("公司地址")
    private String address;
}
