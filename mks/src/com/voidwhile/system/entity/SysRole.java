package com.voidwhile.system.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统角色 实体类
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午10:50:35
 *
 * @Version: v1.0
 */
public class SysRole {

	private String uid;

	private String supplierId;

	/**
	 * @Fields roleCode : 角色代码
	 */
	private String roleCode;

	/**
	 * @Fields roleName : 角色名称
	 */
	private String roleName;

	/**
	 * @Fields dataRange : 角色的数据范围
	 */
	private String dataRange;

	
	/**
	 * @Fields isSystem : 是否系统默认角色
	 */
	private String isSystem;
	
	/**
	 * @Fields isAppLogin :是否APP登录（1是，0否）
	 */
	private String isAppLogin;
	
	/**
	 * @Fields level :级别
	 */
	private Integer level;

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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode == null ? null : roleCode.trim();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	public String getDataRange() {
		return dataRange;
	}

	public void setDataRange(String dataRange) {
		this.dataRange = dataRange == null ? null : dataRange.trim();
	}

	
	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsAppLogin() {
		return isAppLogin;
	}

	public void setIsAppLogin(String isAppLogin) {
		this.isAppLogin = isAppLogin;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
	
	
}