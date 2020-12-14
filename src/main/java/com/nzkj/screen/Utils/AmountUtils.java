package com.nzkj.screen.Utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 金钱单位转换工具
 * 
 * @ClassName: AmountUtils 
 * @Description: 
 * @Company: 凝智科技
 * @author lvwenzhao
 * @data 创建时间 2018-01-29
 * @version V2.0
 */
public class AmountUtils {

	// 匹配分,最大9位整数
	private static final String CURRENCY_CENT_REGEX = "^[0-9]{1,9}$";
	// 匹配元,最大7位整数,2位小数
	private static final String CURRENCY_YUAN_REGEX = "^-?\\d{1,7}(\\.\\d{1,2})?$";
	
	private static final int DEF_DIV_SCALE = 3;
	
	/**  
	* 提供精确的加法运算。  
	* @param v1 被加数  
	* @param v2 加数  
	* @return 两个参数的和  
	*/  
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	/**  
	* 提供精确的加法运算。  
	* @param v1 被加数  
	* @param v2 加数  
	* @return 两个参数的和  
	*/  
	public static String addStr(String v1, String v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).toString();
	}
	
	
	/**  
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指  
	* 定精度，以后的数字四舍五入。  
	* @param v1 被除数  
	* @param v2 除数  
	* @param scale 表示表示需要精确到小数点以后几位。  
	* @return 两个参数的商  
	*/  
	public static double div(String v1, String v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**  
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指  
	* 定精度，以后的数字四舍五入。  
	* @param v1 被除数  
	* @param v2 除数  
	* @param scale 表示表示需要精确到小数点以后几位。  
	* @return 两个参数的商  
	*/  
	public static String divOfStr(String v1, String v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	/**  
	* 提供精确的减法运算。  
	* @param v1 被减数  
	* @param v2 减数  
	* @return 两个参数的差  
	*/  
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 提供精确的减法运算。 
	 * @param v1 被减数  
	 * @param v2 减数
	 * @return String
	 */
	public static String sub(String v1, String v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.subtract(b2).toString();
	}
	
	/**  
	* 提供精确的乘法运算。  
	* @param v1 被乘数  
	* @param v2 乘数  
	* @return 两个参数的积  
	*/  
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	/**  
	* 提供精确的乘法运算。  
	* @param v1 被乘数  
	* @param v2 乘数  
	* @return 两个参数的积  
	*/  
	public static int mulInt(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).intValue();
	}
	/**  
	 * 提供精确的乘法运算。  
	 * @param v1 被乘数  
	 * @param v2 乘数  
	 * @return 两个参数的积  
	 */  
	public static String mul(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).setScale(2,BigDecimal.ROUND_HALF_UP).toString();
	}
	
	/**  
	* 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到  
	* 小数点以后10位，以后的数字四舍五入。  
	* @param v1 被除数  
	* @param v2 除数  
	* @return 两个参数的商  
	*/  
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}
	
	/**  
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指  
	* 定精度，以后的数字四舍五入。  
	* @param v1 被除数  
	* @param v2 除数  
	* @param scale 表示表示需要精确到小数点以后几位。  
	* @return 两个参数的商  
	*/  
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	

	
	/**  
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指  
	* 定精度，以后的数字四舍五入。  
	* @param v1 被除数  
	* @param v2 除数  
	* @param scale 表示表示需要精确到小数点以后几位。  
	* @return 两个参数的商  
	*/  
	public static String divStr(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	
	/**
	 * 单位元转为分:Double转为Integer
	 * 
	 * @author lvwenzhao
	 * @date 创建时间 2018-02-07
	 * @param amount 金额(单位元)
	 * @return 转换后的Integer类型值
	 */
	public static Integer convertYuan2Cent(Double amount) {
		BigDecimal dedimal = new BigDecimal(isEqNull(amount) * 100).setScale(0, BigDecimal.ROUND_HALF_UP);
		return dedimal.intValue();
	}
	
	/**
	 * 单位元转为分:String转为Integer
	 * 
	 * @author lvwenzhao
	 * @date 创建时间 2018-02-07
	 * @param amount 金额(单位元)
	 * @return 转换后的Integer类型值
	 */
	public static Integer convertYuan2Cent(String amount) {
		if (amount == null || !matchesYuan(amount)) {
			return 0;
		}
		BigDecimal dedimal = new BigDecimal(Double.parseDouble(amount) * 100).setScale(0, BigDecimal.ROUND_HALF_UP);
		return dedimal.intValue();
	}
	
	/**
	 * 单位元转为分:Integer转为Double
	 * 
	 * @author lvwenzhao
	 * @date 创建时间 2018-02-07
	 * @param amount 金额(单位分)
	 * @return 转换后的Double类型值或者0.0
	 */
	public static Double convertCent2Yuan(Integer amount) {
		BigDecimal dedimal = new BigDecimal(isEqNull(amount) / 100.0).setScale(2, BigDecimal.ROUND_HALF_UP);
		return dedimal.doubleValue();
	}
	
	/**
	 * 单位元转为分:String转为Double
	 * 
	 * @author lvwenzhao
	 * @date 创建时间 2018-02-07
	 * @param amount 金额(单位分)
	 * @return 转换后的Double类型值或者0.0
	 */
	public static Double convertCent2Yuan(String amount) {
		if (amount == null || !matchesCent(amount)) {
			return 0.0;
		}
		BigDecimal dedimal = new BigDecimal(Integer.parseInt(amount) / 100.0).setScale(2, BigDecimal.ROUND_HALF_UP);
		return dedimal.doubleValue();
	}
	
	/**
	 * 单位转换:Integer转为Double
	 * 
	 * @author lvwenzhao
	 * @date 创建时间 2018-02-07
	 * @param amount 金额(单位分)
	 * @param multiple 转换倍数,不为零
	 * @param newScale 保留小数点数
	 * @return
	 */
	public static Double convertAmount(Integer amount, double multiple, int newScale) {
		if (amount == null) {
			return 0.0;
		}
		BigDecimal dedimal = null;
		try {
			dedimal = new BigDecimal(amount / multiple).setScale(newScale, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			dedimal = new BigDecimal(0.0);
			e.printStackTrace();
		}
		return dedimal.doubleValue();
	}
	
	/**
	 * 单位转换:Double转为Integer
	 * 
	 * @author lvwenzhao
	 * @date 创建时间 2018-02-07
	 * @param amount 金额(单位分)
	 * @param multiple 转换倍数
	 * @return 转换后的值, 超过Integer上限返回0
	 */
	public static Integer convertAmount(Double amount, int multiple) {
		if (amount == null) {
			return 0;
		}
		BigDecimal dedimal = new BigDecimal(amount * multiple).setScale(0, BigDecimal.ROUND_HALF_UP);
		if (dedimal.intValue() < 0) {
			return 0;
		}
		return dedimal.intValue();
	}
	
	/**
	 * 判断是否为空
	 * 
	 * @author lvwenzhao
	 * @date 创建时间 2018-02-06
	 * @param amount Integer类型
	 * @return 0或者输入的amount值
	 */
	public static int isEqNull(Integer amount) {
		if (amount == null || !matchesCent(amount + "")) {
			return 0;
		}
		return amount;
	}
	
	/**
	 * 判断是否为空
	 * 
	 * @author lvwenzhao
	 * @date 创建时间 2018-02-06
	 * @param amount Integer类型
	 * @return 0或者输入的amount值
	 */
	public static double isEqNull(Double amount) {
		if (amount == null || !matchesYuan(amount.toString())) {
			return 0.0;
		}
		return amount;
	}
	
	/**
	 * 匹配分
	 * @param amount 待匹配字符串
	 * @return 匹配结果true或false
	 */
	public static boolean matchesCent(String amount) {
		return amount.matches(CURRENCY_CENT_REGEX);
	}
	
	/**
	 * 匹配元
	 * @param amount 待匹配字符串
	 * @return 匹配结果true或false
	 */
	public static boolean matchesYuan(String amount) {
		return amount.matches(CURRENCY_YUAN_REGEX);
	}
	
	/**
	 * 计算比率
	 * 
	 * @param amount1
	 * @param amount2
	 * @return String
	 * @author lwz
	 * @date 2018年5月29日
	 * @version
	 * @description
	 */
	public static String calculateRatio(Integer amount1, Integer amount2) {
		String flag = "";
		if (amount1 == null || amount2 == null) {
			return null;
		}
		if (amount1 == 0 && amount2 == 0) {
			return "--";
		}
		if (amount1 < 0 && amount2 < 0) {
			flag = "";
		} else if (amount1 < 0) {
			flag = "-";
		} else if (amount2 < 0) {
			flag = "-";
		}
		amount1 = Math.abs(amount1);
		amount2 = Math.abs(amount2);
		int min;
		int ratio = 1;
		if (amount1 < amount2) {
			min = amount1;
		} else {
			min = amount2;
		}
		for (int i = min; i >= 1; i--) {
			if (amount1 % i == 0 && amount2 % i == 0) {
				ratio = i;
				break;
			}
		}
		return flag + amount1 / ratio + ":" + amount2 / ratio;
	}
	
	/**
	 * 数值运算
	 * 
	 * @author lvwenzhao
	 * @date 创建时间 2018-05-31
	 * @param amount
	 * @param multiple 转换倍数,不为零
	 * @param flag true 表示乘
	 * @return
	 */
	public static String convertAmount(String amount, int multiple, boolean flag) {
		if (amount == null) {
			return null;
		}
		BigDecimal dedimal = null;
		try {
			if (flag) {
				dedimal = new BigDecimal(Double.parseDouble(amount) * multiple).setScale(BigDecimal.ROUND_HALF_UP);
			} else {
				dedimal = new BigDecimal(Double.parseDouble(amount) / multiple).setScale(2, BigDecimal.ROUND_HALF_UP);
			}

		} catch (Exception e) {
			dedimal = new BigDecimal(0.0);
			e.printStackTrace();
		}
		return dedimal.toString();
	}
	
	/**
	 * 数值运算
	 * 
	 * @author lvwenzhao
	 * @date 创建时间 2018-05-31
	 * @param amount 金额(单位分)
	 * @param multiple 转换倍数,不为零
	 * @param newScale 保留小数位
	 * @param flag true 表示乘
	 * @return
	 */
	public static String convertAmount(String amount, int multiple, int newScale, boolean flag) {
		if (amount == null) {
			return null;
		}
		BigDecimal dedimal = null;
		try {
			if (flag) {
				dedimal = new BigDecimal(Double.parseDouble(amount) * multiple).setScale(BigDecimal.ROUND_HALF_UP);
			} else {
				dedimal = new BigDecimal(Double.parseDouble(amount) / multiple).setScale(newScale,
						BigDecimal.ROUND_HALF_UP);
			}
		} catch (Exception e) {
			dedimal = new BigDecimal(0.0);
			e.printStackTrace();
		}
		return dedimal.toString();
	}
	/**
	   * 字符串转分钟
	 * @author cmq
	 * @date  2018-05-31
	 * @return minute
	 */
//	public static int stringConvertMinute(String str) {
//		if(StrUtil.isEmpty(str))return 0;
//		String [] time = str.split(":");
//		int minute = Integer.valueOf(time[0])*60+Integer.valueOf(time[1]);
//		return minute;
//	}
	/**
	 * 分钟转字符串
	 * @author cmq
	 * @date  2018-05-31
	 * @return minute
	 */
	public static String minuteConvertString(int time) {
		int hour = time/60;
		int minute = time%60;
		String timeStr = "";
		if(hour < 10) {
			timeStr += "0"+hour+":";
		}else {
			timeStr += hour+":";
		}
		if(minute < 10) {
			timeStr += "0"+minute;
		}else {
			timeStr += minute;
		}
		return timeStr;
	}
	
	/**
	 * 分钟转字符串
	 * @author cmq
	 * @date  2018-05-31
	 * @return minute
	 */
	public static String minConvertString(short time) {
		int hour = time/60;
		int minute = time%60;
		String timeStr = "";
		if(hour < 10) {
			timeStr += "0"+hour+":";
		}else {
			timeStr += hour+":";
		}
		if(minute < 10) {
			timeStr += "0"+minute;
		}else {
			timeStr += minute;
		}
		return timeStr;
	}
	
	/**
	   * 当前时间的时分转换成分总和
	 * @param time 
	 * @return
	 */
//	public static int timeConvertMinSum(Date time) {
//		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//		 String format = sdf.format(time);
//		 int MinSum = stringConvertMinute(format);
//		 return MinSum;
//	}
}
