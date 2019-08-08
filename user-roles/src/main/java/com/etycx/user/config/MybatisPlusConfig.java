package com.etycx.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.etycx.user.config.properties.DruidProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author 武海升
 * @date  2019-08-07
 */
@Configuration
@EnableTransactionManagement(order = 1)
@MapperScan(basePackages = {"com.etycx.user.mapper"})
public class MybatisPlusConfig {

    @Autowired
    private DruidProperties druidProperties;

    /**
     * 数据源
     */
    private DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 单数据源连接池配置
     */
    @Bean
    public DruidDataSource singleDatasource() {
        return dataSource();
    }


}
