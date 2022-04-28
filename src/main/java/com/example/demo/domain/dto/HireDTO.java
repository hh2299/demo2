package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import com.example.demo.orm.entity.Recruit;
import lombok.Data;

@Data
public class HireDTO extends BaseDTO {

   private ApplicantDTO applicant;
   private RecruitDTO recruit;
}
