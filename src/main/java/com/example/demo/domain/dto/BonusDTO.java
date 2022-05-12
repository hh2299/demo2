package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BonusDTO extends BaseDTO {

    private String name;

    private Long applicantId;

    private String applicant;
    @ApiModelProperty("原来的奖金")
    private BigDecimal amount;
    @ApiModelProperty("实际奖金")
    private BigDecimal realAmount;
    @ApiModelProperty("扣税")
    private BigDecimal tax;
    @ApiModelProperty("支付时间")
    private Date payDate;

    private Integer isFinished;

}
