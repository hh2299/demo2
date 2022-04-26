package com.example.demo.common.enums;

/**
 * @Author: DaleShay
 * @Date: 2020/6/17 14:29
 * @Description:
 */
public enum  DocEnum {
    /**
     * ,"主图"
     */
    MI("MI"),
    /**
     * "列表图"
     */
    DI("DI"),
    /**
     * 文档
     */
    DOC("DOC"),

    /**
     * logo
     */
    LOGO("LOGO");

    String code;

    String desc;

    DocEnum(String code) {
        this.code = code;
    }

}
