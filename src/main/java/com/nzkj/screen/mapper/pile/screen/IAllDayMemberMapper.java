package com.nzkj.screen.mapper.pile.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.AllDayData;
import com.nzkj.screen.Entity.AllDayMember;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:26
 * Describe:
 */
@Repository
public interface IAllDayMemberMapper extends BaseMapper<AllDayMember> {

    @Select("SELECT  day_nbus_i_actual_balance_count_rank from  t_all_day_member_table a  where l_seller_id = #{sellerId}  and l_member_id = #{userId} and STR_TO_DATE(a.d_day_time,'%Y-%m-%d') = #{date}   order by day_nbus_i_actual_balance_count_rank desc limit 3")
    List<AllDayMember> getdayTransIncomeRank(@Param("sellerId") Long l_seller_id, @Param("userId")Long userId, @Param("date") String date);

    @Select(" SELECT sum(day_personal_i_power_balance_count)  from t_all_day_member_table WHERE l_seller_id = #{id} and d_day_time = #{date}")
    int getPersonalTrans(@Param("id") Long l_seller_id, @Param("date") String date);
}
