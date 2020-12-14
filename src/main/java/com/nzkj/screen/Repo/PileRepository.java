package com.nzkj.screen.Repo;

import com.nzkj.screen.Entity.Pile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * @Author: Liu yang
 * @Date: 2020/7/6 15:18
 * Describe:
 */
public interface PileRepository  extends JpaRepository<Pile, Long> {

    @Query(nativeQuery = true,value = "select id from t_business_base_operation_pile where l_station_id = :stationId")
    List<BigInteger> findPileUnderStation(@Param("stationId") long stationId);

    @Query(nativeQuery = true,value = "select id from t_business_base_operation_pile where l_station_id = :stationId")
    Set<Long> findPileByStationId(@Param("stationId") long stationId);

    @Query(nativeQuery = true,value = "select count(distinct(tb.v_vin)) as cardaysum, DATE_FORMAT(tb.d_time_s, '%Y-%m-%d') as day, sum(tb.i_actual_balance) as powerbalacesum from t_origin_history_operation_bill tb left join t_business_base_operation_pile tp on tb.l_pile_id = tp.id and tp.b_delete_flag = 0 where tb.b_delete_flag = 0 and tb.b_finish = 1 and tp.l_station_id = :stationId and tb.l_seller_id = :sellerId and tb.d_add_time >= :startTime and tb.d_add_time <= :endTime group by day")
    List<Object> getCarSumData(@Param("stationId") long stationId,@Param("sellerId") long sellerId,@Param("stationId") String startTime,@Param("endTime") String endTime);


}
