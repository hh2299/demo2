package com.example.demo.common.mp.util;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.demo.common.util.StringUtil;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 直接运行 输入 core user finance sysytem 即可
 *
 * @create: 2019-10-16 10:14
 **/
public class CodeGenerator {
    /**
     * 父项目地址
     */
    public static String PROJECT_PATH = System.getProperty("user.dir");

    public static String SUB_PATH = "";
    /**
     * 包路径
     */
    public static String PACKAGE_PATH = "com.lytc.domain";
    /**
     * 包路径
     */
    public static String MODULE_NAME;
    /**
     * 实体类父类
     */
    public static String SUPER_ENTITY_CLASS = "com.lytc.component.common.entity.BaseEntity";
    /**
     * 作者
     */
    public static String AUTHOR = "DaleShay";
    /**
     * 数据库DRIVER_NAME
     */
    public static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    /**
     * 数据库URL
     */
    public static String DRIVER_URL = "122.51.93.195:3306";

    /**
     * 数据库URL
     */
    public static String DATABASE_NAME;

    /**
     * 数据库完整路径  如果有值 优先于 DRIVER_URL
     */
    public static String DRIVER_FULL_PATH = null;
    /**
     * 数据库账号
     */
    public static String USERNAME = "root";
    /**
     * 数据库密码
     */
    public static String PASSWORD = "dfjhf123!@#";

    /**
     * 要生成的表 如果不传入 则为所有表
     */
    private static String[] GENERATOR_TABLES = {};


    private static MySqlTypeConvert CONVERT = new DefaultConvert();

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入域服务" + tip + "：");
        // System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            //TODO
//            if (StringUtil.isNotNull(ipt)) {
//                return ipt;
//            }
            return ipt;
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void setProperties(GeneratorProperties properties) {

        if (StringUtil.isNotNull(properties.getProjectPath())) {
            PROJECT_PATH = properties.getProjectPath();
        }

        if (StringUtil.isNotNull(properties.getSubPath())) {
            SUB_PATH = properties.getSubPath();
        }

        if (StringUtil.isNotNull(properties.getPackagePath())) {
            PACKAGE_PATH = properties.getPackagePath();
        }

        if (StringUtil.isNotNull(properties.getDriverUrl())) {
            DRIVER_URL = properties.getDriverUrl();
        }

        if (StringUtil.isNotNull(properties.getDataBaseName())) {
            DATABASE_NAME = properties.getDataBaseName();
        }

        if(StringUtil.isNotNull(properties.getDriverFullPath())) {
            DRIVER_FULL_PATH = properties.getDriverFullPath();
        }

        if (StringUtil.isNotNull(properties.getUsername())) {
            USERNAME = properties.getUsername();
        }

        if (StringUtil.isNotNull(properties.getPassword())) {
            PASSWORD = properties.getPassword();
        }

        if (StringUtil.isNotNull(properties.getDriverName())) {
            DRIVER_NAME = properties.getDriverName();
        }

        if (StringUtil.isNotNull(properties.getAuthor())) {
            AUTHOR = properties.getAuthor();
        }

        if(properties.getConvert() != null) {
            CONVERT = properties.getConvert();
        }


        if (StringUtil.isNotNull(properties.getSuperClass())) {
            SUPER_ENTITY_CLASS = properties.getSuperClass();
        }


        if (StringUtil.isNotNull(properties.getModuleName())) {
            MODULE_NAME = properties.getModuleName();
        }

        if (!ObjectUtils.isEmpty(properties.getTables())) {
            GENERATOR_TABLES = properties.getTables();
        }

    }

    /**
     * 生成代码
     */
    public static void generator(GeneratorProperties properties) {

        setProperties(properties);
        // 全局配置
        GlobalConfig globalConfig = getGlobalConfig();
        // 数据源配置
        DataSourceConfig dataSourceConfig = getDataSourceConfig();

        // 包名配置
        PackageConfig packageConfig = getPackageConfig();

        // 策略配置
        StrategyConfig strategyConfig = getStrategyConfig();

        // 自定义配置
        InjectionConfig injectionConfig = getInjectionConfig();

        //自动生成
        atuoGenerator(dataSourceConfig, strategyConfig, globalConfig, packageConfig, injectionConfig);

    }


//    public static void main(String[] args) {
//
//        // 全局配置
//        GlobalConfig globalConfig = getGlobalConfig();
//        // 数据源配置
//        DataSourceConfig dataSourceConfig = getDataSourceConfig();
//        // 包名配置
//        PackageConfig packageConfig = getPackageConfig();
//        // 策略配置
//        StrategyConfig strategyConfig = getStrategyConfig();
//
//        // 自定义配置
//        InjectionConfig injectionConfig = getInjectionConfig();
//
//        //自动生成
//        atuoGenerator(dataSourceConfig, strategyConfig, globalConfig, packageConfig,injectionConfig);
//    }

    private static void atuoGenerator(DataSourceConfig dataSourceConfig, StrategyConfig strategyConfig, GlobalConfig globalConfig, PackageConfig packageConfig, InjectionConfig injectionConfig) {
        // 代码生成器
        AutoGenerator autoGenerator = new MyGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setStrategy(strategyConfig)
                .setTemplate(new TemplateConfig().setController(null))
                // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
                .setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }

    private static GlobalConfig getGlobalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(PROJECT_PATH +SUB_PATH+ "/src/main/java")
                .setAuthor(AUTHOR)
                .setOpen(false)
                .setSwagger2(true)
                .setFileOverride(true);
        return globalConfig;
    }

    private static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(driverProjectName())
                .setTypeConvert(CONVERT)
                .setDriverName(DRIVER_NAME)
                .setUsername(USERNAME)
                .setPassword(PASSWORD);
        return dataSourceConfig;
    }

    private static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();

        String moduleName = "";
        if(StringUtil.isNotNull(MODULE_NAME)) {
            moduleName =MODULE_NAME + ".orm";
        }else{
            moduleName = "orm";
        }

        packageConfig.setParent(PACKAGE_PATH)
                .setModuleName(moduleName);
        return packageConfig;
    }

    private static StrategyConfig getStrategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setSuperEntityClass(SUPER_ENTITY_CLASS)

                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setControllerMappingHyphenStyle(true)
                .setLogicDeleteFieldName("deleted")
//                .setExclude("undo_log")
//                .setVersionFieldName("version")
                .setSuperEntityColumns("id", "deleted", "create_time", "update_time", "create_by", "update_by", "version");

        if (!ObjectUtils.isEmpty(GENERATOR_TABLES)) {
            strategyConfig.setInclude(GENERATOR_TABLES);
        }else{
            strategyConfig.setExclude("undo_log");
        }

        return strategyConfig;
    }

    private static InjectionConfig getInjectionConfig() {
        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List <FileOutConfig> focList = new ArrayList <>();
        // 自定义配置会被优先输出
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//                return PROJECT_PATH + subProjectName() + "/src/main/resources/mapper/" + MODULE_NAME
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });

        injectionConfig.setFileOutConfigList(focList);
        return injectionConfig;
    }


    private static String driverProjectName() {

        if(StringUtil.isNotNull(DRIVER_FULL_PATH)) {
            return DRIVER_FULL_PATH;
        }else{
            return "jdbc:mysql://" + DRIVER_URL + "/" + DATABASE_NAME + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
        }
        

    }
}