package com.nzkj.screen.Repo;

import com.nzkj.screen.Entity.TotalOperateData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sun.awt.SunHints;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: Liu yang
 * @Date: 2020/6/24 17:28
 * Describe:
 */
public interface TotalOperateDataRepository extends JpaRepository<TotalOperateData, Long> {


    @Query("select total_i_actual_balance,total_service_user_count,total_service_count from t_all_table where l_seller_id = :id")
    Object queryTotalNum(@Param("id") Long l_seller_id);

    @Query("select total_equipment_power from t_all_table where l_seller_id= :id")
    BigInteger getCapacitySum(@Param("id") Long l_seller_id);

    @Query("SELECT total_charging_power FROM t_all_table WHERE l_seller_id = :id")
    Object getSumPowerCount(@Param("id") Long l_seller_id);

    @Query("SELECT team_charging_power FROM t_all_table WHERE l_seller_id = :id")
    Object getTemPowerCount(@Param("id") Long l_seller_id);

    @Query(nativeQuery = true, value = "SELECT bo.v_name, t.total_station_service_count, IFNULL(month_pile_num_count,0) as allChargeCount, t.total_station_gun_num from station_show_total_table t left join t_business_base_operation_station bo on t.l_station_id = bo.id LEFT JOIN station_show_month_table a on t.l_station_id=a.l_station_id where t.l_seller_id = :id and a.d_month_time  = :date order by month_pile_num_count desc limit 3 ")
    List<Object> getStationServiceList(@Param("id") Long l_seller_id,@Param("date") String date);

    @Query("SELECT total_service_count, team_service_count, personal_service_count, total_service_time, team_service_time, personal_service_time from t_all_table where l_seller_id = :id")
    Object getServiceRunData(@Param("id") Long l_seller_id);

    @Query("select total_wechat_actual_balance, total_app_actual_balance, total_card_actual_balance, total_vin_actual_balance from t_all_table where l_seller_id = :id ")
    Object getTotalBalanceData(@Param("id") Long l_seller_id);

    @Query("SELECT total_i_actual_balance, team_i_actual_balance, personal_i_actual_balance, total_i_power_balance, total_i_service_balance FROM t_all_table WHERE l_seller_id = :id")
    Object getServiceUserCount(@Param("id") Long l_seller_id);

    @Query("select total_station_count,total_foreign_station_count,total_internal_station_count,total_construction_station_count,total_bebuilt_station_count,total_charging_pile_count,total_charging_gun_count from t_all_table where l_seller_id = :id")
    Object getHomeStationData(@Param("id") Long l_seller_id);

    @Query(" SELECT b.total_charging_pile_count FROM t_all_table b WHERE b.l_seller_id =  :id ")
    Object getChargingPileCountCount(@Param("id") Long l_seller_id);

    @Query("SELECT total_service_user_count  FROM t_all_table  WHERE l_seller_id = :id")
    Object getUserChargeCount(@Param("id") Long l_seller_id);

    @Query("SELECT total_service_blackgold_user_count, total_service_diamond_user_count, total_service_platinum_user_count,total_service_gold_user_count,total_service_silver_user_count, total_service_personal_user_count, total_service_team_user_count FROM t_all_table WHERE l_seller_id = :id")
    Object getMemberAndTeamData(@Param("id") Long l_seller_id);

    @Query(nativeQuery = true, value = "SELECT tp.l_station_id as stationId, SUM(tb.i_actual_balance) AS actualBalance FROM t_origin_history_operation_bill tb LEFT JOIN t_business_base_operation_pile tp ON tb.l_pile_id = tp.id WHERE tb.b_finish = 1 AND tb.b_delete_flag = 0 AND tb.l_seller_id = :sellerId  GROUP BY tp.l_station_id  AND tp.l_station_id IN (select n.l_station_id from t_business_base_operation_user_station n WHERE n.l_user_id = :userId) ")
    List<Object> getBalances(@Param("sellerId")Long l_seller_id,@Param("userId")Long userId);

}
