package com.nzkj.screen.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;



/**
 * 转换工具类
 * @ClassName:  ParseUtils   
 * @author: zyr 
 * @date:   2018年9月20日 下午7:49:49   
 *
 */
public class ParseUtils {
	
	/**
	 * 分转元
	 * @param penny
	 * @return
	 * @author: zyr 
	 * @date:   2018年9月20日 下午7:50:11
	 */
	public static BigDecimal pennyParseYuan (Long penny){
		if (penny == null) {
			return new BigDecimal(0);
		}
		String string = String.valueOf(penny);
		if(string.startsWith("-")){			
			String substring = string.substring(string.indexOf("-")+1,string.length());
			long parseLong = Long.parseLong(substring);
			BigDecimal divide = new BigDecimal(parseLong).divide(new BigDecimal(100));
			return divide;
		}else{
			BigDecimal divide = new BigDecimal(penny).divide(new BigDecimal(100));
			return divide;
		}
	}
	/**
	 * 计算占电比
	 * @param jfpg
	 * @param zong
	 * @return
	 */
	public static String chargingPercent(Long jfpg,Long zong){
		BigDecimal zhan = new BigDecimal(jfpg).divide(new BigDecimal(zong),3,BigDecimal.ROUND_HALF_UP);
		BigDecimal last=zhan.multiply(new BigDecimal(100));
		return last.toString();
	}
	
	/**
	 * wh转kwh
	 * @param wh
	 * @return
	 * @author: zyr 
	 * @date:   2018年9月20日 下午7:52:33
	 */
	public static BigDecimal whParseKwh (Long wh){
		if(wh!=null) {
		String string = String.valueOf(wh);
		if(string.startsWith("-")){			
			String substring = string.substring(string.indexOf("-")+1,string.length());
			long parseLong = Long.parseLong(substring);
			BigDecimal divide = new BigDecimal(parseLong).divide(new BigDecimal(1000));
			return divide;
		}else{
			BigDecimal divide = new BigDecimal(wh).divide(new BigDecimal(1000));
			return divide;
		}
		}
		 return BigDecimal.ZERO;
		
	}
	
	
	/**
	 * 分转元不去掉负号
	 * @param penny
	 * @return
	 * @author: zyr 
	 * @date:   2018年9月20日 下午7:50:11
	 */
	public static String pennyParseYuan1 (Long penny){
		if (penny == null) {
			return new BigDecimal(0)+"";
		}
		String string = String.valueOf(penny);
		if(string.startsWith("-")){			
			String substring = string.substring(string.indexOf("-")+1,string.length());
			long parseLong = Long.parseLong(substring);
			BigDecimal divide = new BigDecimal(parseLong).divide(new BigDecimal(100));
			return "-"+divide+"";
		}else{
			BigDecimal divide = new BigDecimal(penny).divide(new BigDecimal(100));
			return divide+"";
		}
	}
	
	/**
	 * wh转kwh不去掉负号
	 * @param wh
	 * @return
	 * @author: zyr 
	 * @date:   2018年9月20日 下午7:52:33
	 */
	public static String whParseKwh1 (Long wh){
		String string = String.valueOf(wh);
		if(string.startsWith("-")){			
			String substring = string.substring(string.indexOf("-")+1,string.length());
			long parseLong = Long.parseLong(substring);
			BigDecimal divide = new BigDecimal(parseLong).divide(new BigDecimal(1000));
			return "-"+divide+"";
		}else{
			BigDecimal divide = new BigDecimal(wh).divide(new BigDecimal(1000));
			return divide+"";
		}
	}
	public static BigDecimal LiTurnYuan (Long penny){
		String string = String.valueOf(penny);
		if(string.startsWith("-")){			
			String substring = string.substring(string.indexOf("-")+1,string.length());
			long parseLong = Long.parseLong(substring);
			BigDecimal divide = new BigDecimal(parseLong).divide(new BigDecimal(10000));
			return new BigDecimal(new DecimalFormat("#0.0000").format(divide));
		}else{
			BigDecimal divide = new BigDecimal(penny).divide(new BigDecimal(10000));
			return new BigDecimal(new DecimalFormat("#0.0000").format(divide));
		}
	}
	
