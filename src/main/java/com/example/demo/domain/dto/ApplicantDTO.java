package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApplicantDTO extends BaseDTO {

    private String name;

    private String sex;

    private String nation;

    private Date birth;

    private String origin;

    private String education;

    private String graduateSchool;

    private Integer isMagorIllness;

    private String disciplinaryHistory;

    @ApiModelProperty(value = "意向工作")
    private Long intentionPositionId;

    private String intentionPosition;

    private String awardHistory;

    private String selfEvaluation;

    private Integer isHired;

    private List<ApplicantCvDTO> applicantCvList;
}
