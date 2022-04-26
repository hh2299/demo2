package com.example.demo.common.mp.util;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.util.List;

/**
 * @Author: DaleShay
 * @Date: 2020/8/5 18:36
 * @Description:
 */
public class MyGenerator extends AutoGenerator {

    /**
     * 解决换行问题
     * @param config
     * @return
     */
    @Override
    protected List<TableInfo> getAllTableInfoList(ConfigBuilder config) {
        List<TableInfo> tableInfos =  super.getAllTableInfoList(config);
        tableInfos.forEach(t->{
            t.getFields().forEach(f->{
                if(!StringUtils.isEmpty(f.getComment())) {
                    String comment = f.getComment();
                    //注意，替换换行符
                    comment = comment.replaceAll("\r\n" , "");
                    comment = comment.replaceAll("\"" , "'");
                    f.setComment(comment);
                }
            });
        });
        return tableInfos;
    }
}
