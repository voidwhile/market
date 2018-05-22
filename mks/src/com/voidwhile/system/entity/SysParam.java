package com.voidwhile.system.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统字典 实体类
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午10:48:55
 *
 * @Version: v1.0
 */
public class SysParam {

	private String uid;

	/**
	 * @Fields supplierId : 商家ID
	 */
	private String supplierId;

	/**
	 * @Fields paramCode : 字典代码（英文、数字、下划线)
	 */
	private String paramCode;

	/**
	 * @Fields paramName : 字典中文名称
	 */
	private String paramName;

	/**
	 * @Fields issystem : 是否为系统内置（1是，0不是)
	 */
	private String issystem;

	/**
	 * @Fields remark : 字典描述
	 */
	private String remark;

	private Date createTime;

	private Date updateTime;

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

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode == null ? null : paramCode.trim();
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName == null ? null : paramName.trim();
	}

	public String getIssystem() {
		return issystem;
	}

	public void setIssystem(String issystem) {
		this.issystem = issystem == null ? null : issystem.trim();
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}