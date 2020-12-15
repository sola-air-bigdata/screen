package com.nzkj.screen.Componment;


import com.nzkj.screen.Entity.enums.DataSourceEnum;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
/** @Description 多数据源配置 @Author gdq @Date 2020/7/31 13:42 @Version 1.0 */
public class MultiDataSourceConfig {

  @Bean("memberDataSource")
  @ConfigurationProperties("spring.datasource.member.hikari")
  public DataSource dataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean("configDataSource")
  @ConfigurationProperties("spring.datasource.config.hikari")
  public DataSource configDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean("billDataSource")
  @ConfigurationProperties("spring.datasource.bill.hikari")
  public DataSource billDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  @Bean("screenDataSource")
  @ConfigurationProperties("spring.datasource.screen.hikari")
  public DataSource screenDataSource() {
    return DataSourceBuilder.create().type(HikariDataSource.class).build();
  }

  /**
   * 动态数据源配置
   *
   * @return
   */
  @Bean
  @Primary
  public DataSource multipleDataSource(
      @Qualifier("memberDataSource") DataSource memberDataSource,
      @Qualifier("configDataSource") DataSource configDataSource,
      @Qualifier("billDataSource") DataSource billDataSource,
      @Qualifier("screenDataSource") DataSource screenDataSource) {
    DynamicDataSource dynamicDataSource = new DynamicDataSource();
    Map<Object, Object> targetDataSources = new HashMap<>();
    targetDataSources.put(DataSourceEnum.PILE_BILL.getName(), billDataSource);
    targetDataSources.put(DataSourceEnum.PILE_MEMBER.getName(), memberDataSource);
    targetDataSources.put(DataSourceEnum.PILE_CONFIG.getName(), configDataSource);
    targetDataSources.put(DataSourceEnum.PILE_SCREEN.getName(), screenDataSource);
    dynamicDataSource.setTargetDataSources(targetDataSources);
    dynamicDataSource.setDefaultTargetDataSource(memberDataSource);
    return dynamicDataSource;
  }
}
