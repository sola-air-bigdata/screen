package com.nzkj.screen.Repo;

import com.nzkj.screen.Entity.AllMonthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author: Liu yang
 * @Date: 2020/6/30 14:51
 * Describe:
 */
public interface AllMonthDataRepository  extends JpaRepository<AllMonthData, Long> {

    //月充电总收入  月其他总收入
    @Query("SELECT month_i_charging_actual_balance,month_i_service_actual_balance FROM t_all_month_table WHERE l_seller_id = :id AND d_month_time = :date")
    Object getRevenueTrendList(@Param("id") Long l_seller_id, @Param("date") String date);

    //月个人充电收入  月个人其他收入
    @Query(" SELECT month_personal_i_charging_actual_balance, month_personal_i_service_actual_balance FROM t_all_month_table WHERE l_seller_id = :id AND d_month_time = :date")
    Object getPersonRevenueTrendList(@Param("id") Long l_seller_id, @Param("date") String date);

    @Query("SELECT month_growth_team_user,  month_growth_personal_user  from t_all_month_table WHERE  l_seller_id = :id AND d_month_time = :date ")
    Object getPlatformMemberCount(@Param("id") Long l_seller_id, @Param("date") String date);

//    @Query("select month_growth_team_user, month_growth_personal_user from t_all_month_table where l_seller_id = :id AND d_month_time = :date ")
//    Object getMemberAddRecord(@Param("id") Long l_seller_id, @Param("date") String date);

    @Query("select month_fault_pile  from t_all_month_table where l_seller_id = :id and d_month_time = :date ")
    Object getMothFault(@Param("id") Long l_seller_id, @Param("date") String date);

    @Query("SELECT b.month_fault_pile AS billCount FROM t_all_month_table b WHERE b.l_seller_id = :id AND b.d_month_time = :date ")
    Object getFaultCount(@Param("id") Long l_seller_id, @Param("date") String date);

}

