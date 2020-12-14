package com.nzkj.screen.Entity.DTO;

import java.io.Serializable;

/**
 * 车辆基础信息上行（异步）
 * @author LiuLulin
 *
 */
public class CarInfo  implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Vin;//车辆识别码
    private String BatDate;//电池组生产日期
    private String BatGroupSN;//电池组序号
    private String BatMan;//电池生产厂商名
    private String BatPropertyRightTag;//电池组产权标识
    private String BatRateCap;//动力蓄电池组额定容量
    private String BatRateTVol;//动力蓄电池额定总电压
    private String BatTVolBeforeCharge;//充电前动力蓄电池总电压
    private String BatType;//动力蓄电池类型         01H：铅酸电池，02H：镍氢电池，03H：磷酸铁锂电池，04H：锰酸锂电池，05H：钴酸锂电池，06H：三元材料电池，07H：聚合物锂电池，08H：钛酸锂电池FFH：其他电池
    private String Res;//预留
	

    
	public String getVin() {
		return Vin;
	}
	public void setVin(String vin) {
		Vin = vin;
	}
	public String getBatDate() {
		return BatDate;
	}
	public void setBatDate(String batDate) {
		BatDate = batDate;
	}
	public String getBatGroupSN() {
		return BatGroupSN;
	}
	public void setBatGroupSN(String batGroupSN) {
		BatGroupSN = batGroupSN;
	}
	public String getBatMan() {
		return BatMan;
	}
	public void setBatMan(String batMan) {
		BatMan = batMan;
	}
	public String getBatPropertyRightTag() {
		return BatPropertyRightTag;
	}
	public void setBatPropertyRightTag(String batPropertyRightTag) {
		BatPropertyRightTag = batPropertyRightTag;
	}
	public String getBatRateCap() {
		return BatRateCap;
	}
	public void setBatRateCap(String batRateCap) {
		BatRateCap = batRateCap;
	}
	public String getBatRateTVol() {
		return BatRateTVol;
	}
	public void setBatRateTVol(String batRateTVol) {
		BatRateTVol = batRateTVol;
	}
	public String getBatTVolBeforeCharge() {
		return BatTVolBeforeCharge;
	}
	public void setBatTVolBeforeCharge(String batTVolBeforeCharge) {
		BatTVolBeforeCharge = batTVolBeforeCharge;
	}
	public String getBatType() {
		return BatType;
	}
	public void setBatType(String batType) {
		BatType = batType;
	}
	public String getRes() {
		return Res;
	}
	public void setRes(String res) {
		Res = res;
	}
    
    
    

}