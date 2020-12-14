package com.nzkj.screen.Entity.DTO;

import java.util.HashMap;
import java.util.Map;

public enum CatalogEnum {
	
	KeHua(1,"科华"),HanYi(2,"海印"),YingChong(3,"宜充"),JuNeng(4,"钜能"),NingZhi(5,"凝智"),ShengHong(6,"盛弘"),
	JinWeiYuan(7,"金威源"),JuDian(8,"聚电"),JuNen(9,"聚能"),HuiJu(10,"汇聚"),KeShiDa(11,"科士达"),	AiPuLa(12,"爱普拉"),
	jinZhongMoLei(13,"金钟默勒"),ZhongKeHaiAO(14,"中科海奥"),JinFuYuan(15,"晶福元"),ZhongHeng(16,"中恒"),
	YingFeiYuan(17,"英飞源"),YinKeRui(18,"英可瑞"),AoNai(19,"奥耐"),RuiSu(20,"锐速"),EnYiTi(21,"恩亿梯"),
	HuiNeng(22,"汇能"),YiLian(23,"驿联"),YiShiTe(24,"易事特"),GuoDianNanRui(25,"国电南瑞"),KeLu(26,"科陆"),
	NanJingNanRui(27,"南京南瑞"),PuRuiTe(28,"普瑞特"),TaiTan(29,"泰坦"),AoTeXun(30,"奥特讯"),ZongYeDa(31,"众业达"),
	JiaSheng(32,"嘉盛"),KeDaZhiNeng(33,"科大智能"),GuangZhouLianHangKe(34,"广州联航科"),Unknown(35,"未知"),;
	
	
	  private	String cnName;
	  private   Integer coetx;
	
   CatalogEnum(Integer coetx, String cnName){
	    this.coetx=coetx;
	    this.cnName=cnName;
	}

	public String getCnName() {
		return cnName;
	}
	public int getCoetx() {
		return coetx;
	}

	public void setCoetx(int coetx) {
		this.coetx = coetx;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	/**
	 * 下拉列表
	 * @return
	 */
	public  final static Map<String,String> getSelect(){
    	Map<String,String> maps=new HashMap<String,String>();
		for(CatalogEnum c: CatalogEnum.values()){
			maps.put(c.name(), c.getCnName());
		}
		return maps;
	}
	
	public final static CatalogEnum getEnumByName(String name){
		if("科华".equals(name)){
			return CatalogEnum.KeHua;
		}else if("海印".equals(name)){
			return CatalogEnum.HanYi;
		}else if("宜充".equals(name)){
			return CatalogEnum.YingChong;
		}else if("钜能".equals(name)){
			return CatalogEnum.JuNeng;
		}else if("凝智".equals(name)){
			return CatalogEnum.NingZhi;
		}else if("盛弘".equals(name)){
			return CatalogEnum.ShengHong;
		}else if("金威源".equals(name)){
			return CatalogEnum.JinWeiYuan;
		}else if("聚电".equals(name)){
			return CatalogEnum.JuDian;
		}else if("聚能".equals(name)){
			return CatalogEnum.JuNen;
		}else if("汇聚".equals(name)){
			return CatalogEnum.HuiJu;
		}else if("科士达".equals(name)){
			return CatalogEnum.KeShiDa;
		}else if("爱普拉".equals(name)){
			return CatalogEnum.AiPuLa;
		}else if("金钟默勒".equals(name)){
			return CatalogEnum.jinZhongMoLei;
		}else if("中科海奥".equals(name)){
			return CatalogEnum.ZhongKeHaiAO;
		}else if("晶福元".equals(name)){
			return CatalogEnum.JinFuYuan;
		}else if("中恒".equals(name)){
			return CatalogEnum.ZhongHeng;
		}else if("英飞源".equals(name)){
			return CatalogEnum.YingFeiYuan;
		}else if("英可瑞".equals(name)){
			return CatalogEnum.YinKeRui;
		}else if("奥耐".equals(name)){
			return CatalogEnum.AoNai;
		}else if("锐速".equals(name)){
			return CatalogEnum.RuiSu;
		}else if("恩亿梯".equals(name)){
			return CatalogEnum.EnYiTi;
		}else if("汇能".equals(name)){
			return CatalogEnum.HuiNeng;
		}else if("驿联".equals(name)){
			return CatalogEnum.YiLian;
		}else if("易事特".equals(name)){
			return CatalogEnum.YiShiTe;
		}else if("国电南瑞".equals(name)){
			return CatalogEnum.GuoDianNanRui;
		}else if("科陆".equals(name)){
			return CatalogEnum.KeLu;
		}else if("南京南瑞".equals(name)){
			return CatalogEnum.NanJingNanRui;
		}else if("普瑞特".equals(name)){
			return CatalogEnum.PuRuiTe;
		}else if("泰坦".equals(name)){
			return CatalogEnum.TaiTan;
		}else if("奥特讯".equals(name)){
			return CatalogEnum.AoTeXun;
		}else if("众业达".equals(name)){
			return CatalogEnum.ZongYeDa;
		}else if("嘉盛".equals(name)){
			return CatalogEnum.JiaSheng;
		}else if("科大智能".equals(name)){
			return CatalogEnum.KeDaZhiNeng;
		}else if("广州联航科".equals(name)){
			return CatalogEnum.GuangZhouLianHangKe;
		}else if("未知".equals(name)) {
			return CatalogEnum.Unknown;
		}
		
		return null;
	}
	
}
