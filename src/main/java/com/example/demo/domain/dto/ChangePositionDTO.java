package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChangePositionDTO extends BaseDTO {

    @ApiModelProperty("应聘人id")
    private Long applicantId;
    @ApiModelProperty("原职位id")
    private Long oldPositionId;
    @ApiModelProperty("新职位id")
    private Long newPositionId;
    @ApiModelProperty("新职位名称")
    private String newPositionName;
    @ApiModelProperty("新权重")
    private PerformWeightDTO performWeight;


}
