package com.nzkj.screen.Utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;


/**
 * 类型强转方便模板直接引用
 * @author Administrator
 *
 */ 
public class TypeRotationUtil {


	
	/**
	 * 分转元
	 * @param msg
	 * @return
	 */
	public static String stringTurnYuan(Long msg){
		if(msg!=null&&!msg.toString().equals("0")){
			BigDecimal divide = new BigDecimal(msg).divide(new BigDecimal(100));
			return divide.toString();
		}
		return "0.0";
	}
	public static String stringLiTurnYuan(Long msg){
		if(msg!=null&&!msg.toString().equals("0")){
			BigDecimal divide = new BigDecimal(msg).divide(new BigDecimal(10000));
			return new DecimalFormat("#0.0000").format(divide);
		}
		return "0.0000";
	}
	
	
	
	/**
	 * 充电量转换为kw
	 * @param msg
	 * @return
	 */
	public static String stringTurnKw2(Long msg){
		if(msg!=null&&!msg.toString().equals("0")){
			BigDecimal divide = new BigDecimal(msg).divide(new BigDecimal(1000));
			return divide.toString();
		}
		return "0.0";
	}
	
	
	
	/**
	 * 部分单价转换
	 * @param msg
	 * @return
	 */
	public static Double stringTimeTurnYuan(Object msg){
		if(msg!=null){
			if(Integer.parseInt(msg.toString())>0){
				return (Double.valueOf((msg.toString()))/100);
			}else {
				return 0.0;
			}
		}
		return 0.0;
	}
	public static String stringTimeLiTurnYuan(Object msg){
		if(msg!=null){
			if(Integer.parseInt(msg.toString())>0){
				return new DecimalFormat("#0.0000").format(Double.valueOf((msg.toString()))/10000);
			}else {
				return "0.0000";
			}
		}
		return "0.0000";
	}
	
	/**计费规则相关单价转换*/
	public static String stringTimeLiTurnYuanJi(Object msg){
		if(msg!=null){
			if(Integer.parseInt(msg.toString())>0){
				return new DecimalFormat("#0.0000").format(Double.valueOf((msg.toString()))/10000);
			}else {
				return "";
			}
		}
		return "";
	}
	/* *
	 *  保留两位小数
	 * */
	public static String liTurnYuan2scale(Object msg){
		if(msg!=null){
			if(Integer.parseInt(msg.toString())>0){
				return new DecimalFormat("#0.00").format(Double.valueOf((msg.toString()))/100);
			}else {
				return "0.00";
			}
		}
		return "0.00";
	}
	/* *
	 *  保留四位小数
	 * */
	public static String liTurnYuan4scale(Object msg){
		if(msg!=null){
			if(Integer.parseInt(msg.toString())>0){
				return new DecimalFormat("#0.0000").format(Double.valueOf((msg.toString()))/10000);
			}else {
				return "0.0000";
			}
		}
		return "0.0000";
	}
	public static String TurnYuan2scale(Object msg){
		if(msg!=null){
			if(Long.valueOf(msg.toString())>0){
				return new DecimalFormat("0.00").format(Double.valueOf((msg.toString()))/100);
			}else {
				return "0.00";
			}
		}
		return "0.00";
	}

	public static String LiTurnYuan(String msg){
		if(msg!=null){
			if(Integer.parseInt(msg.toString())>0){
				return new DecimalFormat("#0.00").format(Double.valueOf((msg.toString()))/10000);
			}else {
				return "0.00";
			}
		}
		return "0.00";
	}
	public static String LiTurnYuan(Long msg){
		if(msg!=null){
			if(Long.valueOf(msg.toString())>0){
				return new DecimalFormat("#0.00").format(Double.valueOf((msg.toString()))/10000);
			}else {
				return "0.00";
			}
		}
		return "0.00";
	}
	public static String LiTurnYuan2(Long msg){
		if(msg!=null){
			if(Long.valueOf(msg.toString())>0){
				return new DecimalFormat("#0.00").format(Double.valueOf((msg.toString()))/100);
			}else {
				return "0.00";
			}
		}
		return "0.00";
	}
	public static String LiTurnYuan2(String msg){
		if(msg!=null){
			if(Long.valueOf(msg)>0){
				return new DecimalFormat("#0.00").format(Double.valueOf(msg)/100);
			}else {
				return "0.00";
			}
		}
		return "0.00";
	}
	public static String LiTurnYuan(Double msg){
		if(msg!=null){
			if(Double.valueOf(msg.toString())>0){
				return new DecimalFormat("#0.00").format(Double.valueOf((msg.toString()))/10000);
			}else {
				return "0.00";
			}
		}
		return "0.00";
	}
	

	public static String stringTimeTurnYuan(String msg){
		if(msg!=null){
			BigDecimal divide = new BigDecimal(msg).divide(new BigDecimal(100));
			return divide.toString();
		}
		return "0.0";
	}
	public static String stringTimeLiTurnYuan(String msg){
		if(msg!=null){
			BigDecimal divide = new BigDecimal(msg).divide(new BigDecimal(10000));
			return new DecimalFormat("#0.0000").format(divide);
		}
		return "0.0000";
	}
	public static String stringLiTurnYuan(String msg){
		if(msg!=null){
			BigDecimal divide = new BigDecimal(msg).divide(new BigDecimal(10000));
			return new DecimalFormat("#0.00").format(divide);
		}
		return "0.00";
	}
	
	
	public static String stringTimeTurnKw(String msg){
		if(msg!=null){
			BigDecimal divide = new BigDecimal(msg).divide(new BigDecimal(1000));
			return divide.toString();
		}
		return "0.0";
	}
//	public static String power2Kwh(Object power) {
//		if(power != null) {
//			 double div = NumberUtil.div(Double.valueOf(power.toString()).doubleValue(), 1000.00d, 2);
//			 return String.valueOf(div);
//		}
//		return "0.0";
//	}
}
