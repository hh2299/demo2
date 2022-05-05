package com.example.demo.domain.vo;

import com.example.demo.domain.dto.LeaveDTO;
import lombok.Data;

import java.text.SimpleDateFormat;

@Data
public class LeaveVo extends LeaveDTO {

    private String startTimeStr;

    private String endTimeStr;

    public String getStartTimeStr() {
        SimpleDateFormat formatter = new SimpleDateFormat( "HH:mm" );
        String dateString = formatter.format(getStartTime());
        return dateString;
    }

    public String getEndTimeStr() {
        SimpleDateFormat formatter = new SimpleDateFormat( "HH:mm" );
        String dateString = formatter.format(getEndTime());
        return dateString;
    }
}
