package com.nzkj.screen.mapper.pile.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.TotalOperateData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 16:29
 * Describe:
 */
@Repository
public interface ITotalOperateMapper extends BaseMapper<TotalOperateData> {

    @Select("select * from t_all_table where l_seller_id = #{id}")
    TotalOperateData get(@Param("id")Long l_seller_id);

    /**
     * 获取商家桩总数
     * @param l_seller_id
     * @return
     */
    @Select("SELECT total_charging_pile_count FROM t_all_table  WHERE l_seller_id =  #{id} ")
    BigInteger getChargingPileCount(@Param("id")Long l_seller_id);

    /**
     * 获取累计服务的会员数
     * @param l_seller_id
     * @return
     */
    @Select("SELECT total_service_user_count  FROM t_all_table  WHERE l_seller_id = #{id}")
    BigInteger getUserChargeCount(@Param("id") Long l_seller_id);

}
