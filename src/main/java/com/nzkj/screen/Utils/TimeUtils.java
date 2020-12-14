package com.nzkj.screen.Utils;

import com.nzkj.screen.Entity.DTO.TimeSharingEnum;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 操作时间util类
* @ClassName: TimeUtils 
* @Description: 
* @Company: 凝智科技
* @author: Administrator 
* @date 2017年8月11日
* @version V1.0
 */
public class TimeUtils {
	

	private static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final String DEFAULT_TIME_PATTERNS = "yyyy-MM-dd";
	private static final String YEAR_MONTH_PATTERN = "yyyy-MM";
	/**
	 * 带格式参数的格式化时间函数
	* @Title: formatTimeToString 
	* @Description: 
	* @param time
	* @param pattern
	* @return String
	* @date 2017年8月11日
	* @version V1.0 
	* @Company: 凝智科技
	 */
//	public static String formatTimeToString(Date time, String pattern)  {
//		if (StringUtils.isEmpty(pattern)) {
//			pattern = DEFAULT_TIME_PATTERN;
//		}
//		DateFormat format = new SimpleDateFormat(pattern);
//		return format.format(time);
//	}

	/**
	 * n月前或后 + -
	 *
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date addMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);

		return new Date(cal.getTime().getTime());
	}

	/**
	 * 格式化完整时间
	 *
	 * @param date 日期
	 * @return 格式化时间
	 */
	public static String formatFullTime(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * n天前或后 + -
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date addDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);

