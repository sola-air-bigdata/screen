package com.nzkj.screen.Entity.DTO;
import java.io.Serializable;

/**
 * 枪dto
 * @author zhangtian
 *
 */
public class GunDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 枪号
	 */
	private String number;

	/**
	 * 桩
	 */
	private Long pileId;
	public String serial;
	
	private String name;
	

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getPileId() {
		return pileId;
	}

	public void setPileId(Long pileId) {
		this.pileId = pileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
