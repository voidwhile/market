package com.voidwhile.system.constant;

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
	 * 位置上报类型， SIGNIN：签到， SIGNOUT：签退， SUBMIT：位置上报
	 */
	public static final String LOCATION_TYPE_SIGNIN = "signin";

	public static final String LOCATION_TYPE_SIGNOUT = "signout";

	public static final String LOCATION_TYPE_SUBMIT = "submit";

	/**
	 * **************************
	 * 
	 * 报表类型：day：日报表,week：周报表,month：月报表,year：年报表
	 */
	public static final String WORK_REPORT_DAY = "day";
	public static final String WORK_REPORT_WEEK = "week";
	public static final String WORK_REPORT_MONTH = "month";
	public static final String WORK_REPORT_YEAR = "year";

	/**
	 * **************************
	 * 
	 * 系统默认角色代码
	 */
	// 系统管理员
	public static final String ROLE_CODE_ADMIN = "admin";
	// 高管
	public static final String ROLE_CODE_BOSS = "boss";
	//主管
	public static final String ROLE_CODE_DIRECTOR = "director";
	// 业务员
	public static final String ROLE_CODE_SALEMAN = "saleman";

	/*****************************
	 * 用户数据范围
	 */
	// 全部数据，无限制
	public static final String ROLE_DATARANG_FULL = "full";
	// 部门内数据
	public static final String ROLE_DATARANG_DEPT = "dept";
	// 只能查询自己的数据
	public static final String ROLE_DATARANG_OWNER = "owner";

}
