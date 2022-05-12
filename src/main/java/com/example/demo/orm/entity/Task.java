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
 * @since 2022-05-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Task对象", description="")
public class Task extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long companyId;

    private Long applicantId;

    private String applicant;

    private String content;

    private Integer isSummary;

    private String summary;

    private Date startDate;

    @ApiModelProperty(value = "要求完成时间")
    private Date endDate;

    private Integer isPublished;


}
