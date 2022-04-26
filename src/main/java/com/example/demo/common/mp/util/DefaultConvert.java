package com.example.demo.common.mp.util;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * @Author: DaleShay
 * @Date: 2019/10/24 17:22
 * @Description:
 */
public class DefaultConvert extends MySqlTypeConvert {

    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {

        if(fieldType.equals("datetime")) {
            return DbColumnType.DATE;
        }

        if(fieldType.equals("date")) {
            return DbColumnType.DATE;
        }

        return super.processTypeConvert(globalConfig,fieldType);
    }


}
