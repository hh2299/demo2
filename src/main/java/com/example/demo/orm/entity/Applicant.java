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
@ApiModel(value="Applicant对象", description="")
public class Applicant extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String sex;

    private String nation;

    private Date birth;

    private String origin;

    private String education;

    private String graduateSchool;

    private Integer isMagorIllness;

    private String disciplinaryHistory;

    @ApiModelProperty(value = "意向工作")
    private Long intentionPositionId;

    private String intentionPosition;

    private String awardHistory;

    private String selfEvaluation;

    private Integer isHired;


}
