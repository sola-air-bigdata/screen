package com.nzkj.screen.mapper.pile.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.StationShowDay;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:52
 * Describe:
 */
@Repository
public interface IStationShowDayMapper extends BaseMapper<StationShowDay> {


    @Select("select * from station_show_day_table where l_station_id = #{stationId} and l_seller_id = #{sellerId} and d_day_time= #{date} ")
    StationShowDay get(@Param("stationId") Long stationId, @Param("sellerId") Long sellerId, @Param("date")String date);
}
