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
@ApiModel(value="ApplicantCv对象", description="")
public class ApplicantCv extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long applicantId;

    private Long companyId;

    private String company;

    private Long positionId;

    private String position;

    private Date startDate;

    private Date endDate;


}
