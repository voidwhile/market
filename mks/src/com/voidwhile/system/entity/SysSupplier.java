package com.voidwhile.system.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 企业基本信息
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午10:35:24
 *
 * @Version: v1.0
 */
public class SysSupplier {

	/**
	 * @Fields supplierCode : 企业代码
	 */
	private String supplierCode;
	/**
	 * @Fields supplierName : 企业名称
	 */
	private String supplierName;
	/**
	 * @Fields supplierLevel : 企业等级
	 */
	private String supplierLevel;
	/**
	 * @Fields runProduct : 企业主营项目
	 */
	private String runProduct;

	/**
	 * @Fields upLogo : 企业LOGO
	 */
	private String upLogo;
	/**
	 * @Fields supplierIntro : 企业描述
	 */
	private String supplierIntro;

	/**
	 * @Fields province : 省
	 */
	private String province;

	/**
	 * @Fields city : 市
	 */
	private String city;

	/**
	 * @Fields county : 县（区）
	 */
	private String county;

	/**
	 * @Fields address : 详细地址
	 */
	private String address;
	/**
	 * @Fields lon : 地图_经度
	 */
	private String lon;

	/**
	 * @Fields lat : 地图_纬度
	 */
	private String lat;

	/**
	 * @Fields contacts : 联系人
	 */
	private String contacts;

	/**
	 * @Fields contactNumber : 联系电话
	 */
	private String contactNumber;

	/**
	 * @Fields approvalTime : 企业认证时间
	 */
	private Date approvalTime;

	/**
	 * @Fields supplierExpire : 企业过期时间
	 */
	private Date supplierExpire;

	private Date createTime;

	private Date updateTime;

	private String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid == null ? null : uid.trim();
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode == null ? null : supplierCode.trim();
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName == null ? null : supplierName.trim();
	}

	public String getSupplierLevel() {
		return supplierLevel;
	}

	public void setSupplierLevel(String supplierLevel) {
		this.supplierLevel = supplierLevel == null ? null : supplierLevel.trim();
	}

	public String getRunProduct() {
		return runProduct;
	}

	public void setRunProduct(String runProduct) {
		this.runProduct = runProduct == null ? null : runProduct.trim();
	}

	public String getUpLogo() {
		return upLogo;
	}

	public void setUpLogo(String upLogo) {
		this.upLogo = upLogo == null ? null : upLogo.trim();
	}

	public String getSupplierIntro() {
		return supplierIntro;
	}

	public void setSupplierIntro(String supplierIntro) {
		this.supplierIntro = supplierIntro == null ? null : supplierIntro.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county == null ? null : county.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon == null ? null : lon.trim();
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat == null ? null : lat.trim();
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts == null ? null : contacts.trim();
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber == null ? null : contactNumber.trim();
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}

	public Date getSupplierExpire() {
		return supplierExpire;
	}

	public void setSupplierExpire(Date supplierExpire) {
		this.supplierExpire = supplierExpire;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}