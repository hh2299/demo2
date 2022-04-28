package com.example.demo.domain.enums;

import com.example.demo.common.enums.ConstantsEnum;

public enum SexEnum implements ConstantsEnum {

    MALE("男"),
    FEMALE("女"),
    ;

    private String desc;

    SexEnum(String desc) {
        this.desc = desc;
    }


    @Override
    public String getDesc() {
        return desc;
    }
}
