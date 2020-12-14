package com.nzkj.screen.Service;

import com.alibaba.fastjson.JSONArray;
import com.nzkj.screen.Entity.DTO.*;
import com.nzkj.screen.Entity.Station;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Liu yang
 * @Date: 2020/7/3 11:16
 * Describe:
 */
public interface StationService {
    List<StationDto> getStationList(long id);

    List<GunMonitorDto> getGunByStation(long id);

    Map<String, Object> getFaultCountMap(SelectCondition condition);

    Map<String, Object> getFaultAndRate(SelectCondition condition);

    int getMemberChargeCount(Long sellerId);

    MemberDataDto getMemberDataDto(SelectCondition condition);

    Map<Long, Long> getBalances(Long sellerId, Long userId);

    StationDto findById(long stationId);

    Map<String, Long> getTransBalance(SelectCondition condition);

    List<IncomeRankDto> getTransIncomeRank(Long sellerId, Long userId);

    Map<String, Long> getChargeBalanceSum(SelectCondition condition);

    Map<String, Object> getTransIncomeIndicator(SelectCondition condition);


}
