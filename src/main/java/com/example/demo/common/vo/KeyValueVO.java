package com.example.demo.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: DaleShay
 * @Date: 2020/9/14 09:04
 * @Description:
 */
@Data
public class KeyValueVO implements Serializable {

    private String key;

    private String value;
}
