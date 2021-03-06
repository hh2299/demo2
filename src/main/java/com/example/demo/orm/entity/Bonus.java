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
 * @since 2022-04-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Bonus对象", description="")
public class Bonus extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Long applicantId;

    private String applicant;

    private BigDecimal amount;

    private BigDecimal realAmount;

    private BigDecimal tax;

    private Date payDate;

    private Integer isFinished;


}
