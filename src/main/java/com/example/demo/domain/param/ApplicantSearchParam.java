package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ApplicantSearchParam extends BaseSearchParam {

    private static final long serialVersionUID = 3004865261949721261L;

    @ApiModelProperty("姓名：模糊查询")
    private String name;

    @ApiModelProperty("性别：MALE/FEMALE")
    private String sex;

    @ApiModelProperty("民族")
    private String nation;

    @ApiModelProperty("籍贯：模糊查询")
    private String origin;

    @ApiModelProperty("教育程度：模糊查询")
    private String education;

    @ApiModelProperty("毕业学院：模糊查询")
    private String graduateSchool;

    @ApiModelProperty("有无重大疾病：0/1")
    private Integer isMagorIllness;

    @ApiModelProperty(value = "意向工作id")
    private Long intentionPositionId;

    private String intentionPosition;

    private Integer isHired;
}
