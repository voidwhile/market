
package com.voidwhile.system.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.voidwhile.system.constant.SysConstant;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统日志 实体类
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午10:47:37
 *
 * @Version: v1.0
 */
public class SysLog {

	private String uid;

	/**
	 * @Fields supplierId : 企业ID
	 */
	private String supplierId;

	/**
	 * @Fields operator : 日志操作者
	 */
	private String operator;

	/**
	 * @Fields logLevel : 日志级别:INFO、WARN、 ERROR
	 */
	private SysConstant.LogLevel	logLevel;

	/**
	 * @Fields logType : 日志分类
	 */
	private String logType;
	
	/**
	 * @Fields logContent : 日志详细内容
	 */
	private String logContent;

	/**
	 * @Fields ipAddress : IP地址
	 */
	private String ipAddress;

	/**
	 * @Fields createTime : 记录时间
	 */
	private Date createTime;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public SysConstant.LogLevel getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(SysConstant.LogLevel logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType == null ? null : logType.trim();
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress == null ? null : ipAddress.trim();
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent == null ? null : logContent.trim();
	}
	
}