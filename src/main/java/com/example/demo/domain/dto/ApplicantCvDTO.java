package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class ApplicantCvDTO extends BaseDTO {

    private static final long serialVersionUID = 8012415806694029724L;

    private Long applicantId;

    private Long companyId;

    private String company;

    private Long positionId;

    private String position;

    private Date startDate;

    private Date endDate;
}
