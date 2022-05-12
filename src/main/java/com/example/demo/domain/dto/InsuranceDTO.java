package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InsuranceDTO extends BaseDTO {
    private Long applicantId;

    private String applicant;
    @ApiModelProperty("养老保险")
    private BigDecimal endowment;
    @ApiModelProperty("医疗保险")
    private BigDecimal medical;
    @ApiModelProperty("失业保险")
    private BigDecimal unemployment;
    @ApiModelProperty("工伤保险")
    private BigDecimal inductrialInjury;
    @ApiModelProperty("生育保险")
    private BigDecimal fertility;
    @ApiModelProperty("公积金")
    private BigDecimal housingFund;
    private Date payDate;
}
