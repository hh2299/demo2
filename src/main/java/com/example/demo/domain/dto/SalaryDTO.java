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

    private BigDecimal payableWage;

    private BigDecimal realWage;

    private BigDecimal insurancePay;

    private BigDecimal tax;

    private Date payDate;

    private Integer isFinished;
}
