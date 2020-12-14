package com.nzkj.screen.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/6/24 17:21
 * Describe:
 */
@Entity(name = "t_all_table")
public class TotalOperateData {

    @Id
    @GeneratedValue
    //商家id
    private long l_seller_id;

    //累计收入运营
    private Long total_i_actual_balance;

    //累计车队收入运营
    private Long team_i_actual_balance;

    //累计个人收入运营
    private Long personal_i_actual_balance;

    //累计充电收入运营
    private Long total_i_power_balance;

    //累计其他收入运营
    private Long total_i_service_balance;

    //累计服务会员数
    private int total_service_user_count;

    //累计服务次数
    private int total_service_count;

    //总功率
    private BigInteger total_equipment_power;

    //累计总充电量
    private BigInteger total_charging_power;

    //车队总充电量
    private BigInteger team_charging_power;

    //车队累计服务次数
    private BigInteger team_service_count;

    //个人累计服务次数
    private BigInteger personal_service_count;

    //累计服务时长
    private BigInteger total_service_time;

    //车队累计服务时长
    private BigInteger team_service_time;

    //个人累计服务时长
    private BigInteger personal_service_time;

    //累计微信运营收入
    private BigInteger total_wechat_actual_balance;

    //累计app运营收入
    private BigInteger total_app_actual_balance;

    //累计card 运营收入
    private BigInteger total_card_actual_balance;

    //累计vin运营收入
    private BigInteger total_vin_actual_balance;

    //充电站数量
    private BigInteger total_station_count;

    //对外充电站数量
    private BigInteger total_foreign_station_count;

    //对内充电站数量
    private BigInteger total_internal_station_count;

    //在建充电站数量
    private BigInteger total_construction_station_count;

    //桩数量
    private BigInteger total_charging_pile_count;

    //待建充电站数量
    private BigInteger total_bebuilt_station_count;

    //枪数量
    private BigInteger total_charging_gun_count;

    //累计服务黑金卡用户数
    private BigInteger total_service_blackgold_user_count;

    //累计服务钻石卡用户数
    private BigInteger total_service_diamond_user_count;

    //累计服务白金卡用户数
    private BigInteger total_service_platinum_user_count;

    //累计服务黄金卡用户数
    private BigInteger total_service_gold_user_count;

    //累计服务白银卡用户数
    private BigInteger total_service_silver_user_count;

    //累计服务个人用户数
    private BigInteger total_service_personal_user_count;

    //累计服务车队用户数
    private BigInteger total_service_team_user_count;










    public int getTotal_service_count() {
        return total_service_count;
    }

    public void setTotal_service_count(int total_service_count) {
        this.total_service_count = total_service_count;
    }

    public int getTotal_service_user_count() {
        return total_service_user_count;
    }

    public void setTotal_service_user_count(int total_service_user_count) {
        this.total_service_user_count = total_service_user_count;
    }


    public long getL_seller_id() {
        return l_seller_id;
    }

    public void setL_seller_id(long l_seller_id) {
        this.l_seller_id = l_seller_id;
    }

    public Long getTotal_i_actual_balance() {
        return total_i_actual_balance;
    }

    public void setTotal_i_actual_balance(Long total_i_actual_balance) {
        this.total_i_actual_balance = total_i_actual_balance;
    }

    public Long getTeam_i_actual_balance() {
        return team_i_actual_balance;
    }

    public void setTeam_i_actual_balance(Long team_i_actual_balance) {
        this.team_i_actual_balance = team_i_actual_balance;
    }

    public Long getPersonal_i_actual_balance() {
        return personal_i_actual_balance;
    }

    public void setPersonal_i_actual_balance(Long personal_i_actual_balance) {
        this.personal_i_actual_balance = personal_i_actual_balance;
    }

    public Long getTotal_i_power_balance() {
        return total_i_power_balance;
    }

    public void setTotal_i_power_balance(Long total_i_power_balance) {
        this.total_i_power_balance = total_i_power_balance;
    }

    public Long getTotal_i_service_balance() {
        return total_i_service_balance;
    }

    public void setTotal_i_service_balance(Long total_i_service_balance) {
        this.total_i_service_balance = total_i_service_balance;
    }
}
