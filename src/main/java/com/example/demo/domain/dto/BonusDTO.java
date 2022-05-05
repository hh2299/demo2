package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BonusDTO extends BaseDTO {

    private String name;

    private Long applicantId;

    private String applicant;

    private BigDecimal amount;

    private BigDecimal realAmount;

    private BigDecimal tax;

    private Date payDate;

    private Integer isFinished;

}
