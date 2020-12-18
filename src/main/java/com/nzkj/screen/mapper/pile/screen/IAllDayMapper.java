package com.nzkj.screen.mapper.pile.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.AllDayData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 13:16
 * Describe:
 */
@Repository
public interface IAllDayMapper extends BaseMapper<AllDayData> {

    /**
     * 根据sellerId 和 日期 查询 汇总表里的数据
     * @param l_seller_id
     * @param date
     * @return
     */
    @Select("SELECT * from t_all_day_table WHERE l_seller_id = #{sellerId} AND STR_TO_DATE(d_day_time,'%Y-%m-%d') = #{date} ")
    AllDayData get(@Param("sellerId") Long l_seller_id,@Param("date") String date);

    @Select("SELECT day_team_active_count, day_personal_active_count from t_all_day_table WHERE l_seller_id = #{sellerId} AND STR_TO_DATE(d_day_time,'%Y-%m-%d') = #{date} ")
    AllDayData getUserActivityList(@Param("sellerId") Long l_seller_id, @Param("date") String date);


    @Select("SELECT day_i_actual_balance_balance_nteam  from t_all_day_table WHERE l_seller_id = #{id} AND d_day_time = #{date} ")
    BigInteger getTotalTransIncome(@Param("id") Long l_seller_id, @Param("date") String date);



}
