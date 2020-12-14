package com.nzkj.screen.Repo;

import com.nzkj.screen.Entity.StationShowTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/11/19 9:26
 * Describe:
 */
public interface StationShowTotalRepository extends JpaRepository<StationShowTotal, Long> {

    @Query("SELECT total_station_service_count, total_station_power_count, total_station_money_count, total_station_jian_power_count, total_station_feng_power_count, total_station_ping_power_count, total_station_gu_power_count FROM  station_show_total_table WHERE l_seller_id = :sellerId")
    Object getStationBalanceInfo(@Param("sellerId") Long l_seller_id);

    @Query("SELECT total_station_service_count, total_station_power_count, total_station_money_count, total_station_jian_power_count, total_station_feng_power_count, total_station_ping_power_count, total_station_gu_power_count FROM  station_show_total_table WHERE l_seller_id = :sellerId AND l_station_id = :stationId")
    Object getStationBalanceInfoByStationId(@Param("sellerId") Long l_seller_id, @Param("stationId")Long l_station_id);

    @Query("from station_show_total_table where l_station_id = :stationId")
    StationShowTotal getByL_station_id(@Param("stationId")Long l_station_id);
}
