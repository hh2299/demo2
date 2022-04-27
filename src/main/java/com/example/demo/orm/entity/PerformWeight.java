package com.example.demo.orm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2022-04-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("perform_weight")
@ApiModel(value="PerformWeight对象", description="")
public class PerformWeight extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer learn;

    private Integer liability;

    private Integer social;

    private Integer expertise;

    private Integer competitive;

    private Integer schedule;

    private Integer computer;

    private Integer manage;


}
