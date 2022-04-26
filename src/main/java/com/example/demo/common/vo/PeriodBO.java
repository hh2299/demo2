package com.example.demo.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: DaleShay
 * @Date: 2021/6/17 18:27
 * @Description:
 */
@Data
public class PeriodBO implements Serializable {

    private static final long serialVersionUID = -3531746887525213143L;
    private Date startDate;

    private Date endDate;

    private Boolean isFull;
}
