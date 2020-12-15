package com.nzkj.screen.Entity.enums;

import lombok.Getter;

/** @Description 数据源类型 @Author gdq @Date 2020/7/31 14:24 @Version 1.0 */
public enum DataSourceEnum {
  PILE_MEMBER("member", "会员数据库"),
  PILE_CONFIG("config", "配置数据库"),
  PILE_BILL("bill", "账单数据库"),
  PILE_SCREEN("screen", "大屏数据库");
  @Getter private String name;
  @Getter private String desc;

  DataSourceEnum(String name, String desc) {
    this.name = name;
    this.desc = desc;
  }
}
