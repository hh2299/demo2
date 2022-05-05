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
 * @since 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Salary对象", description="")
public class Salary extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long applicantId;

    private String applicant;

    private BigDecimal payableWage;

    private BigDecimal realWage;

    private BigDecimal insurancePay;

    private BigDecimal tax;

    private Date payDate;

    private Integer isFinished;

    private Integer setup;


}
