package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RecruitDTO extends BaseDTO {

    private static final long serialVersionUID = -9057271635054033885L;

    @ApiModelProperty("职位id")
    private Long positionId;

    @ApiModelProperty("公司id")
    private Long companyId;

    @ApiModelProperty("职位")
    private String positionName;

    @ApiModelProperty("招聘人数")
    private Integer num;

    @ApiModelProperty("招聘开始时间")
    private Date startDate;

    @ApiModelProperty("招聘结束时间")
    private Date endDate;

    @ApiModelProperty("薪资")
    private BigDecimal salary;

    @ApiModelProperty("要求")
    private String requirements;

    @ApiModelProperty("是否招聘结束")
    private Integer isFinished;

    @ApiModelProperty("岗位详情")
    private String detail;

    private PerformWeightDTO performWeight;
}
