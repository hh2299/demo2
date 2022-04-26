package com.example.demo.common.vo;


import com.example.demo.common.constant.Constants;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.example.demo.common.exception.MyException;
import lombok.Data;

import java.io.Serializable;


/**
 * <p>
 * model基类。
 * </p>
 *
 * @param <T>
 * @author 甘 <br />
 * 履历 <br />
 * 2016/01/27 : 甘: 新建<br />
 * @version 1.0
 */
@Data
public class BaseModel<T> implements Serializable {


    private static final long serialVersionUID = 8868163258271605343L;
    private String status = Constants.SUCCESS;

    private String message = "操作成功";

    private static final String errorMessage = "操作失败";

    private T data;

    private PageModel page;

    public PageModel getPage() {
        return page;
    }

    public void setPage(PageInfo<?> pageInfo) {
        this.page = new PageModel(pageInfo);
    }

    public static <T> BaseModel<T> buildSuccess() {
        String successMessage = "操作成功";
        return build(null, Constants.SUCCESS, successMessage, null);
    }


    public static <T> BaseModel<T> buildSuccess(T data) {

        String successMessage = "操作成功";

        PageInfo<T> pageInfo = null;
        if (data instanceof Page) {
            pageInfo = new PageInfo((Page) data);
        }

        if (data instanceof com.baomidou.mybatisplus.core.metadata.IPage) {
            com.baomidou.mybatisplus.core.metadata.IPage p = (com.baomidou.mybatisplus.core.metadata.IPage) data;
            pageInfo = new PageInfo<>();
            pageInfo.setTotal(p.getTotal());
            pageInfo.setPageNum((int) p.getCurrent());
            pageInfo.setSize((int) p.getSize());
            pageInfo.setPages((int) p.getPages());
            //TODO
            data = (T) p.getRecords();
        }

        return build(data, Constants.SUCCESS, successMessage, pageInfo);
    }


    public static <T> BaseModel<T> buildError() {
        return build(null, Constants.FAIL_BUSINESS_ERROR, errorMessage, null);
    }

    public static <T> BaseModel<T> buildError(String errorMessage) {
        return build(null, Constants.FAIL_BUSINESS_ERROR, errorMessage, null);
    }

    public static <T> BaseModel<T> buildError(MyException e) {
        String errorMessage = e.getMessage();
        return build(null, Constants.FAIL_BUSINESS_ERROR, errorMessage, null);
    }

    public static <T> BaseModel<T> buildError(String status, String message) {
        return build(null, status, message, null);
    }


    private static <T> BaseModel<T> build(T data, String status, String message, PageInfo<?> pageInfo) {
        BaseModel<T> result = new BaseModel<>();
        result.setData(data);
        result.setStatus(status);
        result.setMessage(message);
        if (pageInfo != null) {
            result.setPage(pageInfo);
        }

        return result;
    }
}
