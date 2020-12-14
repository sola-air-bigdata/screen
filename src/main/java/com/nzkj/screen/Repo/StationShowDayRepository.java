package com.nzkj.screen.Repo;

import com.nzkj.screen.Entity.StationShowDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 13:06
 * Describe:
 */
public interface StationShowDayRepository extends JpaRepository<StationShowDay, BigInteger> {


    @Query("SELECT day_station_pile_count, day_station_power_count,day_station_balance_count from station_show_day_table WHERE l_seller_id = :sellerId AND l_station_id = :stationId  AND d_day_time = :date")
    Object getDayMemberInfo(@Param("stationId") Long stationId,@Param("sellerId") Long sellerId, @Param("date")String date );

    @Query(nativeQuery = true,value = "SELECT count(tb.id) AS billsum, sum(tb.m_sum_power) AS powersum,sum(tb.i_actual_balance) AS powerbalacesum FROM t_origin_history_operation_bill tb LEFT JOIN t_business_base_operation_pile tp ON tb.l_pile_id = tp.id and tp.b_delete_flag = 0  WHERE tb.b_delete_flag = 0 and tb.b_finish =1 AND tp.l_station_id = :stationId and tb.l_seller_id = :sellerId AND tb.d_add_time >= :startTime  AND tb.d_add_time <= :endTime")
    List<Object> getStationBalanceData(@Param("stationId") Long stationId, @Param("sellerId") Long sellerId, @Param("startTime")String startTime , @Param("endTime")String endTime);

    @Query("select day_station_service_bus_car_num ,day_station_service_other_car_num from station_show_day_table where l_station_id = :stationId and l_seller_id = :sellerId and d_day_time= :date")
    Object getTodayServideCarSum(@Param("stationId") Long stationId,@Param("sellerId") Long sellerId, @Param("date")String date );

}
