package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import lombok.Data;

@Data
public class AttendanceStatisticDTO extends BaseDTO {

    private static final long serialVersionUID = 4445986622569187265L;

    private String name;

    private Integer attendance;

    private Integer absenteeism;

    private Integer late;

    private Integer leave;

    private Integer signError;
}
