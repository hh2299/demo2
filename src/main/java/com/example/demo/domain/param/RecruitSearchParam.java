package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class RecruitSearchParam extends BaseSearchParam {

    @ApiModelProperty("职位名称")
    private String positionName;

    @ApiModelProperty("是否发布")
    private Integer isPublished;

    @ApiModelProperty("是否结束")
    private Integer isFinished;
}
