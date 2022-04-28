package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import lombok.Data;

@Data
public class PositionSearchParam extends BaseSearchParam {

    private Long companyId;

    private String name;

}
