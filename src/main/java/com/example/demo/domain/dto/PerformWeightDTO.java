package com.example.demo.domain.dto;

import com.example.demo.common.entity.BaseDTO;
import com.example.demo.common.exception.MyException;
import lombok.Data;

@Data
public class PerformWeightDTO extends BaseDTO {

    private static final long serialVersionUID = -6967531800002373118L;

    private Integer learn;

    private Integer liability;

    private Integer social;

    private Integer expertise;

    private Integer competitive;

    private Integer schedule;

    private Integer computer;

    private Integer manage;

    public Integer getWeightSum() {
        Integer result;
        try {
            result = learn + liability + social + expertise + competitive + schedule + computer + manage;
        } catch (NullPointerException e) {
            throw new MyException("请完善权重信息");
        }
        return result;
    }


}