		return new Date(cal.getTime().getTime());
	}
	
	/**
	* 使用默认格式的时间格式化函数
	* @Title: formatTimeToString 
	* @Description: 
	* @param time
	* @return String
	* @date 2017年8月11日
	* @version V1.0 
	* @Company: 凝智科技
	 */
	public static String formatTimeToString(Date time)  {
		if(time!=null) {
			DateFormat format = new SimpleDateFormat(DEFAULT_TIME_PATTERN);
			return format.format(time);
		}
		return "-";
		
	}
	public static String formatTimeToDay(Date time)  {
		if(time!=null) {
			DateFormat format = new SimpleDateFormat(DEFAULT_TIME_PATTERNS);
			return format.format(time);
		}
		return "-";
		
	}

	/**
	 * 根据某个时间获取该月的第一天
	 * @param date
	 * @return
	 * @author hyc
	 */
	public static Date getStartDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 根据某个时间获取该月的最后一天
	 * @param date
	 * @return
	 * @author hyc
	 */
	public static Date getEndDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 0);
		return cal.getTime();
	}


	/**
	 * 根据开始-结束时间获取分段时间
	 *
	 * @param timeSharingEnum 分时段枚举
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return List
	 */
	public static List<String> getTimeList(Date startTime, Date endTime, TimeSharingEnum timeSharingEnum) {
		List<String> timeList = new ArrayList<>();
		if (startTime == null) {
			return timeList;
		}
		if (endTime == null) {
			endTime = new Date();
		}
		// 开始时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		if (endTime.before(calendar.getTime())) {
			return timeList;
		}
		// 默认月
		if (timeSharingEnum == null) {
			timeSharingEnum = TimeSharingEnum.Month;
		}
		if (timeSharingEnum == TimeSharingEnum.Year) {
			calendar.set(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			while (!endTime.before(calendar.getTime())) {
				timeList.add(TimeUtils.format(calendar.getTime(), "yyyy"));
				calendar.add(Calendar.YEAR, 1);
			}
		} else if (timeSharingEnum == TimeSharingEnum.Month) {
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			while (!endTime.before(calendar.getTime())) {
				timeList.add(TimeUtils.format(calendar.getTime(), "yyyy-MM"));
				calendar.add(Calendar.MONTH, 1);
			}
		} else if (timeSharingEnum == TimeSharingEnum.Day) {
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			while (!endTime.before(calendar.getTime())) {
				timeList.add(TimeUtils.format(calendar.getTime(), "yyyy-MM-dd"));
				calendar.add(Calendar.DATE, 1);
			}
		} else if (timeSharingEnum == TimeSharingEnum.Hour) {
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			while (!endTime.before(calendar.getTime())) {
				timeList.add(TimeUtils.format(calendar.getTime(), "yyyy-MM-dd HH"));
				calendar.add(Calendar.HOUR, 1);
			}
		}
		return timeList;
	}

	/**
	 * 转换时间
	 *
	 * @param dateStr
	 * @param fmt
	 * @return
	 */
	public static Date parse(String dateStr, String fmt) {
		DateFormat formatter = new SimpleDateFormat(fmt);
		try {
			return formatter.parse(dateStr);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 增删对应时间（单位：天）
	 * @param num 天数
	 * @param newDate 修改的时间
	 * @return
	 */
    public static Date plusDay(int num,Date time) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(time);
        ca.add(Calendar.DATE, num);
        time = ca.getTime();
        return time;
     }
    
    /**
	 * 获取num天前（后）的凌晨
	 * @param num 天数
	 * @param time 时间
	 * @return Date
	 */
	public static Date plusDayZeroTime(int num, Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.DATE, num);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	
	 /**
     * 得到某一天的凌晨时间
     *
     * @param date
     * @return
     */
    public static Date getDayStart(Date date) {
        if (date == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 得到某一天的23:59:59时间
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        if (date == null)
            return date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    
    
    //获得当前年份
    public static int getCurrentYear() {
    	Calendar ca = Calendar.getInstance();
    	return ca.get(Calendar.YEAR);
    }
    //获得当前月份
    public static int getCurrentMonth() {
    	Calendar ca = Calendar.getInstance();
    	//因为Calendar 的月份从0开始算,所以当前月份正确 +=1
    	return ca.get(Calendar.MONTH)+1;
    }
    
    public static Date getParse(String time) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_TIME_PATTERN);  
        Date date = sdf.parse(time);	
        return date;
    }
    
    public static Date getParses(String time) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_TIME_PATTERNS);  
        Date date = sdf.parse(time);	
        return date;
    }
    /**
     * 把传过来的yyyy-MM格式字符串，转成时间Calendar对象操作
     * @param time
     * @return calendar
     * @author cmq
     * @date 2018-2-3
     * */
    public static Calendar getYearMonthParse(String time) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH_PATTERN);  
        Date date = sdf.parse(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    /**
     * 单位转换：毫秒转换成  ---“0 天 0 小时 0 分 0 秒” 格式
     * @param time ：时间差（毫秒）
     * @return
     */
	public static String getTimeUnitConversion(Long time) {
		try {
			 time = time /1000;
			long day = time / (24 * 3600);
			long hour = time % (24 * 3600) / 3600;
			long minute = time % 3600 / 60;
			long second = time % 60;
			return day + "天" + hour + "小时" + minute + "分" + second + "秒";
		} catch (Exception e) {
			return "0 天 0 小时 0 分 0 秒";
		}

	}
	
	/**
	 * 毫秒转换为时分秒
	 * @param second
	 * @return
	 */
	public static String secondToHours(Long time) {
		try {
			long hour = time / 3600;
			long minute = time % 3600 / 60;
			long second = time % 60;
			return  hour + "小时" + minute + "分" + second + "秒";
		} catch (Exception e) {
			return " 0 小时 0 分 0 秒";
		}
	}
	
	/**
	 * 秒转小时 四舍五入保留两位小数
	 * @param time
	 * 
	 */
	public static String secondToHour(Long time){
		if(time!=null){
			BigDecimal num=new BigDecimal(time).divide(new BigDecimal(3600),2, BigDecimal.ROUND_HALF_UP);
			return num.toString();
		}
		return "0";
	}
	
	
	/**
	 * 毫秒转小时(保留两位小数)
	 * @param ms
	 * @return
	 */
	public static String toHours(Long ms){
		DecimalFormat df = new DecimalFormat("0.00");
		String hour =df.format(ms.doubleValue() / 3600);
		return hour;
	}
	
	/**
     * 格式化时间
     * 
     * @param date
     * @param fmt
     * @return
     */
    public static String format(Date date, String fmt) {
        DateFormat formatter = new SimpleDateFormat(fmt);
        return formatter.format(date);
    }
 
    /**
     * 获取前后num天(包含今天)的0点日期集合
     * 
     * @param num 天数 小于0表示之前
     * @param time
     * @return List<Date>
     * @author lwz
     * @date 2018年5月25日
     * @version
     * @description
     */
    public static List<Date> getPlusDayZeroList(int num, Date time) {
		Calendar calendar = Calendar.getInstance();
		Date zero = new Date();
		List<Date> dateList = new ArrayList<>();
		int start = 0;
		int end = 0;
		if (num < 0) {
			start = num + 1;
			end = 0;
		} else {
			start = 0;
			end = num - 1;
		}
		for (int i = start; i <= end; i++) {
			calendar.setTime(time);
			calendar.add(Calendar.DATE, i);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			zero = calendar.getTime();
			dateList.add(zero);
		}
		return dateList;
	}
    /**
     * 获取近num个月(包含本月)的开始日期集合
     * @param num
     * @param time
     * @return List<Date>
     * @author lwz
     * @date 2018年5月25日
     * @version
     * @description
     */
	public static List<Date> getPlusMonthZeroList(int num, Date time) {
		Calendar calendar = Calendar.getInstance();
		List<Date> dateList = new ArrayList<>(num);
		for (int i = num - 1; i >= 0; i--) {
			calendar.setTime(time);
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - i); // 逐次往前推1个月
			// 设置时间为0
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY),
					calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			// 设置每月第一天
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			dateList.add(calendar.getTime());
		}
		return dateList;
	}

	
    /**
     * 返回年日时分
     * @param second
     * @return 
     * @return
     */
	public static  String secondToTime(long second) {
		long year = second / 31536000;//转换年
		second = second % 31536000;//剩余秒数
        long days = second / 86400;//转换天数
        second = second % 86400;//剩余秒数
        long hours = second / 3600;//转换小时数
        second = second % 3600;//剩余秒数
        long minutes = second / 60;//转换分钟
        second = second % 60;//剩余秒数
        if(0<year){
        	return year+"年"+days + "天"+hours+"小时"+minutes+"分";
        }else if (0 < days){
            return days + "天"+hours+"小时"+minutes+"分";
        }else {
            return hours+"小时"+minutes+"分";
        }
    }

	
	//获得当前月份第一天
    public static String getCurrentMonthStart() {
    	Calendar calendar = Calendar.getInstance();
    	return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-01";
    }
    
	/**
	 * 传入月份返回当年的查询月的开始时间和结束时间
	 * @param month
	 * @return
	 * @author: zyr 
	 * @date:   2018年9月26日 下午5:16:37
	 */
	public static Map<String,Object> getStartDateAndEndDate(String month){
		Map<String,Object> map = new HashMap<>();
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		startTime.set(Calendar.MONTH, Integer.valueOf(month)-1);
		startTime.set(Calendar.DAY_OF_MONTH, 1);
		startTime.set(Calendar.HOUR_OF_DAY, 0);
		startTime.set(Calendar.MINUTE, 0);
		startTime.set(Calendar.SECOND, 0);
		map.put("startDate", startTime.getTime());
		endTime.set(Calendar.MONTH, Integer.valueOf(month)-1);
		endTime.set(Calendar.DAY_OF_MONTH, endTime.getActualMaximum(Calendar.DAY_OF_MONTH));
		endTime.set(Calendar.HOUR_OF_DAY, 0);
		endTime.set(Calendar.MINUTE, 0);
		endTime.set(Calendar.SECOND, 0);
		map.put("endDate", endTime.getTime());
		return map;
	}
	/**
	 * 计算两个 yyyy-MM-dd HH:mm:ss 格式字符串日期之间相隔的分钟数
	 * @param startTime
	 * @param endTime
	 * @return minute
	 * @author: cmq
	 * @throws ParseException 
	 * @date:   2019-03-21
	 */
