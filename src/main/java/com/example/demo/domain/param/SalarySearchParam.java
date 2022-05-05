package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SalarySearchParam extends BaseSearchParam {


    @ApiModelProperty("查找月份")
    private Date searchMonth;

}
