package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TaskRecordDTO extends BaseDTO {

    private static final long serialVersionUID = -6272879838340012041L;

    @ApiModelProperty("任务id")
    private Long taskId;

    @ApiModelProperty("记录日期")
    private Date recordDate;

    @ApiModelProperty("记录内容")
    private String recordContent;
}
