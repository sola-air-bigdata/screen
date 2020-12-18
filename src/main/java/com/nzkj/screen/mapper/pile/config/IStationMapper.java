package com.nzkj.screen.mapper.pile.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nzkj.screen.Entity.DTO.StationInfoDTO;
import com.nzkj.screen.Entity.Station;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 * @Author: Liu yang
 * @Date: 2020/12/15 11:10
 * Describe:
 */
@Repository
public interface IStationMapper extends BaseMapper<Station> {

    @Select("select * from t_station where id = #{id}")
    Station get(@Param("id") Long id);


//    @Select("")
//    List<Station> findBySellIdAndDeleteFlag(long sellerId, Boolean b);


    /**
     * 根据sellerId找 商家下的站点id
     * @param sellerId
     * @return
     */
    @Select("select id from t_station where seller_id = #{sellerId} and deleted = 0 ")
    List<BigInteger> findIdBySellerId(@Param("sellerId") BigInteger sellerId);

//    Station findById(BigInteger id);

    /**
     * 根据sellerId找 商家下的站点id
     * @param sellerId
     * @return
     */
    @Select("select id from t_station where seller_id = #{sellerId} and deleted = 0 ")
    Set<Long> findIdBySeller(@Param("sellerId") long sellerId);

    /**
     * 根据区域添加者ID查找其下站点ID
     * @param userId
     * @return
     */
    @Select("select id from t_station where user_id = #{userId} and seller_id = #{sellerId} and deleted = 0 ")
    Set<Long> findIdByUser(@Param("userId") long userId,@Param("sellerId") long sellerId);

    @Select("select * from t_station where seller_id = #{sellerId} and deleted = 0 ")
    List<Station> findBySeller(@Param("sellerId") long sellerId);


    /**
     * 根据站点id获取相关信息stationImage(站点图片) stationName(站点名称) stationaddres(站点地址)
     * stationRating(站点评级) stationPile(站点桩数量)
     * @param stationId
     * @param sellerId
     * @return
     */
    @Select("select st.list_img as image,st.name as name,st.street as address,st.score as score,psum.pilesum as pilesum from t_station st left join (select count(0) pilesum, station_id from t_pile where deleted = 0 group by station_id) psum on psum.station_id = st.id where st.deleted = 0 and st.id = #{stationId} and st.seller_id = #{sellerId} ")
    StationInfoDTO getStationDatanum(@Param("stationId")Long stationId, @Param("sellerId") Long sellerId);
}
