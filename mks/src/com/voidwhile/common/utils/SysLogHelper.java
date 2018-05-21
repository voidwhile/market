package com.voidwhile.common.utils;

import java.util.Date;

import com.voidwhile.common.util.SpringContextHelper;
import com.voidwhile.system.constant.SysConstant.LogLevel;
import com.voidwhile.system.entity.SysLog;
import com.voidwhile.system.service.SysLogService;
import com.voidwhile.system.service.impl.SysLogServiceImpl;

/**
 * 系统日志操作工具类
 * 
 * @author xiaowei
 *
 */
public class SysLogHelper {

	private SysLogHelper() {
	}

	/**
	 * 记录INFO日志
	 * 
	 * @param supplierId 企业ID
	 * @param operator 操作者
	 * @param logType 日志类型
	 * @param logContent 日内容
	 * @param ipAddress 操作IP地址
	 */
	public static void info(String supplierId, String operator, String logType, String logContent, String ipAddress) {
		log(supplierId, operator, LogLevel.INFO, logType, logContent, ipAddress);
	}
	
	/**
	 * 记录警告(WARN)日志
	 * 
	 * @param supplierId 企业ID
	 * @param operator 操作者
	 * @param logType 日志类型
	 * @param logContent 日内容
	 * @param ipAddress 操作IP地址
	 */
	public static void warn(String supplierId, String operator, String logType, String logContent, String ipAddress) {
		log(supplierId, operator, LogLevel.WARN, logType, logContent, ipAddress);
	}

	/**
	 * 记录错误(ERROR)日志
	 * 
	 * @param supplierId 企业ID
	 * @param operator 操作者
	 * @param logType 日志类型
	 * @param logContent 日内容
	 * @param ipAddress 操作IP地址
	 */
	public static void error(String supplierId, String operator, String logType, String logContent, String ipAddress) {
		log(supplierId, operator, LogLevel.ERROR, logType, logContent, ipAddress);
	}

	/**
	 * 记录日志
	 * 
	 * @param supplierId 企业ID
	 * @param operator 操作者
	 * @param logLevel 日志级别
	 * @param logType 日志类型
	 * @param logContent 日内容
	 * @param ipAddress 操作IP地址
	 */
	private static void log(String supplierId, String operator, LogLevel logLevel, String logType, String logContent, String ipAddress) {
		SysLog log = new SysLog();
		log.setSupplierId(supplierId);
		log.setOperator(operator);
		log.setLogLevel(logLevel);
		log.setLogType(logType);
		log.setLogContent(logContent);
		log.setIpAddress(ipAddress);
		log.setCreateTime(new Date());

		SysLogService logService = SpringContextHelper.getBean(SysLogServiceImpl.class);
		logService.save(log);
	}

}
