package com.electronicpayment.televentas.config.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatasourceConfiguration {
    @Bean(name = "dbTeleventasDataSource")
    @ConfigurationProperties(prefix ="spring.datasource.televentas")
    public DataSource dbTeleventasHikariConfig() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate dbTeleventasJdbcTemplate(@Qualifier("dbTeleventasDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
