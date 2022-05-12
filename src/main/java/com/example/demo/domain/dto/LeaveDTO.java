package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class LeaveDTO extends BaseDTO {

    private Long companyId;

    private Long applicantId;

    private String applicant;

    private Date startTime;

    private Date endTime;
    @ApiModelProperty("请假类型")
    private String type;
    @ApiModelProperty("说明")
    private String instructions;

    private Integer isAgreed;
}
