package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SalaryDTO extends BaseDTO {

    @ApiModelProperty("员工Id")
    private Long applicantId;

    private String applicant;

    @ApiModelProperty("应付工资")
    private BigDecimal payableWage;
    @ApiModelProperty("实际到手工资")
    private BigDecimal realWage;
    @ApiModelProperty("保险金额")
    private BigDecimal insurancePay;
    @ApiModelProperty("税额")
    private BigDecimal tax;
    private Date payDate;

    private Integer isFinished;
}
