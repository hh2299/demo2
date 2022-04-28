package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
public class CompanySearchParam extends BaseSearchParam {

    @ApiModelProperty("公司名称：模糊查找")
    private String name;

}
