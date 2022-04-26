package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class HrSearchParam extends BaseSearchParam {

    private static final long serialVersionUID = 8652347495431585051L;

    @ApiModelProperty("Hr姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("所属公司Id")
    private Long companyId;

    @ApiModelProperty("所属公司name")
    private String company;

}
