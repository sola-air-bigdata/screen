package com.nzkj.screen.Entity;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 9:10
 * Describe:
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity(name = "station_show_total_table")
public class StationShowTotal {

    @Id
    @GeneratedValue
    //站点
    private Long	l_station_id;
    //商家
    private Long	l_seller_id;
    //站点总桩数
    private BigInteger	total_station_pile_num;
    //站点总枪数
    private BigInteger	total_station_gun_num;
    //站点总服务次数
    private BigInteger	total_station_service_count;
    //站点服务个人用户总数
    private BigInteger	total_station_service_use_count;
    //站点服务车队用户总数
    private BigInteger	total_station_service_team_count;
    //站点车队总充电量
    private BigInteger	total_station_team_power_count;
    //站点车队总订单数
    private BigInteger	total_station_team_bill_count;
    //站点车队订单总金额
    private BigInteger	total_station_team_menoy_count;
    //站点服务车队车辆总数
    private BigInteger	total_station_service_team_cars;
    //站点服务车队充电总时长
    private BigInteger	total_station_service_team_time;
    //站点服务个人银卡用户总数
    private BigInteger	total_station_service_silver_use_count;
    //站点服务个人金卡用户总数
    private BigInteger	total_station_service_gold_use_count;
    //站点服务个人白金卡用户总数
    private BigInteger	total_station_service_platinum_use_count;
    //站点服务个人钻石卡用户总数
    private BigInteger	total_station_service_diamond_use_count;
    //站点服务个人黑金卡用户总数
    private BigInteger	total_station_service_blackgold_use_count;
    //站点总充电量
    private BigInteger	total_station_power_count;
    //站点总收入
    private BigInteger	total_station_money_count;
    //站点总功率
    private BigInteger	total_station_i_power_count;
    //站点变压器总功率
    private BigInteger	total_station_change_power_count;
    //站点尖总充电量
    private BigInteger	total_station_jian_power_count;
    //站点峰总充电量
    private BigInteger	total_station_feng_power_count;
    //站点平总充电量
    private BigInteger	total_station_ping_power_count;
    //站点谷总充电量
    private BigInteger	total_station_gu_power_count;

    public Long getL_station_id() {
        return l_station_id;
    }

    public void setL_station_id(Long l_station_id) {
        this.l_station_id = l_station_id;
    }

    public Long getL_seller_id() {
        return l_seller_id;
    }

    public void setL_seller_id(Long l_seller_id) {
        this.l_seller_id = l_seller_id;
    }

    public BigInteger getTotal_station_pile_num() {
        return total_station_pile_num;
    }

    public void setTotal_station_pile_num(BigInteger total_station_pile_num) {
        this.total_station_pile_num = total_station_pile_num;
    }

    public BigInteger getTotal_station_gun_num() {
        return total_station_gun_num;
    }

    public void setTotal_station_gun_num(BigInteger total_station_gun_num) {
        this.total_station_gun_num = total_station_gun_num;
    }

    public BigInteger getTotal_station_service_count() {
        return total_station_service_count;
    }

    public void setTotal_station_service_count(BigInteger total_station_service_count) {
        this.total_station_service_count = total_station_service_count;
    }

    public BigInteger getTotal_station_service_use_count() {
        return total_station_service_use_count;
    }

    public void setTotal_station_service_use_count(BigInteger total_station_service_use_count) {
        this.total_station_service_use_count = total_station_service_use_count;
    }

    public BigInteger getTotal_station_service_team_count() {
        return total_station_service_team_count;
    }

    public void setTotal_station_service_team_count(BigInteger total_station_service_team_count) {
        this.total_station_service_team_count = total_station_service_team_count;
    }

    public BigInteger getTotal_station_team_power_count() {
        return total_station_team_power_count;
    }

    public void setTotal_station_team_power_count(BigInteger total_station_team_power_count) {
        this.total_station_team_power_count = total_station_team_power_count;
    }

    public BigInteger getTotal_station_team_bill_count() {
        return total_station_team_bill_count;
    }

    public void setTotal_station_team_bill_count(BigInteger total_station_team_bill_count) {
        this.total_station_team_bill_count = total_station_team_bill_count;
    }

    public BigInteger getTotal_station_team_menoy_count() {
        return total_station_team_menoy_count;
    }

