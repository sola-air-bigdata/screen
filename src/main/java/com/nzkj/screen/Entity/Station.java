package com.nzkj.screen.Entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

/**
 * @Author: Liu yang
 * @Date: 2020/7/3 10:04
 * Describe:
 */
@Entity(name = "t_business_base_operation_station")
public class Station {

    @Id
    @GeneratedValue
    //id
    private long id;

    /**
     * 区域添加者id
     */
    @Column(name = "v_manager_id")
    private Long managerId;

    /**
     * 区域名称
     */
    @Column(name = "v_name")
    private String name;

    /**
     * 站点电话
     */
    @Column(name = "v_manager_phone")
    private String managerPhone;
    /**
     * 站点经度
     */
    @Column(name = "v_longitude")
    private String Longitude;
    /**
     * 站点纬度
     */
    @Column(name = "v_latitude")
    private String latitude;

    /**
     * 详细地址
     */
    @Column(name = "v_street")
    private String street;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "v_station_id")
    private Station stationParent;
    /**
     * 站点的状态
     */
    @Column(name = "v_status")
    private String status;
    /**
     * 图片的路径
     */
    @Column(name = "v_image_path")
    private String imagePath;

    /**
     * 上午开放时间
     */
    @Column(name = "d_open_time_am")
    private Integer openTimeAm;
    /**
     * 上午关闭时间
     */
    @Column(name = "d_close_time_am")
    private Integer closetimeAm;

    /**
     * 下午开放时间
     */
    @Column(name = "d_open_time_pm")
    private Integer openTimePm;
    /**
     * 下午关闭时间
     */
    @Column(name = "d_close_time_pm")
    private Integer closeTimePm;



    @OneToMany(mappedBy = "station")
    private Set<Pile> setPile;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "l_area_id")
    private Area area;

