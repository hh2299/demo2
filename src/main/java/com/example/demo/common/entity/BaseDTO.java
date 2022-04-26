package com.example.demo.common.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: DaleShay
 * @Date: 2020/6/12 11:26
 * @Description:
 */
@Data
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 5743082712938822056L;

    @ApiModelProperty(value = "物理主键")
    protected Long id;

    @ApiModelProperty(value = "是否删除")
    private Integer deleted;


    @ApiModelProperty(value = "创建时间")
    private Timestamp createTime;

    @ApiModelProperty(value = "创建人")
    private Long createBy;

    @ApiModelProperty(value = "更新时间")
    private Timestamp updateTime;

    @ApiModelProperty(value = "更新人")
    private Long updateBy;
}
