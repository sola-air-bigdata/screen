package com.nzkj.screen.mapper.pile.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.AllMember;
import com.nzkj.screen.Entity.DTO.TeamRankingDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: Liu yang
 * @Date: 2021/4/8 14:34
 * Describe:
 */
@Repository
public interface IAllMemberMapper extends BaseMapper<AllMember> {

    @Select("select b.l_member_id AS memberId, b.month_team_actual_balance_count as actualBalanceCount, a.total_balance_count as totalBalanceCount , IFNULL(c.team_car_num, 0) as carNum from station_show_month_team_balance_table b join station_show_member_balance_table a on a.l_station_id = b.l_station_id and a.l_member_id = b.l_member_id left join t_all_member_table c on b.l_member_id = c.l_member_id where b.l_seller_id = #{sellerId} and a.l_station_id = #{stationId} and d_month_time = #{time} order by b.month_team_actual_balance_count desc limit 0,8")
    List<TeamRankingDTO> getTeamRankingInfo (@Param("stationId")Long l_station_id, @Param("sellerId")Long l_seller_id, @Param("thisMonth")String thisMonth);


}
