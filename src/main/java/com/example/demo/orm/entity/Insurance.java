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
@ApiModel(value="Insurance对象", description="")
public class Insurance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long applicantId;

    private String applicant;

    private BigDecimal endowment;

    private BigDecimal medical;

    private BigDecimal unemployment;

    private BigDecimal inductrialInjury;

    private BigDecimal fertility;

    private BigDecimal housingFund;

    private Date payDate;


}
