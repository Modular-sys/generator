package xyz.zenheart.generator.datasource.config;


import com.zaxxer.hikari.HikariDataSource;
import xyz.zenheart.generator.datasource.properties.DataSourceProperties;

import java.sql.SQLException;

/**
 * HikariDataSource
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public class DynamicDataSourceFactory {

    public static HikariDataSource buildHikariDataSource(DataSourceProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setDriverClassName("");

        dataSource.setMaximumPoolSize(5);
        dataSource.setMinimumIdle(2);
        dataSource.setMaxLifetime(1000L);
        return dataSource;
    }
}