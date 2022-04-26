package com.example.demo.common.mp.util;

import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import lombok.Data;

/**
 * @Author: DaleShay
 * @Date: 2020/6/10 17:28
 * @Description:
 */
@Data
public class GeneratorProperties {

    /**
     * 父项目地址
     */
    public String projectPath;

    /**
     * 子目录地址 如果是更目录 则不需要
     */
    public String subPath;

    /**
     * 包路径
     */
    public String packagePath;
    /**
     * 包路径
     */
    public String moduleName;
    /**
     * 实体类父类
     */
    public String superClass;
    /**
     * 作者
     */
    public String author;
    /**
     * 数据库DRIVER_NAME
     */
    public String driverName;
    /**
     * 数据库URL
     */
    public String driverUrl;
    /**
     * 库名
     */
    public String dataBaseName;

    /**
     * 数据库URL 如果有值 优先于 driverUrl
     */
    public String driverFullPath;
    /**
     * 数据库账号
     */
    public String username;
    /**
     * 数据库密码
     */
    public String password;

    private String[] tables;

    private MySqlTypeConvert convert = new DefaultConvert();
}
