package xyz.zenheart.generator.datasource.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import xyz.zenheart.generator.datasource.properties.DataSourceProperties;
import xyz.zenheart.generator.datasource.properties.DynamicDataSourceProperties;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
//@Configuration
//@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DynamicDataSourceConfig {
    @Resource
    private DynamicDataSourceProperties properties;

    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DynamicDataSource dynamicDataSource(DataSourceProperties dataSourceProperties) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(getDynamicDataSource());

        //默认数据源
        HikariDataSource defaultDataSource = DynamicDataSourceFactory.buildHikariDataSource(dataSourceProperties);
        dynamicDataSource.setDefaultTargetDataSource(defaultDataSource);

        return dynamicDataSource;
    }

    private Map<Object, Object> getDynamicDataSource() {
        Map<String, DataSourceProperties> dataSourcePropertiesMap = properties.getDatasource();
        Map<Object, Object> targetDataSources = new HashMap<>(dataSourcePropertiesMap.size());
        dataSourcePropertiesMap.forEach((k, v) -> {
            HikariDataSource HikariDataSource = DynamicDataSourceFactory.buildHikariDataSource(v);
            targetDataSources.put(k, HikariDataSource);
        });

        return targetDataSources;
    }

}