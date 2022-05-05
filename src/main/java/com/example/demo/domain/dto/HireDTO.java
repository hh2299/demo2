package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import com.example.demo.orm.entity.Recruit;
import lombok.Data;

import java.util.Date;

@Data
public class HireDTO extends BaseDTO {

   private Long companyId;
   private Long applicantId;
   private Long recruitId;

   private Date hireDate;

}
