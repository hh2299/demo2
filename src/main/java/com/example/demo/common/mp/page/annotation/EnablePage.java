package com.example.demo.common.mp.page.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnablePage {
    /**
     * 描述
     */
    String description() default "参数中getPage不为空时进行分页";
}
