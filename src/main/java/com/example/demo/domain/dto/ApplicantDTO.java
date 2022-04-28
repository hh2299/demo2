package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApplicantDTO extends BaseDTO {

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("民族")
    private String nation;

    @ApiModelProperty("出生日期")
    private Date birth;

    @ApiModelProperty("籍贯")
    private String origin;

    @ApiModelProperty("教育经历")
    private String education;

    @ApiModelProperty("毕业院校")
    private String graduateSchool;

    @ApiModelProperty("有无重大疾病")
    private Integer isMagorIllness;

    @ApiModelProperty("违纪违规情况")
    private String disciplinaryHistory;

    @ApiModelProperty(value = "意向工作")
    private Long intentionPositionId;

    @ApiModelProperty("意向工作")
    private String intentionPosition;

    @ApiModelProperty("获奖情况")
    private String awardHistory;

    @ApiModelProperty("自我评价")
    private String selfEvaluation;

    @ApiModelProperty("是否被雇佣")
    private Integer isHired;

    @ApiModelProperty("在职履历")
    private List<ApplicantCvDTO> applicantCvList;
}
