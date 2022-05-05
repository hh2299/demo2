package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class InsuranceDTO extends BaseDTO {
    private Long applicantId;

    private String applicant;

    private BigDecimal endowment;

    private BigDecimal medical;

    private BigDecimal unemployment;

    private BigDecimal inductrialInjury;

    private BigDecimal fertility;

    private BigDecimal housingFund;

    private Date payDate;
}
