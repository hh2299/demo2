package com.example.demo.orm.entity;

import java.util.Date;
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
 * @since 2022-04-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Performance对象", description="")
public class Performance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long applicantCvId;

    private Date month;

    private String leaderEval;

    private String selfEval;

    @ApiModelProperty(value = "综合评级")
    private String level;

    private Integer learn;

    private Integer liability;

    private Integer social;

    private Integer expertise;

    private Integer competitive;

    private Integer schedule;

    private Integer computer;

    private Integer manage;


}
