package com.nzkj.screen.mapper.pile.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.nzkj.screen.Entity.DTO.PileDto;
import com.nzkj.screen.Entity.Pile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Repository
public interface IPileMapper extends BaseMapper<Pile> {

  /**
   * 根据id找桩
   * @param id
   * @return
   */
  @Select("select * from t_pile where id = #{id}")
  Pile get(@Param("id") Long id);

  /**
   * 根据站点id查询站点下所有桩id
   * @param stationId
   * @return
   */
  @Select("select id from t_pile where station_id = #{stationId} and deleted = 0")
  List<BigInteger> findPileUnderStation(@Param("stationId") long stationId);

  /**
   * 根据站点id查询站点下所有桩id
   * @param stationId
   * @return
   */
  @Select("select id from t_pile where station_id = #{stationId} and deleted = 0")
  Set<Long> findPileByStationId(@Param("stationId") long stationId);


//  @Select("select count(distinct(tb.v_vin)) as cardaysum, DATE_FORMAT(tb.d_time_s, '%Y-%m-%d') as day, sum(tb.i_actual_balance) as powerbalacesum from t_origin_history_operation_bill tb left join t_business_base_operation_pile tp on tb.l_pile_id = tp.id and tp.b_delete_flag = 0 where tb.b_delete_flag = 0 and tb.b_finish = 1 and tp.l_station_id = :stationId and tb.l_seller_id = :sellerId and tb.d_add_time >= :startTime and tb.d_add_time <= :endTime group by day")
//  List<Object> getCarSumData(@Param("stationId") long stationId, @Param("sellerId") long sellerId, @Param("stationId") String startTime, @Param("endTime") String endTime);

  @Select("select p.id as id, p.name as name,p.pile_num as pointNum,p.v_handle as handle,p.bulk as bulk,p.province as province,p.area as area,p.city as city,p.street as street,p.longitude as longitude,p.latitude as latitude,p.station_id as stationId,p.type_id as pileTypeId from t_pile p  where  p.deleted =0 and p.station_id = #{stationId} ")
  List<PileDto> findPileDtoByStationId(@Param("stationId") long stationId);

}
