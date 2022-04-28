package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class PerformanceSearchParam extends BaseSearchParam {

    private static final long serialVersionUID = -3310802970640607669L;

    @ApiModelProperty("所属履历")
    private Long applicantCvId;

    private Date month;

    private Date year;
}
