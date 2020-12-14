package com.nzkj.screen.Entity.DTO;

/**
 * 站点桩排设置
 * @author hyc
 * @date 2018-12-10
 *
 */
public class StationRowConfigDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	//站点
	private Long stationId;
	
	//桩
	private Long pileId;
		
	//排号
	private Integer rowNo;
	
	//位号
	private Integer columnNo;
	
	private StationDto stationDto;
	
	private PileDto pileDto;

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

	public Integer getRowNo() {
		return rowNo;
	}

	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}

	public Integer getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(Integer columnNo) {
		this.columnNo = columnNo;
	}

	public StationDto getStationDto() {
		return stationDto;
	}

	public void setStationDto(StationDto stationDto) {
		this.stationDto = stationDto;
	}

	public PileDto getPileDto() {
		return pileDto;
	}

	public void setPileDto(PileDto pileDto) {
		this.pileDto = pileDto;
	}

}
