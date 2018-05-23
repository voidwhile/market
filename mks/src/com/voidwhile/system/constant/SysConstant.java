package com.voidwhile.system.constant;

import com.voidwhile.core.utils.PropertyUtils;

/**
 *******************************************************************************
 * 系统管理常量类，添加常量时请注明其用途
 *******************************************************************************
 * @author xiaowei
 *
 */
public class SysConstant {
	
	/**
	 * Session中的管理员Key
	 */
	public static final String SESSION_ADMIN = "sessionAdmin";
	
	/**
	 * 默认机构
	 */
	public static final String SUPPLIER = "market";

	/**
	 * ************************
	 * 
	 * 是:1,否：0
	 */
	public static final String YES = "1";
	public static final String NO = "0";

	/**
	 * *******************************
	 * 
	 * 客户端登陆类型(android,ios)
	 */
	public static final String APP_ANDROID = "android";
	public static final String APP_IOS = "ios";

	/**
	 * 日志级别
	 * 
	 * @author xiaowei
	 *
	 */
	public static enum LogLevel {
		INFO, WARN, ERROR
	};
	


	/**
	 * **************************
	 * 
	 * 报表类型：day：日报表,week：周报表,month：月报表,year：年报表
	 */
	public static final String WORK_REPORT_DAY = "day";
	public static final String WORK_REPORT_WEEK = "week";
	public static final String WORK_REPORT_MONTH = "month";
	public static final String WORK_REPORT_YEAR = "year";

	public static final String FILE_ROOT = PropertyUtils.getPropertyValue("config.properties", "file.path");
	public static final String IMG_URL = PropertyUtils.getPropertyValue("config.properties", "imgUrl");
	public static final String PATH = PropertyUtils.getPropertyValue("config.properties", "path");
	public static final String WXMENUPATH = PropertyUtils.getPropertyValue("config.properties", "WXMenuPath");

}
