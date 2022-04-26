package com.example.demo.common.mp.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.example.demo.common.mp.handler.MyMetaObjectHandler;
import com.example.demo.common.mp.page.EnablePageAspect;
import com.github.pagehelper.PageInterceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Properties;


/**
 * mybatis plus 配置类
 *
 * @author: DaleShay
 * @create: 2019-10-29 15:38
 **/
@Configuration
@Import({MyMetaObjectHandler.class})
public class MyBatisPlusConfiguration {
    /**
     * 乐观锁
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }



    /**
     * 分页插件
     * @return
     */
    @Bean
    public PageInterceptor paginationInterceptor() {
        PageInterceptor pageHelper = new PageInterceptor();
        Properties p = new Properties();
        p.put("offsetAsPageNum", "true");
        p.put("rowBoundsWithCount", "true");
        p.put("reasonable", "true");
        p.put("helperDialect","com.example.demo.common.mp.dialect.MysqlDialect");
        pageHelper.setProperties(p);
        return pageHelper;
    }

    @Bean
    public EnablePageAspect enablePageAspect(){
        return new EnablePageAspect();
    }

}
