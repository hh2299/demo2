package com.example.demo.domain.vo;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CompanyStatisticVo{


    private static final long serialVersionUID = 1696623838272815362L;

    @ApiModelProperty("根据行业的公司数")
    private List<CompanyCategoryStatisticVo> companyCategoryStatistic;

    @ApiModelProperty("今年的数量变化")
    private List<Integer> monthNum;

    @ApiModelProperty("企业位置分布")
    private List<CompanyCityStatisticVo> companyCityStatistic;

    @ApiModelProperty("企业位置分布")
    private List<CompanyScaleStatisticVo> companyScaleStatistic;
}
