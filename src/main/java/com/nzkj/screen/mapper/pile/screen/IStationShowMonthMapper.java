package com.nzkj.screen.mapper.pile.screen;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.StationShowMonth;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2021/3/31 10:39
 * Describe:
 */
@Repository
public interface IStationShowMonthMapper extends BaseMapper<StationShowMonth> {

    @Select("select * from station_show_month_table where l_station_id = #{stationId} and l_seller_id = #{sellerId} and d_month_time= #{date}")
    StationShowMonth get(@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id, @Param("date")String date);


    @Select("SELECT sum(month_pile_personal_num_count) from  station_show_month_table where l_station_id = #{stationId}  and l_seller_id = #{sellerId} ")
    BigInteger getUserCumulativeOrderNumber(@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id);

//    @Query("select month_hour_zero_charge_count,month_hour_one_charge_count,month_hour_two_charge_count,month_hour_three_charge_count,month_hour_four_charge_count,month_hour_five_charge_count,month_hour_six_charge_count,month_hour_seven_charge_count,month_hour_eight_charge_count,month_hour_nine_charge_count,month_hour_ten_charge_count,month_hour_eleven_charge_count,month_hour_twelve_charge_count,month_hour_thirteen_charge_count,month_hour_fourteen_charge_count,month_hour_fifteen_charge_count,month_hour_sixteen_charge_count,month_hour_seventeen_charge_count,month_hour_eighteen_charge_count,month_hour_nineteen_charge_count,month_hour_twenty_charge_count,month_hour_twenty_one_charge_count,month_hour_twenty_two_charge_count,month_hour_twenty_three_charge_count from station_show_month_table where l_station_id = #{stationId} and l_seller_id = #{sellerId} and d_month_time= #{date} ")
//     getMemberNumberOfConsumption(@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id, @Param("date")String date);




}