//    @ManyToOne
//    @Fetch(FetchMode.SELECT)
//    @JoinColumn(name = "l_seller_id")
//    private Operator operator;
    @Column(name = "l_seller_id")
    private Long sellId;

    @Column(name = "i_score")
    private Double score;

    @Column(name = "i_vehicle_number")
    private Integer vehicleNumber;

    @Column(name = "v_subsidy")
    private String subsidy;

    @Column(name = "v_img1")
    private String img1;

    @Column(name = "v_img2")
    private String img2;

    @Column(name = "v_img3")
    private String img3;
    @Column(name = "v_list_img")
    private String listImg;
    /**
     * 月租金
     */
    @Column(name = "i_monthly_rent")
    private Integer monthlyRent = 0;

    @Column(name = "v_area_code")
    private String areaCode;

    @Column(name = "i_station_type")
    private Integer stationType;

    @Column(name = "i_construction")
    private Integer construction;

    /**
     * 站点简称
     */
    @Column(name = "v_abbreviation_name")
    private String abbreviationName;

    /**
     * 快慢充
     *
     * @return
     */
    @Column(name = "v_fast_slow")
    private String fastSlow;
    /**
     * 运营类型
     *
     * @return
     */
    @Column(name = "v_run_type")
    private String runType;

    // 站点建设目标投入金额（分）
    @Column(name = "l_put_into_money")
    private Long putIntoMoney;

    // 站点建设目标实际投入金额（分）
    @Column(name = "l_actual_put_into_money")
    private Long actualPutIntoMoney;

    // 站点充电量指标（度）
    @Column(name = "l_charge_target")
    private Long chargeTarget;

    // 站点建桩指标
    @Column(name = "i_pile_target")
    private Integer pileTarget;

    //桩排数
    @Column(name = "i_pile_row_num")
    private Integer pileRowNum;

    //每一排的桩数量
    @Column(name = "i_pile_num_of_row")
    private Integer pileNumOfRow;

    //站点的设置状态
    @Column(name = "v_config_type")
    private String configType;

    // 站点编号
    @Column(name = "v_station_number")
    private String stationNumber;

    //  ===========    限制优惠券使用的时间段配置     ====== start
    @Column(name = "v_preferential_limit_s1")
    private String preferentialLimitS1;
    @Column(name = "v_preferential_limit_s2")
    private String preferentialLimitS2;
    @Column(name = "v_preferential_limit_s3")
    private String preferentialLimitS3;
    @Column(name = "v_preferential_limit_s4")
    private String preferentialLimitS4;
    @Column(name = "v_preferential_limit_e1")
    private String preferentialLimitE1;
    @Column(name = "v_preferential_limit_e2")
    private String preferentialLimitE2;
    @Column(name = "v_preferential_limit_e3")
    private String preferentialLimitE3;
    @Column(name = "v_preferential_limit_e4")
    private String preferentialLimitE4;

    //  ===========    限制优惠券使用的时间段配置     ====== end

    //互联接口id
    @Column(name = "l_h_interface_id")
    private Long huLianInterfaceId;

    //运营单位id
    @Column(name = "l_unit_id")
    private Long operatingUnitId;


    @Column(name="b_delete_flag", nullable=true, length=1)
    private Boolean deleteFlag = Boolean.valueOf(false);

    @Column(name="d_add_time", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date addTime;

    @Column(name="d_update_time", nullable=true)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date updateTime;

    @Transient
    public Long stationParentId;
    @Transient
    public Long areaId;
    @Transient
    public Long operatorId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Station getStationParent() {
        return stationParent;
    }

    public void setStationParent(Station stationParent) {
        this.stationParent = stationParent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getOpenTimeAm() {
        return openTimeAm;
    }

    public void setOpenTimeAm(Integer openTimeAm) {
        this.openTimeAm = openTimeAm;
    }

    public Integer getClosetimeAm() {
        return closetimeAm;
    }

    public void setClosetimeAm(Integer closetimeAm) {
        this.closetimeAm = closetimeAm;
    }

    public Integer getOpenTimePm() {
        return openTimePm;
    }

    public void setOpenTimePm(Integer openTimePm) {
        this.openTimePm = openTimePm;
    }

    public Integer getCloseTimePm() {
        return closeTimePm;
    }

    public void setCloseTimePm(Integer closeTimePm) {
        this.closeTimePm = closeTimePm;
    }

    public Set<Pile> getSetPile() {
        return setPile;
    }

    public void setSetPile(Set<Pile> setPile) {
        this.setPile = setPile;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Long getSellId() {
        return sellId;
    }

    public void setSellId(Long sellId) {
        this.sellId = sellId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(String subsidy) {
        this.subsidy = subsidy;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getListImg() {
        return listImg;
    }

    public void setListImg(String listImg) {
        this.listImg = listImg;
    }

    public Integer getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Integer monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Integer getStationType() {
        return stationType;
    }

    public void setStationType(Integer stationType) {
        this.stationType = stationType;
    }

    public Integer getConstruction() {
        return construction;
    }

    public void setConstruction(Integer construction) {
        this.construction = construction;
    }

    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }

    public String getFastSlow() {
        return fastSlow;
    }

    public void setFastSlow(String fastSlow) {
        this.fastSlow = fastSlow;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public Long getPutIntoMoney() {
        return putIntoMoney;
    }

    public void setPutIntoMoney(Long putIntoMoney) {
        this.putIntoMoney = putIntoMoney;
    }

    public Long getActualPutIntoMoney() {
        return actualPutIntoMoney;
    }

    public void setActualPutIntoMoney(Long actualPutIntoMoney) {
        this.actualPutIntoMoney = actualPutIntoMoney;
    }

    public Long getChargeTarget() {
        return chargeTarget;
    }

    public void setChargeTarget(Long chargeTarget) {
        this.chargeTarget = chargeTarget;
    }

    public Integer getPileTarget() {
        return pileTarget;
    }

    public void setPileTarget(Integer pileTarget) {
        this.pileTarget = pileTarget;
    }

    public Integer getPileRowNum() {
        return pileRowNum;
    }

    public void setPileRowNum(Integer pileRowNum) {
        this.pileRowNum = pileRowNum;
    }

    public Integer getPileNumOfRow() {
        return pileNumOfRow;
    }

    public void setPileNumOfRow(Integer pileNumOfRow) {
        this.pileNumOfRow = pileNumOfRow;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getPreferentialLimitS1() {
        return preferentialLimitS1;
    }

    public void setPreferentialLimitS1(String preferentialLimitS1) {
        this.preferentialLimitS1 = preferentialLimitS1;
    }

    public String getPreferentialLimitS2() {
        return preferentialLimitS2;
    }

    public void setPreferentialLimitS2(String preferentialLimitS2) {
        this.preferentialLimitS2 = preferentialLimitS2;
    }

    public String getPreferentialLimitS3() {
        return preferentialLimitS3;
    }

    public void setPreferentialLimitS3(String preferentialLimitS3) {
        this.preferentialLimitS3 = preferentialLimitS3;
    }

    public String getPreferentialLimitS4() {
        return preferentialLimitS4;
    }

    public void setPreferentialLimitS4(String preferentialLimitS4) {
        this.preferentialLimitS4 = preferentialLimitS4;
    }

    public String getPreferentialLimitE1() {
        return preferentialLimitE1;
    }

    public void setPreferentialLimitE1(String preferentialLimitE1) {
        this.preferentialLimitE1 = preferentialLimitE1;
    }

    public String getPreferentialLimitE2() {
        return preferentialLimitE2;
    }

    public void setPreferentialLimitE2(String preferentialLimitE2) {
        this.preferentialLimitE2 = preferentialLimitE2;
    }

    public String getPreferentialLimitE3() {
        return preferentialLimitE3;
    }

    public void setPreferentialLimitE3(String preferentialLimitE3) {
        this.preferentialLimitE3 = preferentialLimitE3;
    }

    public String getPreferentialLimitE4() {
        return preferentialLimitE4;
    }

    public void setPreferentialLimitE4(String preferentialLimitE4) {
        this.preferentialLimitE4 = preferentialLimitE4;
    }

    public Long getHuLianInterfaceId() {
        return huLianInterfaceId;
    }

    public void setHuLianInterfaceId(Long huLianInterfaceId) {
        this.huLianInterfaceId = huLianInterfaceId;
    }

    public Long getOperatingUnitId() {
        return operatingUnitId;
    }

    public void setOperatingUnitId(Long operatingUnitId) {
        this.operatingUnitId = operatingUnitId;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getStationParentId() {
        return stationParentId;
    }

    public void setStationParentId(Long stationParentId) {
        this.stationParentId = stationParentId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }
}
