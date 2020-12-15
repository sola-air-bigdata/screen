package com.nzkj.screen.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/** @Description 枪类 @Author fqg @Date 2020/5/11 15:25 @Version 1.0 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Gun extends Entity {
  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  private Long id;

  /**
   * 枪号
   */
  private String number;

  /**
   * 桩
   */
  private Long pileId;

  //connetctorId
  public String connectorId;

  //枪名称
  private String name;

}
