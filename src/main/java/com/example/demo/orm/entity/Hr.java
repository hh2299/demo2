package com.example.demo.orm.entity;

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
 * @since 2022-04-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Hr对象", description="")
public class Hr extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;

    private String info;

    private Long companyId;


}
