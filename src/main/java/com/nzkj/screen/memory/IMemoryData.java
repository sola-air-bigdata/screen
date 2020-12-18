package com.nzkj.screen.memory;



import com.nzkj.screen.Entity.DTO.GunMonitorDto;
import com.nzkj.screen.Entity.DTO.PileDto;
import com.nzkj.screen.Entity.DTO.StationDto;

import java.util.List;
import java.util.Map;

/**
 * 桩、站点、区域、枪的内存管理类
 * @author 007idle 
 *
 */
public interface IMemoryData {

	/**
	 * 获取枪
	 * @param sellerId 商家ID
	 * @return
	 */
	List<GunMonitorDto> getGunBySeller(Long sellerId);

	/**
	 * 获取桩
	 * @param stationId 站点ID
	 * @return
	 */
	List<PileDto> getPileByStation(Long stationId);

	/**
	 * 获取枪
	 * @param pileId 桩ID
	 * @return
	 */
	List<GunMonitorDto> getGunByPile(Long pileId);

	/**
	 * 获取桩
	 * @param pileId 桩ID
	 * @return
	 */
	PileDto getPile(Long pileId);

	/**
	 * 获取枪
	 * @param stationId 站点ID
	 * @return
	 */
	List<GunMonitorDto> getGunByStation(Long stationId);


	/**
	 * 获取站点
	 * @param sellerId 商家ID
	 * @return
	 */
	List<StationDto> getStationBySeller(Long sellerId);

	/**
	 * 获取站点
	 * @param stationId 站点ID
	 * @return
	 */
	StationDto getStation(Long stationId);


}