//	public static String between(String startTime,String endTime) throws ParseException {
//		long between = DateUtil.between(getParse(startTime), getParse(endTime), DateUnit.MINUTE);
//		return String.valueOf(between);
//	}
	
    /**
     * 得到今天凌晨的时间
     * 
     * @return
    
     */
    public static Date getTodayStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 得到今天23:59:59时间
     * 
     * @return
     */
    public static Date getTodayEnd() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }
    
    /**
     * 返回当月开始时间和结束时间，上月开始时间和结束时间
     * @author lemon
     * @return key--lastStart 上月开始时间 ；key--lastEnd  上月结束书时间报；  key--thisStart  本月开始时间；  key--thisEnd  本月结束时间
     * @throws ParseException
     */
    public static  Map<String,Object> getLastMonSAndE() throws ParseException {
    	
    	Map<String,Object> map = new HashMap<>();
    	Calendar c2=Calendar.getInstance();
		c2.add(Calendar.MONTH, -2);
		SimpleDateFormat sdfl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int lastMonthMaxDay2=c2.getActualMaximum(Calendar.DAY_OF_MONTH);
		c2.set(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), lastMonthMaxDay2, 23, 59, 59);
		
		String gtimel = sdfl.format(c2.getTime()); //上上月最后一天
		SimpleDateFormat sdfl2 = new SimpleDateFormat("yyyy-MM-01 00:00:00");
		String gtimel2 = sdfl2.format(c2.getTime()); //上上月第一天
		map.put("lastlastStart", gtimel2);
		map.put("lastlastEnd", gtimel);
		
		Calendar c=Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int lastMonthMaxDay=c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);
		
		String gtime = sdf.format(c.getTime()); //上月最后一天
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01 00:00:00");
		String gtime2 = sdf2.format(c.getTime()); //上月第一天
		
		map.put("lastStart", gtime2);
		map.put("lastEnd", gtime);
		
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=Calendar.getInstance();
		Date theDate=calendar.getTime();
		GregorianCalendar gcLast=(GregorianCalendar)Calendar.getInstance();
		gcLast.setTime(theDate);
		//设置为第一天
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first=sf.format(gcLast.getTime());
		map.put("thisStart", day_first);
		
		Calendar calendar2=Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMaximum(calendar2.DATE));
		SimpleDateFormat sf2=new SimpleDateFormat("yyyy-MM-dd");
		String ss=sf2.format(calendar.getTime());
		ss = ss+" 23:59:59";
		map.put("thisEnd", ss);
		return map;
    }
    
}
