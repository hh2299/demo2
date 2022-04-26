package com.example.demo.domain.param;

import com.example.demo.common.param.BaseSearchParam;
import lombok.Data;

@Data
public class CompanySearchParam extends BaseSearchParam {

    private String name;

}
