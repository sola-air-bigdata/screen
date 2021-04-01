package com.nzkj.screen.mapper.pile.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.DTO.StationShowMonthMemberDTO;
import com.nzkj.screen.Entity.StationShowMonthMember;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: Liu yang
 * @Date: 2021/3/31 13:12
 * Describe:
 */
@Repository
public interface IStationShowMonthMemberMapper extends BaseMapper<StationShowMonthMember> {

    @Select("select count(*) from (select COUNT(*) from station_show_month_member_table where l_station_id = #{stationId} and l_seller_id = #{sellerId} and d_month_time= #{date}  group by l_member_id)c ")
    BigInteger getTotalFansData(@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id, @Param("date")String date);

    @Select("select count(*) from (select COUNT(*) from station_show_month_member_table where l_station_id = #{stationId} and l_seller_id = #{sellerId} and d_month_time= #{date} AND  month_member_charge_count >=5 group by l_member_id)c ")
    BigInteger getThanFiveFansData(@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id, @Param("date")String date);

    @Select("select count(1) from station_show_month_member_table where l_station_id = #{stationId} and l_seller_id = #{sellerId} and d_month_time= #{date} and month_member_charge_count < 5")
    BigInteger getLessFiveRate(@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id, @Param("date")String date);

    @Select("select count(1) from station_show_month_member_table where l_station_id = #{stationId} and l_seller_id = #{sellerId} and d_month_time= #{date} and month_member_charge_count >= 5")
    BigInteger getThanFiveRate(@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id, @Param("date")String date);

    @Select("select count(1) from station_show_month_member_table where l_station_id = #{stationId} and l_seller_id = #{sellerId} and d_month_time= #{date} and month_member_charge_count >= 10")
    BigInteger getThanTenRate(@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id, @Param("date")String date);

    @Select("select count(1) from station_show_month_member_table where l_station_id = #{stationId} and l_seller_id = #{sellerId} and d_month_time= #{date} and month_member_charge_count >= 20")
    BigInteger getThanTwentyRate(@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id, @Param("date")String date);

    @Select("select s.l_member_id, s.month_team_balance_count,s.month_member_charge_count from station_show_month_member_table s where s.l_station_id = #{stationId} and s.l_seller_id = #{sellerId} and s.d_month_time= #{date} order by s.month_team_balance_count desc limit 8 ")
    List<StationShowMonthMemberDTO>getStationConsumptionRanking(@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id, @Param("date")String date);


}
