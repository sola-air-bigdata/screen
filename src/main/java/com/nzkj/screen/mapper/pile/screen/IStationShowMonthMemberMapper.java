package com.nzkj.screen.mapper.pile.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.DTO.StationShowMonthMemberDTO;
import com.nzkj.screen.Entity.DTO.TeamChargeRankingDTO;
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

    @Select("select v.n_member_name as memberName, ROUND( st.month_team_power_count / st.month_member_charge_count, 1 ) as avePower, ROUND( st.month_member_charge_count  / v.team_car_num,  1 ) as IFfrePower, v.team_car_num as carCount, ( select ROUND( mt.month_member_charge_count / car.team_car_num, 1 ) as lastCount  from station_show_month_member_table mt left join t_all_member_table car on mt.l_member_id = car.l_member_id where mt.l_member_id = st.l_member_id and mt.l_station_id = st.l_station_id and mt.l_seller_id = st.l_seller_id and mt.l_seller_id = #{sellerId} and mt.l_station_id = #{stationId} and ( mt.s_member_flag = 2 or mt.s_member_flag = 0 ) and mt.d_month_time = #{lastMonth} ) as lastfrePower from station_show_month_member_table st left join t_all_member_table v on st.l_member_id = v.l_member_id where l_seller_id = #{sellerId} and l_station_id = #{stationId} and ( s_member_flag = 2 or s_member_flag = 0 ) and d_month_time = #{thisMonth} order by avePower desc limit 0,8")
    List<TeamChargeRankingDTO> getTeamSingleChargeRankingList(@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id, @Param("thisMonth")String thisMonth, @Param("lastMonth")String lastMonth);


}
