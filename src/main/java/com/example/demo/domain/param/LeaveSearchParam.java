package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
public class LeaveSearchParam extends BaseSearchParam {

    private Long applicantId;
    private Date startDate;
    private Date endDate;

    private Integer isAgreed;
}
