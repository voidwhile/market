package com.voidwhile.system.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统运行参数 实体类
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午10:36:38
 *
 * @Version: v1.0
 */
public class SysConfig {

	private String uid;

	/**
	 * @Fields configCode : 参数代码
	 */ 
	private String configCode;

	/**
	 * @Fields configValue : 参数值
	 */ 
	private String configValue;

	/**
	 * @Fields configDesc : 参数描述
	 */ 
	private String configDesc;

	/**
	 * @Fields supplierId : 参数归属商家，为0时为全局设置
	 */ 
	private String supplierId;

	private Date createTime;

	private Date updateTime;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode == null ? null : configCode.trim();
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue == null ? null : configValue.trim();
	}

	public String getConfigDesc() {
		return configDesc;
	}

	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc == null ? null : configDesc.trim();
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createDate) {
		this.createTime = createDate;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateDate) {
		this.updateTime = updateDate;
	}
}