package com.example.demo.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DimissionDTO extends BaseDTO {

    private Long companyId;

    private Long applicantId;

    private String applicantName;
    @ApiModelProperty("申请时间")
    private Date submitDate;
    @ApiModelProperty("原因")
    private String reason;

    @TableField("isAgreed")
    private Integer isAgreed;
}
