package com.voidwhile.market.entity;

import java.util.Date;

/**
 * 
 * CopyRright (c) 2017: 
 * 
 * @Description: 客户端登陆Session信息表
 * @author: zhanzheng
 * @Create Date: 2014年11月19日 上午9:55:30
 *
 * @Version: v1.0
 */
/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 类的描述
 * @author: zhanzheng
 * @Create Date: 2014年12月8日 下午5:11:09
 *
 * @Version: v1.0
 */
public class AppSession {
	/**
	 * @Fields sessionId : 会话ID
	 */
	private String sessionId;

	/**
	 * @Fields supplierId : 企业ID
	 */
	private String supplierId;

	/**
	 * @Fields supplierCode : 企业代码
	 */
	private String supplierCode;

	/**
	 * @Fields userId : 登陆用户ID
	 */
	private String userId;

	/**
	 * @Fields userName : 登陆用户名
	 */
	private String userName;

	/**
	 * @Fields clientType : 客户端类型（android,IOS)
	 */
	private String clientType;

	/**
	 * @Fields loginTime : 登陆时间
	 */
	private Date loginTime;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId == null ? null : sessionId.trim();
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode == null ? null : supplierCode.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType == null ? null : clientType.trim();
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}