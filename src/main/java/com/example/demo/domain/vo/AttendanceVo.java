package com.example.demo.domain.vo;

import com.example.demo.domain.dto.AttendanceDTO;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class AttendanceVo extends AttendanceDTO {

    private static final long serialVersionUID = -7643992830353629514L;
    private String signInTimeStr;

    private String signOutTimeStr;

    public String getSignInTimeStr() {
        SimpleDateFormat formatter = new SimpleDateFormat( "HH:mm:ss" );
        String dateString = formatter.format(getSignInTime());
        return dateString;
    }

    public String getSignOutTimeStr() {
        SimpleDateFormat formatter = new SimpleDateFormat( "HH:mm:ss" );
        String dateString = formatter.format(getSignOutTime());
        return dateString;
    }
}
