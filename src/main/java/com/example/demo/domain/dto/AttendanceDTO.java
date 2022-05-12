package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AttendanceDTO extends BaseDTO {

    private static final long serialVersionUID = 6163747033882233210L;

    private Long companyId;

    private Long applicantId;

    private String applicant;

    @ApiModelProperty("签到时间")
    private Date signInTime;

    @ApiModelProperty("签到地点")
    private String signInScope;

    @ApiModelProperty("签退时间")
    private Date signOutTime;

    @ApiModelProperty("签退地点")
    private String signOutScope;

    private String note;
}
