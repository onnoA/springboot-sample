package com.onnoa.springboot.mybatis.plus.multidatasourceconfig;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/10/30 11:14
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return  DataSourceContextHolder.getDataSource();
    }
}
