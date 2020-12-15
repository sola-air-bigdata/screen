package com.nzkj.screen.Componment;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/** @Description 动态数据源路由类 @Author gdq @Date 2020/7/31 14:16 @Version 1.0 */
public class DynamicDataSource extends AbstractRoutingDataSource {
  @Override
  protected Object determineCurrentLookupKey() {
    return DataSourceContext.getCurrent();
  }
}
