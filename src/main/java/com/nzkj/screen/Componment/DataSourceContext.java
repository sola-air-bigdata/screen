package com.nzkj.screen.Componment;


import com.nzkj.screen.Entity.enums.DataSourceEnum;

/** @Description 数据源上下文 @Author gdq @Date 2020/7/31 14:21 @Version 1.0 */
public class DataSourceContext {
  public static final ThreadLocal<String> DATASOURCE_CONTEXT = new ThreadLocal<>();

  public static void setCurrent(DataSourceEnum sourceEnum) {
    DATASOURCE_CONTEXT.set(sourceEnum.getName());
  }

  public static String getCurrent() {
    return DATASOURCE_CONTEXT.get();
  }

  public static void clear() {
    DATASOURCE_CONTEXT.remove();
  }
}
