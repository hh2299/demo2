package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import com.example.demo.orm.entity.Recruit;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class HireDTO extends BaseDTO {

   @ApiModelProperty("公司id")
   private Long companyId;
   @ApiModelProperty(" 应聘人id")
   private Long applicantId;
   @ApiModelProperty("招聘id")
   private Long recruitId;
   @ApiModelProperty("雇佣时间")
   private Date hireDate;

}
