package com.example.demo.common.mp.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import com.example.demo.common.util.DateUtil;
import com.example.demo.common.util.UserUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * 填充器
 *
 * @author: DaleShay
 * @create: 2019-10-16 17:10
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Long DEFAULT_USER_ID = 9999L;

    @Override
    public void insertFill(MetaObject metaObject) {
        Timestamp date = DateUtil.getCurrentDate();
        Long currentUserId = UserUtils.getCurrentUserIdOfLong();
        if (currentUserId != null) {
            this.setFieldValByName("createBy", currentUserId, metaObject);
            this.setFieldValByName("updateBy", currentUserId, metaObject);
        } else {
            this.setFieldValByName("createBy", DEFAULT_USER_ID, metaObject);
            this.setFieldValByName("updateBy", DEFAULT_USER_ID, metaObject);
        }
        this.setFieldValByName("createTime", date, metaObject);
        this.setFieldValByName("updateTime", date, metaObject);
        this.setFieldValByName("deleted", 0, metaObject);
        this.setFieldValByName("version", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long currentUserId = UserUtils.getCurrentUserIdOfLong();
        Timestamp date = DateUtil.getCurrentDate();
        if (currentUserId != null) {
            this.setFieldValByName("updateBy", currentUserId, metaObject);
        } else {
            this.setFieldValByName("updateBy", DEFAULT_USER_ID, metaObject);
        }
        this.setFieldValByName("updateTime", date, metaObject);
    }
}
