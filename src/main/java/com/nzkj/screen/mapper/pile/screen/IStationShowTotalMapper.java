package com.nzkj.screen.mapper.pile.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.DTO.StationServiceDTO;
import com.nzkj.screen.Entity.DTO.StationShowTotalDTO;
import com.nzkj.screen.Entity.StationShowTotal;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 16:02
 * Describe:
 */
@Repository
public interface IStationShowTotalMapper extends BaseMapper<StationShowTotal> {


    @Select("select * from station_show_total_table where l_station_id = #{stationId}")
    StationShowTotal get(@Param("stationId")Long l_station_id);


    @Select("select * from station_show_total_table where l_station_id = #{stationId} and l_seller_id = #{id} ")
    StationShowTotal getBySeller(@Param("stationId")Long l_station_id,@Param("id")Long l_seller_id);

    @Select("select t.l_station_id , t.total_station_service_count,   a.month_pile_num_count,   t.total_station_gun_num from   station_show_total_table t left join station_show_month_table a on   t.l_station_id = a.l_station_id where   t.l_seller_id = #{id} and a.d_month_time  = #{date} order by   month_pile_num_count desc limit 3")
    List<StationShowTotalDTO> getTop3(@Param("id")Long l_seller_id, @Param("date")String date);

    @Select("select   t.l_station_id ,   t.total_station_service_count,   a.month_pile_num_count,   t.total_station_gun_num from   station_show_total_table t left join station_show_month_table a on   t.l_station_id = a.l_station_id where   t.l_seller_id = #{id}   and a.d_month_time = #{date} order by   month_pile_num_count desc limit 3")
    List<StationServiceDTO> getStationServiceList(@Param("id") Long l_seller_id, @Param("date") String date);

    /**
     * 根据站点获取总收入
     * @param l_station_id
     * @return
     */
    @Select("select total_station_money_count from station_show_total_table where l_station_id = #{stationId} ")
    Long getBalanceById(@Param("stationId")Long l_station_id);

}
