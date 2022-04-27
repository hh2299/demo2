package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecruitSearchParam extends BaseSearchParam {

    @ApiModelProperty("职位名称")
    private String name;

    private Long companyId;

}
