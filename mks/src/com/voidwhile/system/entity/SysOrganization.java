package com.voidwhile.system.entity;

import java.util.Date;

import com.voidwhile.common.util.SpringContextHelper;
import com.voidwhile.system.service.SysUserService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 企业内部组织机构表 实体类
 * @author: xiaowei
 * @Create Date: 2014年11月18日 上午10:15:39
 *
 * @Version: v1.0
 */
public class SysOrganization {
	private String uid;

	/**
	 * @Fields supplierId : 企业ID
	 */
	private String supplierId;

	/**
	 * @Fields pId : 上级组织机构ID
	 */
	private String pId;

	/**
	 * @Fields orgName : 组织机构名称
	 */
	private String orgName;

	/**
	 * @Fields orgCode : 组织机构代码
	 */
	private String orgCode;

	/**
	 * @Fields orgHead : 负责人
	 */
	private String orgHead;

	/**
	 * @Fields orgPhone : 联系电话
	 */
	private String orgPhone;

	/**
	 * @Fields status : 状态
	 */
	private String status;
	/**
	 * @Fields realName : 负责人姓名
	 */
	
	
    private String realName;
    private String userUid;
	
	private Date createTime;

	private Date updateTime;
	public SysOrganization() {
	}
	public String getOwnerUsername() {
		String createUsername = "";
		try {
			SysUserService userService = (SysUserService) SpringContextHelper.getBean(SysUserService.class);
			SysUser user = userService.getById(orgHead);
			if (user != null) {
				createUsername = user.getRealName();
			}
		} catch (Exception e) {
		}
		return createUsername;
	}
	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	public SysOrganization(String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid == null ? null : uid.trim();
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId == null ? null : supplierId.trim();
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId == null ? null : pId.trim();
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName == null ? null : orgName.trim();
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode == null ? null : orgCode.trim();
	}

	public String getOrgHead() {
		return orgHead;
	}

	public void setOrgHead(String orgHead) {
		this.orgHead = orgHead == null ? null : orgHead.trim();
	}

	public String getOrgPhone() {
		return orgPhone;
	}

	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone == null ? null : orgPhone.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysOrganization other = (SysOrganization) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

}