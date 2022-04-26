package com.example.demo.common.vo;

import lombok.Data;

/**
 * @Author: DaleShay
 * @Date: 2021/6/18 09:21
 * @Description:
 */
@Data
public class UnitPeriodBO {

    public UnitPeriodBO(Integer periodCount, Integer monthCount, Integer dayCount) {
        this.periodCount = periodCount;
        this.monthCount = monthCount;
        this.dayCount = dayCount;
    }

    private Integer periodCount = 0;

    private Integer monthCount = 0;

    private Integer dayCount = 0;
}
