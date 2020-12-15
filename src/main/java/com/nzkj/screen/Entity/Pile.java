package com.nzkj.screen.Entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/** @Description 桩类 @Author fqg @Date 2020/5/11 14:35 @Version 1.0 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Pile extends Entity {
  private static final long serialVersionUID = 1L;

  //商家id
  private Long sellerId;
  /**
   * 桩名称
   */
  private String name;
  /**
   * 桩体号
   */
  private String pileNum;

  /**
   * 站点id
   */
  private Long stationId;
  /**
   * 桩类型id
   */
  private Long typeId;


  private String equipmentownerid;// 设备所属方ID

  private String manufacturerid;// 设备生产商ID

/*
  private PileOperateTypeEnum pileOperateTypeEnum;
*/


  //关闭开始时间1
  private Integer closeStartOne;

  //关闭开始时间2
  private Integer closeStartTwo;

  //关闭开始时间3
  private Integer closeStartThree;

  //关闭开始时间4
  private Integer closeStartFour;

  //关闭开始时间5
  private Integer closeStartFive;

  //关闭开始时间6
  private Integer closeStartSix;

  //关闭结束时间1
  private Integer closeFinishOne;

  //关闭结束时间2
  private Integer closeFinishTwo;

  //关闭结束时间3
  private Integer closeFinishThree;

  //关闭结束时间4
  private Integer closeFinishFour;

  //关闭结束时间5
  private Integer closeFinishFive;

  //关闭结束时间6
  private Integer closeFinishSix;

  //互联接口id
  private Long huLianInterfaceId;

  //计费规则id
  @TableField(updateStrategy = FieldStrategy.IGNORED)
  private Long stationRuleId;
}
