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
 * @since 2022-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Attendance对象", description="")
public class Attendance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long companyId;

    private Long applicantId;

    private String applicant;

    private Date signInTime;

    private String signInScope;

    private Date signOutTime;

    private String signOutScope;

    private String note;


}
