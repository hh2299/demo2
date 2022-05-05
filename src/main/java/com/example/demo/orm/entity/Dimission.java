package com.example.demo.orm.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2022-05-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Dimission对象", description="")
public class Dimission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long companyId;

    private Long applicantId;

    private String applicantName;

    private Date submitDate;

    private String reason;

    @TableField("isAgreed")
    private Integer isAgreed;


}
