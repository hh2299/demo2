package com.example.demo.domain.vo;

import com.example.demo.domain.dto.DimissionDTO;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class DimissionVo extends DimissionDTO {

    private String submitDateStr;

    public String getSubmitDateStr() {
        SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm " );
        String dateString = formatter.format(getSubmitDate());
        return dateString;
    }
}
