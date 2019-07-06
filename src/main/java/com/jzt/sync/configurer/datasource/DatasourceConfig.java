/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: DatasourceConfig
 * Author:   nick
 * Date:     2019/7/6 11:29
 * Description: 定义数据库实体类并配置为多数据源的形式
 * History:
 */
package com.jzt.sync.configurer.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * 〈定义数据库实体类并配置为多数据源的形式〉
 *
 * @author nick
 * @create 2019/7/6
 * @since 1.0.0
 */
@Configuration
public class DatasourceConfig {
    //destroy-method="close"的作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
    @Bean(destroyMethod = "close", name = DataSources.MASTER_DB)
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(destroyMethod = "close", name = DataSources.SLAVE_DB)
    @ConfigurationProperties(prefix = "spring.datasource.cluster")
    public DataSource dataSourceSlave() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }
}