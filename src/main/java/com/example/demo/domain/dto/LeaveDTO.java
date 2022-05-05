package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class LeaveDTO extends BaseDTO {

    private Long companyId;

    private Long applicantId;

    private String applicant;

    private Date startTime;

    private Date endTime;

    private String type;

    private String instructions;

    private Integer isAgreed;
}
