package com.nzkj.screen.mapper.pile.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.AllMonthData;
import com.nzkj.screen.Entity.Bill;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 15:46
 * Describe:
 */
@Repository
public interface IAllMonthDataMapper extends BaseMapper<AllMonthData> {

    @Select("select * from t_all_month_table where l_seller_id = #{id} AND d_month_time = #{date}")
    AllMonthData get(@Param("id") Long id,@Param("date") String date);

    /**
     * 获取故障桩数量
     * @param id
     * @param date
     * @return
     */
    @Select("select month_fault_pile from t_all_month_table where l_seller_id = #{id} AND d_month_time = #{date}")
    BigInteger getFaultPile(@Param("id") Long id,@Param("date") String date);

}
