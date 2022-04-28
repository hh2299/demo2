package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PerformanceDTO extends BaseDTO {

    private static final long serialVersionUID = 3969766068121065835L;

    private Long applicantCvId;

    private Date month;

    private String leaderEval;

    private String selfEval;

    @ApiModelProperty(value = "综合评级")
    private String level;

    private Integer learn;

    private Integer liability;

    private Integer social;

    private Integer expertise;

    private Integer competitive;

    private Integer schedule;

    private Integer computer;

    private Integer manage;
}
