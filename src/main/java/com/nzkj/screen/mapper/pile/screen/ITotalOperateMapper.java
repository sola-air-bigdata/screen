package com.nzkj.screen.mapper.pile.screen;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.TotalOperateData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 16:29
 * Describe:
 */
@Repository
public interface ITotalOperateMapper extends BaseMapper<TotalOperateData> {

    @Select("select * from t_all_table where l_seller_id = #{id}")
    TotalOperateData get(@Param("id")Long l_seller_id);
}