	public static String LiTurnYuan1 (Long penny){
		String string = String.valueOf(penny);
		if(string.startsWith("-")){			
			String substring = string.substring(string.indexOf("-")+1,string.length());
			long parseLong = Long.parseLong(substring);
			BigDecimal divide = new BigDecimal(parseLong).divide(new BigDecimal(10000));
			//return divide+"";
			return "-"+new DecimalFormat("#0.0000").format(divide);
		}else{
			BigDecimal divide = new BigDecimal(penny).divide(new BigDecimal(10000));
			
			return new DecimalFormat("#0.0000").format(divide);
		}
	}
	public static String LiTurnYuan (Integer penny){
		String string = String.valueOf(penny);
		if(string.startsWith("-")){			
			String substring = string.substring(string.indexOf("-")+1,string.length());
			long parseLong = Long.parseLong(substring);
			BigDecimal divide = new BigDecimal(parseLong).divide(new BigDecimal(10000));
			//return divide+"";
			return "-"+new DecimalFormat("#0.0000").format(divide);
		}else{
			BigDecimal divide = new BigDecimal(penny).divide(new BigDecimal(10000));
			
			return new DecimalFormat("#0.0000").format(divide);
		}
	}
	public static String LiTurnYuan (Double penny){
		String string = String.valueOf(penny);
		if(string.startsWith("-")){			
			String substring = string.substring(string.indexOf("-")+1,string.length());
			long parseLong = Long.parseLong(substring);
			BigDecimal divide = new BigDecimal(parseLong).divide(new BigDecimal(10000));
			//return divide+"";
			return "-"+new DecimalFormat("#0.0000").format(divide);
		}else{
			BigDecimal divide = new BigDecimal(penny).divide(new BigDecimal(10000));
			
			return new DecimalFormat("#0.0000").format(divide);
		}
	}
	
	
	/**
	 * BigDecimal 保留2小数
	 * @param d
	 * @return
	 */
	public static BigDecimal keepTwoValue(BigDecimal d){
			if(d !=null ) {
			BigDecimal num1 = new BigDecimal("0.006");
			BigDecimal aBigDecimal = d.multiply(num1);
			BigDecimal setScale = aBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
	        return setScale;
			}else {
			return BigDecimal.ZERO;
			}
	}	
	

	/**
	 * BigDecimal 数据类型转换
	 * @param
	 * @return
	 */
	public static BigDecimal keepTwoValue1(BigDecimal d){
			if(d !=null ) {
				d = d.setScale(0, BigDecimal.ROUND_HALF_UP);
				return d;
			}else {
				return BigDecimal.ZERO;
			}
	}	
	
	
	public static BigDecimal value(BigDecimal value, Integer num) {
		if(value !=null ) {
			value = value.divide(BigDecimal.valueOf(num));
			BigDecimal numadd = value.setScale(2, BigDecimal.ROUND_CEILING);
			return numadd;
		}else {
			return BigDecimal.ZERO;
		}
		
	}
	
	public static Integer num(Integer value) {
		if(value !=null ) {
			return value;
		}else {
			return 0;
		}
		
	}
	
	
	
	/**
     * @Describe 获取两日期相差天数
     * @Param start - end
     * @Return
     * @Auther xingyu.lu
     * @Date 18/3/29 18:16
     */
    public static int dateDiffer(Date start, Date end) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        Long diff =  end.getTime() - start.getTime() ;
        // 计算差多少天
//        Long day = diff / nd;
        // 计算差多少小时
//        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒
//        long sec = diff % nd % nh % nm / ns;
        return (int)min;
    }
    
    /**
     * 计算电量
     * @param
     * @param
     * @return
     */
    public static BigDecimal voltameterCheck(Long sumPower) {
    	BigDecimal	power =  new BigDecimal(0);
    	BigDecimal bigDecimal = new BigDecimal(sumPower);
    	BigDecimal bigDecimal1 = new BigDecimal(1000);
    	if(sumPower!=null) {
    		power = bigDecimal.divide(bigDecimal1, 2, RoundingMode.HALF_UP);
    		return power;
    	}
		return power;
       
        
    }


	
	
	
	
	
	
	
}
