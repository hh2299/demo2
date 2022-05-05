package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskSearchParam extends BaseSearchParam {


    private static final long serialVersionUID = 1976013164786044420L;
    @ApiModelProperty("公司id")
    private Long companyId;
}
