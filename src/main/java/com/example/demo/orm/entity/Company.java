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
 * @since 2022-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Company对象", description="")
public class Company extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String address;

    private String legalPerson;

    private String legalIcid;

    private String legalPhone;

    private String province;

    private String city;

    private String area;

    private String content;

    private String industryCategory;

    private Integer isAgreed;

    private Date joinDate;


}
