package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import lombok.Data;

@Data
public class PositionDTO extends BaseDTO {

    private static final long serialVersionUID = -2222090157308305887L;

    private Long companyId;

    private String position;
}
