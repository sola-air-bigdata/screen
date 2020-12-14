package com.nzkj.screen.Utils;

import com.nzkj.screen.Entity.DTO.PileDto;

import java.util.Objects;



public class PileUtil {

	/**
	 * 检查空闲
	 * @param protocolEnum 通讯协议
	 * @return 
	 * 科华、公交南网: 检查插枪
	 * 其他:检查空闲
	 */

	/**
	 * 是否为互联互通的桩
	 * @param pile
	 * @return
	 */
	public static boolean isInterflow(PileDto pile) {
		return pile.getHuLianInterfaceId() != null;
	}

}
