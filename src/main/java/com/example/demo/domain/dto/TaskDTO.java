package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskDTO extends BaseDTO {

    private static final long serialVersionUID = -5385163331027023529L;

    @ApiModelProperty("公司id")
    private Long companyId;

    @ApiModelProperty("应聘人id")
    private Long applicantId;

    @ApiModelProperty("应聘人name")
    private String applicant;

    @ApiModelProperty("应聘人照片")
    private String applicantPhoto;


    @ApiModelProperty("任务内容")
    private String content;

    @ApiModelProperty("是否需要填写工作总结")
    private Integer isSummary;

    @ApiModelProperty("总结")
    private String summary;

    @ApiModelProperty("任务开始时间")
    private Date startDate;

    @ApiModelProperty("是否已发布")
    private Integer isPublished;

    private List<TaskRecordDTO> taskRecords;
}
