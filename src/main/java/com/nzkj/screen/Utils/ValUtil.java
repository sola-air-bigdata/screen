package com.nzkj.screen.Utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 判断结果工具类
 * @author LLL
 * @date 2018年10月24日
 * @descrption
 */
public class ValUtil {
	public  final static String     EMPTY = "";
	private final static SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public  final static String     INT_ZERO = "0";
	private final static Integer    INTEGER_ZERO = 0;
	private final static Long       LONG_ZERO = 0L;
	private final static Float      FLOAT_ZERO = 0F;
	private final static Double     DOUBLE_ZERO = 0D;
	private final static BigDecimal BIGDECIMAL_ZERO = BigDecimal.ZERO;
	private final static BigInteger BIG_ING_ZERO = BigInteger.ZERO;
	private ValUtil() {
	}
	
	
	/*********Float**************/
	public static String val(Float f){
		return f == null ? INT_ZERO : f.toString();
	}
	public static String val(Float f,String varf){
		return f == null ? varf : f.toString();
	}
	public static Float valN(Float f){
		return f == null ? FLOAT_ZERO : f;
	}
	public static Float valN(Float f ,Float defalValue){
		return f == null ? defalValue : f;
	}
	
	
	
	/*********Long**************/
	public static String val(Long value) {
		return value == null ? INT_ZERO : value.toString();
	}
	public static String val(Long value , String defaulVal) {
		return value == null ? defaulVal : value.toString();
	}
	public static Long valN(Long date ){
		return date == null ? LONG_ZERO:date;
	}
	public static Long valN(Long date ,Long defalValue){
		return date == null ? defalValue:date;
	}
	
	/*********BigInteger**************/
	public static String val(BigInteger f){
		return f == null ? INT_ZERO : f.toString();
	}
	public static String val(BigInteger f , String defaultVal) {
		return f == null ? defaultVal : f.toString();
	}
	public static BigInteger valN(BigInteger f) {
		return f == null ? BIG_ING_ZERO : f;
	}
	public static BigInteger valN(BigInteger f , BigInteger defaultVal) {
		return f == null ? defaultVal : f;
	}
	
	/*********Double**************/
	public static String val(Double f){
		return f == null ? INT_ZERO : f.toString();
	}
	public static String val(Double f , String defaultVal) {
		return f == null ? defaultVal : f.toString();
	}
	public static Double valN (Double f) {
		return f == null ? DOUBLE_ZERO : f;
	}
	public static Double valN (Double f , Double defaultVal) {
		return f == null ? defaultVal : f;
	}
	
	/*********Integer**************/
	public static String val(Integer value){
		return value == null ? INT_ZERO : value.toString();
	}
	public static String val(Integer value , String defaulVal) {
		return value == null ? defaulVal : value.toString();
	}
	public static Integer valN(Integer f) {
		return f == null ? INTEGER_ZERO : f;
	}
	public static Integer valN(Integer f , Integer defaultVal) {
		return f == null ? defaultVal : f;
	}
	
	
	/*********String**************/
	public static String val(String value){
		return value == null ? EMPTY : value;
	}
	public static String val(String value , String defaultVal) {
		return value == null  ? defaultVal : value;
	}
	
