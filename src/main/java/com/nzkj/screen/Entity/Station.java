package com.nzkj.screen.Entity;



import com.nzkj.screen.Entity.DTO.StationConfigTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/** @Description 站点类 @Author fqg @Date 2020/5/11 13:57 @Version 1.0 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Station extends Entity {


  private Long id;

  //商家id
  private Long sellerId;

  /**
   * 区域添加者id
   */
  private Long userId;
  /**
   * 区域名称
   */
  private String name;
  /**
   * 站点电话
   */
  private String managerPhone;
  /**
   * 站点经度
   */
  private String longitude;
  /**
   * 站点纬度
   */
  private String latitude;

  /**
   * 详细地址
   */
  private String street;

  /**
   * 站点的状态
   *  //开启 关闭 未知 建设中 维护中
   */
  private Integer status;
  /**
   * 图片的路径
   */
  private String imagePath;

  /**
   * 上午开放时间(分钟)
   */

  private Integer openTimeAm;
  /**
   * 上午关闭时间(分钟)
   */

  private Integer closeTimeAm;

  /**
   * 下午开放时间
   */

  private Integer openTimePm;
  /**
   * 下午关闭时间
   */

  private Integer closeTimePm;

  /**
   * 区域id
   */
  private Long areaId;

  /**
   * 评分
   */
  private Double score;
  /*
   * 停车位数量
   */
  private Integer vehicleNumber;
  /*
   * 补贴说明
   */
  private String subsidy;
  /*
   * 图片1
   */
  private String img1;
  /*
   * 图片2
   */
  private String img2;
  /*
   * 图片3
   */
  private String img3;

  //站点类型
  private Integer stationType;
  //建设场所
  private Integer construction;

  /**
   * 站点简称
   */
  private String abbreviationName;


  /**
   * 快慢充
   * @return
   */
  private String fastSlow;
  /**
   * 运营类型
   * @return
   */
  private String runType;

  //站点的设置状态
  private StationConfigTypeEnum configType;

  //站点桩排设置List
//  private List<List<StationRowConfigDto>> configList;

  // 站点编号
  private String stationNumber;


  //  ===========    限制优惠券使用的时间段配置     ====== start
  private String preferentialLimitS1;
  private String preferentialLimitS2;
  private String preferentialLimitS3;
  private String preferentialLimitS4;
  private String preferentialLimitE1;
  private String preferentialLimitE2;
  private String preferentialLimitE3;
  private String preferentialLimitE4;
  //  ===========    限制优惠券使用的时间段配置     ====== end

  //互联接口id
  private Long hulianinterfaceId;
  //列表图标
  private String listImg;

  //运营单位id
  private Long unitId;


  //private OperatingUnitDto operatingUnitDto;

  private String tag1;

  private String tag2;



  // 是否可预约
    private Boolean appointAble;
  // 站点负责人
  private String managerName;


}
