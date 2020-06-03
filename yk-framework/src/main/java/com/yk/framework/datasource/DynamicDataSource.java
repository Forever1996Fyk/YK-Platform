package com.yk.framework.datasource;

import com.yk.common.config.DynamicDataSourceContextConfig;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @program: YK-Platform
 * @description: 动态数据源
 * @author: YuKai Fan
 * @create: 2020-06-03 09:29
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextConfig.getDataSourceType();
    }
}