package com.nzkj.screen.Repo;

import com.nzkj.screen.Entity.AllDayData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: Liu yang
 * @Date: 2020/6/30 16:39
 * Describe:
 */
public interface AllDayDataRepository  extends JpaRepository<AllDayData, Long> {

    @Query(nativeQuery = true, value = "SELECT day_team_active_count, day_personal_active_count from t_all_day_table WHERE l_seller_id = :id AND STR_TO_DATE(d_day_time,'%Y-%m-%d') = :date")
    Object getUserActivityList(@Param("id") Long l_seller_id, @Param("date") String date);

    @Query("SELECT day_growth_team_user,day_growth_personal_user  from t_all_day_table WHERE l_seller_id = :id AND d_day_time = :date")
    Object getPlatformMemberCount(@Param("id") Long l_seller_id,@Param("date") String date);

    @Query("SELECT  day_team_service_user, day_personal_service_user, day_service_app_user, day_service_wechat_user from t_all_day_table WHERE l_seller_id = :id AND d_day_time = :date")
    Object getMemberInfo (@Param("id") Long l_seller_id,@Param("date") String date);

    @Query("SELECT day_growth_team_user, day_growth_personal_user, d_day_time from  t_all_day_table WHERE l_seller_id = :id AND STR_TO_DATE(d_day_time,'%Y-%m-%d') = :date")
    Object getMemberAddRecordList(@Param("id") Long l_seller_id,@Param("date") String date);

    @Query("select day_i_actual_balance,day_i_power_balance,day_i_power_balance_nteam,day_i_actual_balance_balance_nteam from  t_all_day_table where l_seller_id = :id AND STR_TO_DATE(d_day_time, '%Y-%m-%d') = :date")
    Object getDayTransBalance(@Param("id") Long l_seller_id,@Param("date") String date);


    @Query(nativeQuery = true,value = "SELECT  day_nbus_i_actual_balance_count_rank, b.v_name from  t_all_day_member_table a join t_business_base_operation_persional_member b on a.l_member_id = b.id where a.l_seller_id = :sellerId  and a.l_member_id = :userId  and STR_TO_DATE(a.d_day_time,'%Y-%m-%d') = :date  order by day_nbus_i_actual_balance_count_rank desc limit 3")
    List<Object> getdayTransIncomeRank(@Param("sellerId") Long l_seller_id,@Param("userId")Long userId,@Param("date") String date);

    @Query(" select day_i_actual_balance_wechat, day_i_actual_balance_app, day_i_actual_balance_card, day_i_actual_balance_vin from t_all_day_table where l_seller_id = :id AND d_day_time = :date ")
    Object getTotalBalanceData(@Param("id") Long l_seller_id,@Param("date") String date);

    @Query(nativeQuery = true,value = " SELECT sum(day_personal_i_power_balance_count)  from t_all_day_member_table WHERE l_seller_id = :id and d_day_time = :date")
    Object getPersonalTrans(@Param("id") Long l_seller_id,@Param("date") String date);

    @Query("SELECT day_i_actual_balance_balance_nteam  from t_all_day_table WHERE l_seller_id = :id AND d_day_time = :date")
    Object getTotalTransIncome(@Param("id") Long l_seller_id,@Param("date") String date);


}