    public void setTotal_station_team_menoy_count(BigInteger total_station_team_menoy_count) {
        this.total_station_team_menoy_count = total_station_team_menoy_count;
    }

    public BigInteger getTotal_station_service_team_cars() {
        return total_station_service_team_cars;
    }

    public void setTotal_station_service_team_cars(BigInteger total_station_service_team_cars) {
        this.total_station_service_team_cars = total_station_service_team_cars;
    }

    public BigInteger getTotal_station_service_team_time() {
        return total_station_service_team_time;
    }

    public void setTotal_station_service_team_time(BigInteger total_station_service_team_time) {
        this.total_station_service_team_time = total_station_service_team_time;
    }

    public BigInteger getTotal_station_service_silver_use_count() {
        return total_station_service_silver_use_count;
    }

    public void setTotal_station_service_silver_use_count(BigInteger total_station_service_silver_use_count) {
        this.total_station_service_silver_use_count = total_station_service_silver_use_count;
    }

    public BigInteger getTotal_station_service_gold_use_count() {
        return total_station_service_gold_use_count;
    }

    public void setTotal_station_service_gold_use_count(BigInteger total_station_service_gold_use_count) {
        this.total_station_service_gold_use_count = total_station_service_gold_use_count;
    }

    public BigInteger getTotal_station_service_platinum_use_count() {
        return total_station_service_platinum_use_count;
    }

    public void setTotal_station_service_platinum_use_count(BigInteger total_station_service_platinum_use_count) {
        this.total_station_service_platinum_use_count = total_station_service_platinum_use_count;
    }

    public BigInteger getTotal_station_service_diamond_use_count() {
        return total_station_service_diamond_use_count;
    }

    public void setTotal_station_service_diamond_use_count(BigInteger total_station_service_diamond_use_count) {
        this.total_station_service_diamond_use_count = total_station_service_diamond_use_count;
    }

    public BigInteger getTotal_station_service_blackgold_use_count() {
        return total_station_service_blackgold_use_count;
    }

    public void setTotal_station_service_blackgold_use_count(BigInteger total_station_service_blackgold_use_count) {
        this.total_station_service_blackgold_use_count = total_station_service_blackgold_use_count;
    }

    public BigInteger getTotal_station_power_count() {
        return total_station_power_count;
    }

    public void setTotal_station_power_count(BigInteger total_station_power_count) {
        this.total_station_power_count = total_station_power_count;
    }

    public BigInteger getTotal_station_money_count() {
        return total_station_money_count;
    }

    public void setTotal_station_money_count(BigInteger total_station_money_count) {
        this.total_station_money_count = total_station_money_count;
    }

    public BigInteger getTotal_station_i_power_count() {
        return total_station_i_power_count;
    }

    public void setTotal_station_i_power_count(BigInteger total_station_i_power_count) {
        this.total_station_i_power_count = total_station_i_power_count;
    }

    public BigInteger getTotal_station_change_power_count() {
        return total_station_change_power_count;
    }

    public void setTotal_station_change_power_count(BigInteger total_station_change_power_count) {
        this.total_station_change_power_count = total_station_change_power_count;
    }

    public BigInteger getTotal_station_jian_power_count() {
        return total_station_jian_power_count;
    }

    public void setTotal_station_jian_power_count(BigInteger total_station_jian_power_count) {
        this.total_station_jian_power_count = total_station_jian_power_count;
    }

    public BigInteger getTotal_station_feng_power_count() {
        return total_station_feng_power_count;
    }

    public void setTotal_station_feng_power_count(BigInteger total_station_feng_power_count) {
        this.total_station_feng_power_count = total_station_feng_power_count;
    }

    public BigInteger getTotal_station_ping_power_count() {
        return total_station_ping_power_count;
    }

    public void setTotal_station_ping_power_count(BigInteger total_station_ping_power_count) {
        this.total_station_ping_power_count = total_station_ping_power_count;
    }

    public BigInteger getTotal_station_gu_power_count() {
        return total_station_gu_power_count;
    }

    public void setTotal_station_gu_power_count(BigInteger total_station_gu_power_count) {
        this.total_station_gu_power_count = total_station_gu_power_count;
    }
}