	/*********Date**************/
	public static String val(Date date){
		if(date == null)
			return EMPTY;
		synchronized (DEFAULT_DATE_FORMAT) {
			return DEFAULT_DATE_FORMAT.format(date);
		}
	}
	public static String val(Date date,String format){
		if(date == null)
			return EMPTY;
		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * @param Date 2018-10-24 21:59:06
	 * @return
	 */
	public static Date valN(String Date){
		if (Date == null) {
			return null;
		}
		try {
			return DEFAULT_DATE_FORMAT.parse(Date);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 
	 * @param Date
	 * @param format
	 * @return
	 */
	public static Date valN(String Date , SimpleDateFormat format ){
		if (Date == null) {
			return null;
		}
		try {
			return format.parse(Date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	
	/*********Short**************/
	public static String val(Short value) {
		return value == null ? INT_ZERO : value.toString();
	}
	public static String val(Short value , String defaulVal) {
		return value == null ? defaulVal : value.toString();
	}
	
	
	/*********Object**************/
	public static String val(Object obj) {
		return obj == null ? EMPTY : obj.toString();
	}
	
	/*********BigDecimal**************/
	
	public static String val(BigDecimal decimal){
		return decimal == null ? INT_ZERO : decimal.toString(); 
	}
	public static String val(BigDecimal decimal , String defaulVal){
		return decimal == null ? defaulVal : decimal.toString(); 
	}
	public static BigDecimal valN(BigDecimal f) {
		return f == null ? BIGDECIMAL_ZERO : f;
	}
	public static BigDecimal valN(BigDecimal f , BigDecimal defaultVal) {
		return f == null ? defaultVal : f;
	}
   
    /*********isEmpty**************/
    public static boolean isNotEmpty(String val) {
    	return val != null && !"".equals(val.trim());
    }
	public static boolean isEmpty(String val) {
		return val == null || "".equals(val.trim());
	}
	public static boolean isNotEmpty(Object val) {
		return val != null ;
	}
	public static boolean isEmpty(Object val) {
		return val == null ;
	}
	
	/*********toLong**************/
	public static Long toLong(Object val ) {
		return toLong(val, null);
	}
	/**
	 * toLong
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static Long toLong(Object val ,Long defaultVal) {
		if (val == null) {
			return defaultVal;
		}else{
			return Long.valueOf(val.toString());
		}
	}
	
	/*********toFloat**************/
	public static Float toFloat(Object val ) {
		return toFloat(val, null);
	}
	/**
	 * toFloat
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static Float toFloat(Object val ,Float defaultVal) {
		if (val == null) {
			return defaultVal;
		}else{
			return Float.valueOf(val.toString());
		}
	}
	/*********toBigInteger**************/
	public static BigInteger toBigInteger(Object val ) {
		return toBigInteger(val, null);
	}
	/**
	 * toBigInteger
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static BigInteger toBigInteger(Object val ,BigInteger defaultVal) {
		if (val == null) {
			return defaultVal;
		}else{
			return BigInteger.valueOf(toLong(val));
		}
	}
	/*********toDouble**************/
	public static Double toDouble(Object val ) {
		return toDouble(val, null);
	}
	/**
	 * toDouble
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static Double toDouble(Object val ,Double defaultVal) {
		if (val == null) {
			return defaultVal;
		}else{
			return Double.valueOf(val.toString());
		}
	}
	
	/*********toInteger**************/
	public static Integer toInteger(Object val ) {
		return toInteger(val, null);
	}
	/**
	 * toInteger
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static Integer toInteger(Object val ,Integer defaultVal) {
		if (val == null) {
			return defaultVal;
		}else{
			return Integer.valueOf(val.toString());
		}
	}
	/*********toString**************/
	public static String toString(Object val ) {
		return toString(val, EMPTY);
	}
	/**
	 * toString
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static String toString(Object val ,String defaultVal) {
		if (val == null) {
			return defaultVal;
		}else{
			return val.toString();
		}
	}
	
	/*********toBoolean**************/
	public static Boolean toBoolean(Object val) {
		return toBoolean(val, null);
	}
	/**
	 * toBoolean
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static Boolean toBoolean(Object val, Boolean defaultVal) {
		if (val == null) {
			return defaultVal;
		}
		return Boolean.valueOf(val.toString());
	}
	
	/*********toDate**************/
	public static Date toDate(Object val ) {
		return toDate(val,null);
	}
	
	public static Date getDate(Object val ,String fmt) {
		if (val == null) {
			return null;
		}else{
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(fmt);
				return sdf.parse(val.toString());
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	/**
	 * toDate
	 * @param val
	 * @param defaultVal
	 * @return
	 */
	public static Date toDate(Object val ,Date defaultVal) {
		if (val == null) {
			return defaultVal;
		}else{
			try {
				return DEFAULT_DATE_FORMAT.parse(val.toString());
			} catch (ParseException e) {
				e.printStackTrace();
				return defaultVal;
			}
		}
	}
	
	/*********toDateStr**************/
	public static String toDateStr(Object val ,String fmt) {
		return toDateStr(val,null ,fmt);
	}
	/**
	 * toDateStr
	 * @param val
	 * @param defaultVal
	 * @param fmt
	 * @return
	 */
	public static String toDateStr(Object val ,String defaultVal ,String fmt) {
		if (val == null) {
			return defaultVal;
		}else{
			DateFormat formatter = new SimpleDateFormat(fmt);
			try {
				synchronized (formatter) {
					return formatter.format(DEFAULT_DATE_FORMAT.parse(val.toString()));
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return defaultVal;
			}
		}
	}
	/*********toDoubleDiv**************/
	public static Double toDoubleDiv(Object val ,Double val2 , int scale){
		if (val == null) {
			return DOUBLE_ZERO;
		}else{
			return div(Double.valueOf(val.toString()), val2, scale);
		}
	}
	/*********toDoubleMul**************/
	public static Double toDoubleMul(Object val ,Double val2 , int scale){
		if (val == null) {
			return DOUBLE_ZERO;
		}else{
			return mul(Double.valueOf(val.toString()), val2, scale);
		}
	}
	
	public static double mul(double v1, double v2, int scale) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static BigDecimal toDivBigDecimal(Object obj01,Object obj02,int scale){
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		if(obj01 == null || "".equals(obj01)){
			return new BigDecimal(0);
		}
		if(obj02 == null || "".equals(obj02) || "0".equals(obj02.toString())){
			return new BigDecimal(0);
		}
		return  new BigDecimal(obj01.toString()).divide(new BigDecimal(obj02.toString()) , scale, BigDecimal.ROUND_HALF_UP);
	}
}
