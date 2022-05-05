package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class LeaveStaticDTO extends BaseDTO {

    private static final long serialVersionUID = 8483528625939924308L;

    private String name;

    private Integer leaveCount;

    private Integer sickCount;

    private Integer affairsCount;

    private Integer leaveRate;
}
