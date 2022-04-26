package com.example.demo.common.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: DaleShay
 * @Date: 2020/6/11 18:39
 * @Description:
 */
@Data
public class BaseSearchParam implements Serializable {

    private static final long serialVersionUID = 1500479591865674626L;

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("一页的数量")
    private Integer size;

    @ApiModelProperty("公共查找条件，多个字段统一查询字段")
    private String searchField;

    @ApiModelProperty("排序字段")
    private String sortField;

    @ApiModelProperty("是否倒序")
    private Boolean isDesc = true;

    @ApiModelProperty("项目ID")
    private List<Long> projectIds;

    @ApiModelProperty("楼栋ID")
    private List<Long> buildingIds;

    @ApiModelProperty("楼栋ID")
    private Long buildingId;

    private String levelName;

    private String industryName;
}
