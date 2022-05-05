package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class AttendanceDTO extends BaseDTO {

    private static final long serialVersionUID = 6163747033882233210L;

    private Long companyId;

    private Long applicantId;

    private String applicant;

    private Date signInTime;

    private String signInScope;

    private Date signOutTime;

    private String signOutScope;

    private String note;
}
