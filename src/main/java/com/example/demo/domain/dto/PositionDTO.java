package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PositionDTO extends BaseDTO {

    private static final long serialVersionUID = -2222090157308305887L;

    @ApiModelProperty("公司id")
    private Long companyId;

    @ApiModelProperty("职位名称")
    private String name;
}
