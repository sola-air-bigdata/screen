package com.nzkj.screen.Utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 日志简单记录
 * @author 007idle
 *
 */
public class LogUtil {
	private static final Logger error = LogManager.getLogger("Rerror");
	private static final Logger debug = LogManager.getLogger("Rdebug");
	private static final Logger BILL_LOG = LogManager.getLogger("ncWebBusBill");
	private static final Logger charglogger = LogManager.getLogger("chargingProcess");
	
	
	public static void chargeDebug(String ... argus ){
		try {
		String objects = argus[0];
		int size = argus.length;
		switch (size) {
		case 1:
			charglogger.debug(objects);
			break;
		case 2:
			charglogger.debug(objects.toString(),argus[1]);
			break;
		case 3:
			charglogger.debug(objects.toString(),argus[1],argus[2]);
			break;
		case 4:
			charglogger.debug(objects.toString(),argus[1],argus[2],argus[3]);
			break;
		case 5:
			charglogger.debug(objects.toString(),argus[1],argus[2],argus[3],argus[4]);
			break;
		case 6:
			charglogger.debug(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5]);
			break;
		case 7:
			charglogger.debug(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5],argus[6]);
			break;
		case 8:
			charglogger.debug(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5],argus[6],argus[7]);
			break;
		case 9:
			charglogger.debug(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5],argus[6],argus[7],argus[8]);
			break;
		default:
			break;
		}
		} catch (Exception e) {
			charglogger.error("记录充电日志出现异常", e);
		}
	}
	
	public static void chargeInfo(String ... argus ){
		try {
		String objects = argus[0];
		int size = argus.length;
		switch (size) {
		case 1:
			charglogger.info(objects);
			break;
		case 2:
			charglogger.info(objects.toString(),argus[1]);
			break;
		case 3:
			charglogger.info(objects.toString(),argus[1],argus[2]);
			break;
		case 4:
			charglogger.info(objects.toString(),argus[1],argus[2],argus[3]);
			break;
		case 5:
			charglogger.info(objects.toString(),argus[1],argus[2],argus[3],argus[4]);
			break;
		case 6:
			charglogger.info(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5]);
			break;
		case 7:
			charglogger.info(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5],argus[6]);
			break;
		case 8:
			charglogger.info(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5],argus[6],argus[7]);
			break;
		case 9:
			charglogger.info(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5],argus[6],argus[7],argus[8]);
			break;
		default:
			break;
		}
	} catch (Exception e) {
		charglogger.error("记录充电日志出现异常", e);
	}
	}
	
	public static void chargeWarn(String ... argus ){
		try {
		String objects = argus[0];
		int size = argus.length;
		switch (size) {
		case 1:
			charglogger.warn(objects);
			break;
		case 2:
			charglogger.warn(objects.toString(),argus[1]);
			break;
		case 3:
			charglogger.warn(objects.toString(),argus[1],argus[2]);
			break;
		case 4:
			charglogger.warn(objects.toString(),argus[1],argus[2],argus[3]);
			break;
		case 5:
			charglogger.warn(objects.toString(),argus[1],argus[2],argus[3],argus[4]);
			break;
		case 6:
			charglogger.warn(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5]);
			break;
		case 7:
			charglogger.warn(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5],argus[6]);
			break;
		case 8:
			charglogger.warn(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5],argus[6],argus[7]);
			break;
		case 9:
			charglogger.warn(objects.toString(),argus[1],argus[2],argus[3],argus[4],argus[5],argus[6],argus[7],argus[8]);
			break;
		default:
			break;
		}
		} catch (Exception e) {
			charglogger.error("记录充电日志出现异常", e);
		}
	}
	
	/**
	 * 记录debug日志
	 * @param msg 日志信息
	 */
	public static void debug(String msg) {
		debug.warn("信息："+msg);
	}
	/**
	 * 记录error日志
	 * @param msg
	 * @param throwable
	 */
	public static void error(String msg,Throwable throwable) {
		error.error(msg,throwable);
	}
	/**
	 * 记录账单日志
	 * @param type
	 * @param content
	 */
	public static void billLog(String msg,String content) {
		BILL_LOG.warn(new StringBuilder().append(",信息: ").append(msg)
										 .append(",内容：").append(content)
										 .toString());
	}
	/**
	 * 获取异常栈的信息
	 * @param throwable
	 * @return
	 * @author 007idle
	 * @date 2018年1月18日
	 * @description
	 */
	public static String getStrackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally
        {
            pw.close();
        }
	}
}
