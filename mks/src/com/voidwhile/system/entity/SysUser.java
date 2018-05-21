package com.voidwhile.system.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.voidwhile.common.util.SpringContextHelper;
import com.voidwhile.system.service.SysOrganizationService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统用户表 实体类
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午10:15:11
 *
 * @Version: v1.0
 */
@JsonIgnoreProperties(value = { "plainPassword", "password", "phoneCode", "isSystem" })
public class SysUser {

	private Long uid;
	/**
	 * @Fields supplierId : 企业Id
	 */
	private String supplierId;
	/**
	 * @Fields userName : 用户名
	 */
	private String userName;
	/**
	 * @Fields plainPassword : 明文密码
	 */
	private String plainPassword;
	/**
	 * @Fields password : 加密密码
	 */
	private String password;
	/**
	 * @Fields realName : 姓名
	 */
	private String realName;

	/**
	 * @Fields deptId : 用户所在部门
	 */
	private String deptId;
	/**
	 * @Fields gender : 性别（1男 2女 0未知）
	 */
	private String gender;
	/**
	 * @Fields birthday : 出生日期
	 */
	private Date birthday;
	/**
	 * @Fields portrait : 头像
	 */
	private String portrait;

	/**
	 * @Fields tel : 电话
	 */
	private String tel;
	/**
	 * @Fields phone : 手机
	 */
	private String phone;
	/**
	 * @Fields qq : qq
	 */
	private String qq;

	/**
	 * @Fields email : email
	 */
	private String email;

	/**
	 * @Fields phoneCode : 手机串码
	 */
	private String phoneCode;

	/**
	 * @Fields lastLogin : 最近登录时间
	 */
	private Date lastLogin;
	/**
	 * @Fields lastDevice : 最近登录设备
	 */
	private String lastDevice;
	/**
	 * @Fields lastIp : 登录登录Ip
	 */
	private String lastIp;
	/**
	 * @Fields loginNum : 登录次数
	 */
	private Integer loginNum;

	/**
	 * @Fields status : 用户状态(1有效，0失效）
	 */
	private String status;

	/**
	 * @Fields isSystem : 是否系统默认(1是，0不是)
	 */
	private String isSystem;
    private String imgpath;
    /**
     * user_position : 职位
     */
    private String userPosition;

	/**
	 * @Fields createTime : 注册时间
	 */
	private Date createTime;
	/**
	 * @Fields updateTime : 修改时间
	 */
	private Date updateTime;
	
	public SysUser() {
		// TODO Auto-generated constructor stub
	}
	
	public SysUser(String uid,String imgpath) {
		this.imgpath = imgpath;
	}

	public SysUser(Long uid, String userName, String realName, String deptId, String gender, String email, String phone) {
		this.uid = uid;
		this.userName = userName;
		this.realName = realName;
		this.deptId = deptId;
		this.gender = gender;
		this.email = email;
		this.phone=phone;
		
	}
	
	public SysUser(Long uid, String userName, String realName, String deptId, String gender, String email, String phone,Date updateTime) {
		this.uid = uid;
		this.userName = userName;
		this.realName = realName;
		this.deptId = deptId;
		this.gender = gender;
		this.email = email;
		this.phone=phone;
		this.updateTime = updateTime;
		
	}
	
	public SysUser(Long uid, String supplierId, String realName, String deptId, String attr1) {
		this.uid = uid;
		this.supplierId = supplierId;
		this.realName = realName;
		this.deptId = deptId;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId == null ? null : supplierId.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword == null ? null : plainPassword.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId == null ? null : deptId.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getBirthday() {
		return birthday;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait == null ? null : portrait.trim();
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode == null ? null : phoneCode.trim();
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getLastDevice() {
		return lastDevice;
	}

	public void setLastDevice(String lastDevice) {
		this.lastDevice = lastDevice == null ? null : lastDevice.trim();
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp == null ? null : lastIp.trim();
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem == null ? null : isSystem.trim();
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
	
	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public String getDeptName() {
		String deptName = "";
		try {
			SysOrganizationService organizationService = (SysOrganizationService) SpringContextHelper.getBean(SysOrganizationService.class);
			SysOrganization org = organizationService.getById(deptId);
			if (org != null) {
				deptName = org.getOrgName();
			}
		} catch (Exception e) {
		}
		return deptName;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	
}