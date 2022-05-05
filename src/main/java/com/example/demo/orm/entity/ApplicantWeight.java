package com.example.demo.orm.entity;

import com.example.demo.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 应聘人（员工）-》权重关系表
 * </p>
 *
 * @author jobob
 * @since 2022-05-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="ApplicantWeight对象", description="应聘人（员工）-》权重关系表")
public class ApplicantWeight extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long applicantId;

    private Long weightId;


}
