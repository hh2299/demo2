package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import lombok.Data;

import java.util.Date;

@Data
public class StatisticSearchParam extends BaseSearchParam {

    private static final long serialVersionUID = 707658847074235975L;

    private Date startDate;

    private Date endDate;
}
