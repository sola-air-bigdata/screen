package com.nzkj.screen.Repo;

import com.nzkj.screen.Entity.Gun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * @Author: Liu yang
 * @Date: 2020/7/6 15:46
 * Describe:
 */
public interface GunRepository  extends JpaRepository<Gun, Long> {

    @Query(nativeQuery = true , value = "select id from t_business_base_operation_gun where l_pile_id = :pileId ")
    List<Object> findGunUnderPile(@Param("pileId")long pileId);

    @Query(nativeQuery = true , value = "select s_number from t_business_base_operation_gun where l_pile_id = :pileId ")
    Set<Long> findGunNoByPileId(@Param("pileId")long pileId);
}
