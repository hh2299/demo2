package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PerformanceDTO extends BaseDTO {

    private static final long serialVersionUID = 3969766068121065835L;

    private Long applicantId;

    private Long applicantCvId;

    private Date month;
    @ApiModelProperty("领导评估")
    private String leaderEval;
    @ApiModelProperty("自我评估")
    private String selfEval;

    @ApiModelProperty(value = "综合评级")
    private String level;
    @ApiModelProperty("学习能力")
    private Integer learn;
    @ApiModelProperty("责任意识")
    private Integer liability;
    @ApiModelProperty("社交能力")
    private Integer social;
    @ApiModelProperty("专业知识")
    private Integer expertise;
    @ApiModelProperty("竞争意识")
    private Integer competitive;
    @ApiModelProperty("计划执行能力")
    private Integer schedule;
    @ApiModelProperty("计算机水平")
    private Integer computer;
    @ApiModelProperty("人事管理评分")
    private Integer manage;
}
