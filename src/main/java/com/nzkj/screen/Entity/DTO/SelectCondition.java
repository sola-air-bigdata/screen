package com.nzkj.screen.Entity.DTO;



import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 查询条件类
 * @author hyc
 * @date 2018-6-7
 */
public class SelectCondition implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//yyyy-MM-dd
	private String startTime;
	
	private String endTime;
	
	private Date startDate;
	
	private Date endDate;
	
	private Long sellerId;
	
	// 用户ID
	private Long userId;
	
	private Long stationId;
	
	private Long pileId;
	//枪号
	private String gunNo;

	private String stationName;
	
	private String pileName;
	
	//快慢充
	private StationFastSlowEnum slowEnum;
	
	//站点多选
	private List<Long> stationList;
	
	//桩多选
	private List<Long> pileList;
	
	// 年份
	private Integer year;
	
	// 月份:yyyy-MM
	private String month;
	
	//会员类型
	private MemberTypeEnum memberTypeEnum;
	
	//区域
	private String areaName;
	
	private TimeSharingEnum timeSharingEnum;
	
	// 接口类型
	private InterfaceTypeEnum interfaceTypeEnum;
	
	// 接口id
	private Long interfaceId;
	// 会员ID
	private Long memberId;
	// 子成员ID
	private Long leaguerId;
	// vin
	private String vin;
	//集团id
	private Long groupId;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Long getPileId() {
		return pileId;
	}

	public void setPileId(Long pileId) {
		this.pileId = pileId;
	}

	public String getGunNo() {
		return gunNo;
	}

	public void setGunNo(String gunNo) {
		this.gunNo = gunNo;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getPileName() {
		return pileName;
	}

	public void setPileName(String pileName) {
		this.pileName = pileName;
	}

	public StationFastSlowEnum getSlowEnum() {
		return slowEnum;
	}

	public void setSlowEnum(StationFastSlowEnum slowEnum) {
		this.slowEnum = slowEnum;
	}

	public List<Long> getStationList() {
		return stationList;
	}

	public void setStationList(List<Long> stationList) {
		this.stationList = stationList;
	}

	public List<Long> getPileList() {
		return pileList;
	}

	public void setPileList(List<Long> pileList) {
		this.pileList = pileList;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public MemberTypeEnum getMemberTypeEnum() {
		return memberTypeEnum;
	}

	public void setMemberTypeEnum(MemberTypeEnum memberTypeEnum) {
		this.memberTypeEnum = memberTypeEnum;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public TimeSharingEnum getTimeSharingEnum() {
		return timeSharingEnum;
	}

	public void setTimeSharingEnum(TimeSharingEnum timeSharingEnum) {
		this.timeSharingEnum = timeSharingEnum;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public InterfaceTypeEnum getInterfaceTypeEnum() {
		return interfaceTypeEnum;
	}

	public void setInterfaceTypeEnum(InterfaceTypeEnum interfaceTypeEnum) {
		this.interfaceTypeEnum = interfaceTypeEnum;
	}

	public Long getInterfaceId() {
		return interfaceId;
	}

	public void setInterfaceId(Long interfaceId) {
		this.interfaceId = interfaceId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Long getLeaguerId() {
		return leaguerId;
	}

	public void setLeaguerId(Long leaguerId) {
		this.leaguerId = leaguerId;
	}

}
