package com.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * User: lanxinghua
 * Date: 2019/10/25 18:33
 * Desc: MyBatis配置类
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.mall.dao", "com.mall.mapper"})
public class MyBatisConfig {
}
