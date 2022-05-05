package com.example.demo.orm.entity;

import com.example.demo.common.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台管理员
 * </p>
 *
 * @author jobob
 * @since 2022-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Admin对象", description="后台管理员")
public class Admin extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String uid;

    private String username;

    private String password;


}
