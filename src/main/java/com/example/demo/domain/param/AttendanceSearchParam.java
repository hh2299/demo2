package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AttendanceSearchParam extends BaseSearchParam {

    private static final long serialVersionUID = -4652977891419747227L;

    @ApiModelProperty("打卡日期")
    private Date signDate;
}
