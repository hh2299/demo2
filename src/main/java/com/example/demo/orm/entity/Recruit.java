package com.example.demo.orm.entity;

import java.math.BigDecimal;
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
@ApiModel(value="Recruit对象", description="")
public class Recruit extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long positionId;

    private String positionName;

    private Long companyId;

    private Integer num;

    private Date startDate;

    private Date endDate;

    private BigDecimal salary;

    private String requirements;

    @ApiModelProperty(value = "表现权重id")
    private Long weightId;

    private Integer isFinished;

    private String detail;


}
