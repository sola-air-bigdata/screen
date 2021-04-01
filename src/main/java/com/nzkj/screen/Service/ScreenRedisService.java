package com.nzkj.screen.Service;

import com.alibaba.fastjson.JSONArray;
import com.nzkj.screen.Entity.DTO.StationDto;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: Liu yang
 * @Date: 2020/11/18 9:18
 * Describe:
 */
public interface ScreenRedisService {

    public Map<String, Object> doGetStationSumInfoByID( Long stationID, Date date);

    public Map<String, Object> doGetStationSumViewData( Long stationId);

    List<Map<String, Object>> getCarSumData(Long stationId);

    List<Object> doGetGunData(Long stationID);

    List<StationDto> doGetStationList();

    Map<String, Object> doGetStationInfoByID(Long stationID);

    Map<String, Object> doGetStationEquipmentInfo(Long stationID);

    JSONArray getStationInServiceMapData();

    List<StationDto> getStationDto();

    Map<String, Object> doHistoryTotalCharging();

    List<Map<String, Object>> doGetUserServiceNum(Long stationID);

    Map<String, Object> doGetStationMemData(Long stationID);

    List<Map<String, Object>> getMemberAddByMonth (Long stationID);

    List<Map<String, Object>> getMemberByHours(Long stationId);

    Map<String, Object> getFansProportion(Long stationId);

    List<Map<String, Object>> getConsumptionRanking(Long stationId);


}
