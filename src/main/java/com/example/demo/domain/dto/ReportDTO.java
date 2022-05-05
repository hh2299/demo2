package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ReportDTO extends BaseDTO {

    private static final long serialVersionUID = -1423666949622146443L;

    @ApiModelProperty("公司id")
    private Long companyId;
    @ApiModelProperty("报告人id")
    private Long applicantId;
    @ApiModelProperty("报告人")
    private String applicant;
    @ApiModelProperty("报告人照片")
    private String applicantPhoto;
    @ApiModelProperty("报告时间")
    private Date reportDate;
    @ApiModelProperty("报告主题")
    private String topic;
    @ApiModelProperty("描述信息")
    private String description;
    @ApiModelProperty("报告内容")
    private String content;
}
