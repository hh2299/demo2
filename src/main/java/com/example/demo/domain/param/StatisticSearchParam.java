package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class StatisticSearchParam extends BaseSearchParam {

    private static final long serialVersionUID = 707658847074235975L;

    @ApiModelProperty("查询开始时间")
    private Date startDate;
    @ApiModelProperty("查询结束时间")
    private Date endDate;
}
