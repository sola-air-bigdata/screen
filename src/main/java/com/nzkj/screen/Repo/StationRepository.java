package com.nzkj.screen.Repo;

import com.nzkj.screen.Entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @Author: Liu yang
 * @Date: 2020/7/3 11:04
 * Describe:
 */
public interface StationRepository extends JpaRepository<Station, Long> {

//    List<Station> findBySellIdAndDeleteFlag(BigInteger sellerId,Boolean b);

    List<Station> findBySellIdAndDeleteFlag(long sellerId,Boolean b);



    @Query(nativeQuery = true, value = "select id from t_business_base_operation_station where l_seller_id = :sellerId ")
    List<BigInteger> findIdBySellerId(@Param("sellerId") BigInteger sellerId);

    Station findById(BigInteger id);

    @Query(nativeQuery = true, value = "select id from t_business_base_operation_station where l_seller_id = :sellerId ")
    Set<Long> findIdBySeller(@Param("sellerId") long sellerId);

    /**
     * 根据站点id获取相关信息stationImage(站点图片) stationName(站点名称) stationaddres(站点地址)
     * stationRating(站点评级) stationPile(站点桩数量)
     * @param stationId
     * @param sellerId
     * @return
     */
    @Query(nativeQuery = true, value = "select st.v_list_img as image, st.v_name as name, st.v_street as address, st.i_score as score, psum.pilesum as pilesum from t_business_base_operation_station st left join (select count(0) pilesum, l_station_id from t_business_base_operation_pile where b_delete_flag = 0 group by l_station_id) psum on psum.l_station_id = st.id where st.b_delete_flag = 0 and st.id = :stationId and st.l_seller_id = :sellerId")
    Object getStationDatanum(@Param("stationId")Long stationId,@Param("sellerId") Long sellerId);




}
