package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import lombok.Data;

@Data
public class ChangePositionDTO extends BaseDTO {

    private Long applicantId;

    private Long oldPositionId;

    private Long newPositionId;

    private String newPositionName;

    private PerformWeightDTO performWeight;


}
