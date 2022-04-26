package com.example.demo.common.enums;

/**
 * @Author: DaleShay
 * @Date: 2020/6/30 09:29
 * @Description: 审核枚举
 */
public enum AuditEnum implements ConstantsEnum {
    /**
     * 审核不通过
     */
    REJECT("拒绝"),
    /**
     * 审核通过
     */
    AGREE("同意");


    private String desc;

    AuditEnum(String desc) {
        this.desc = desc;
    }

    public Boolean isStrEqual(String val) {
        return this.toString().equals(val);
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
