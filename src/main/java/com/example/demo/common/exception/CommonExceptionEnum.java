package com.example.demo.common.exception;

/**
 * @Author: DaleShay
 * @Date: 2020/2/15 04:12
 * @Description:
 */
public enum  CommonExceptionEnum implements IExceptionEnum {


    /**
     * 系统异常
     */
    SYSTEM_ERROR("000000 ", "系统异常，请联系管理员！");

    private String code;
    private String message;
    /**
     * 构造方法
     */
    CommonExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
