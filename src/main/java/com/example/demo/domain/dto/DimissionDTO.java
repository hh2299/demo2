package com.example.demo.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.example.demo.common.entity.BaseDTO;
import lombok.Data;

import java.util.Date;

@Data
public class DimissionDTO extends BaseDTO {

    private Long companyId;

    private Long applicantId;

    private String applicantName;

    private Date submitDate;

    private String reason;

    @TableField("isAgreed")
    private Integer isAgreed;
}
