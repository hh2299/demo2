package com.example.demo.common.exception;

import com.example.demo.common.vo.BaseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: DaleShay
 * @Date: 2020/2/26 12:51
 * @Description: controller层 全局异常拦截
 */
@Slf4j
@ControllerAdvice
public class GolbalExceptionHandler {

    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public BaseModel handleBusinessError(MyException e) {
        e.printStackTrace();
        log.warn("业务异常：{}", e.getMessage());
        return BaseModel.buildError(e);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseModel handleError(Exception e) {
        e.printStackTrace();
        log.warn("系统异常：{}", e.getMessage());
        return BaseModel.buildError("系统异常");
    }
}
