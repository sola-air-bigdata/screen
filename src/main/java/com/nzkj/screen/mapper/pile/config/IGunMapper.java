package com.nzkj.screen.mapper.pile.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.Gun;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 9:22
 * Describe:
 */
@Repository
public interface IGunMapper extends BaseMapper<Gun> {

    /**
     * 按桩号查找桩下的枪ID
     * @param pileId
     * @return
     */
    @Select("select id from t_gun where pile_id = #{pileId} ")
    List<Object> findGunUnderPile (@Param("pileId")long pileId);

    /**
     * 按桩号查找桩下的枪号
     * @param pileId
     * @return
     */
    @Select("select number from t_gun where pile_id = #{pileId} ")
    Set<Long> findGunNoByPileId (@Param("pileId")long pileId);
}
