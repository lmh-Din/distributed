package com.distributed.seata.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Description
 * @ClassName MultiDataSourceConfig
 * @Author Liumh
 * @Date 2019/6/11 17:00
 * @Version v1.0
 */
@Slf4j
@Configuration
public class MultiDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "storage.datasource")
    public DataSourceProperties storageDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource storageDataSource(){
        DataSourceProperties dataSourceProperties = storageDataSourceProperties();
        log.info("storageDataSource URL:[{}]", dataSourceProperties.getUrl());
        DataSourceBuilder<?> builder = dataSourceProperties.initializeDataSourceBuilder();
        DataSource dataSource = builder.build();
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "account.datasource")
    public DataSourceProperties accountDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource accountDataSource(){
        DataSourceProperties dataSourceProperties = accountDataSourceProperties();
        log.info("accountDataSource properties:[{}]", dataSourceProperties);
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "order.datasource")
    public DataSourceProperties orderDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource orderDataSource(){
        DataSourceProperties dataSourceProperties = orderDataSourceProperties();
        log.info("orderDataSource properties:[{}]", dataSourceProperties);
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }
}
