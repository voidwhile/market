package com.voidwhile.wx.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseWechatUser<M extends BaseWechatUser<M>> extends Model<M> implements IBean {


	public void setOpenid(java.lang.String openid) {
		set("openid", openid);
	}

	public java.lang.String getOpenid() {
		return get("openid");
	}

	public void setHeadimg(java.lang.String headimg) {
		set("headimg", headimg);
	}

	public java.lang.String getHeadimg() {
		return get("headimg");
	}

	public void setRealname(java.lang.String realname) {
		set("realname", realname);
	}

	public java.lang.String getRealname() {
		return get("realname");
	}

	public void setWnickname(java.lang.String wnickname) {
		set("wnickname", wnickname);
	}

	public java.lang.String getWnickname() {
		return get("wnickname");
	}

	public void setNickname(java.lang.String nickname) {
		set("nickname", nickname);
	}

	public java.lang.String getNickname() {
		return get("nickname");
	}

	public void setMobile(java.lang.String mobile) {
		set("mobile", mobile);
	}

	public java.lang.String getMobile() {
		return get("mobile");
	}

	public void setProvUuid(java.lang.String provUuid) {
		set("provUuid", provUuid);
	}

	public java.lang.String getProvUuid() {
		return get("provUuid");
	}

	public void setCityUuid(java.lang.String cityUuid) {
		set("cityUuid", cityUuid);
	}

	public java.lang.String getCityUuid() {
		return get("cityUuid");
	}

	public void setCountyUuid(java.lang.String countyUuid) {
		set("countyUuid", countyUuid);
	}

	public java.lang.String getCountyUuid() {
		return get("countyUuid");
	}

	public void setAddress(java.lang.String address) {
		set("address", address);
	}

	public java.lang.String getAddress() {
		return get("address");
	}

	public void setCardImg(java.lang.String cardImg) {
		set("cardImg", cardImg);
	}

	public java.lang.String getCardImg() {
		return get("cardImg");
	}

	public void setCreatetime(java.util.Date createtime) {
		set("createtime", createtime);
	}

	public java.util.Date getCreatetime() {
		return get("createtime");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}

	public java.lang.String getContent() {
		return get("content");
	}

	public void setPassword(java.lang.String password) {
		set("password", password);
	}

	public java.lang.String getPassword() {
		return get("password");
	}

	public void setSubscribe(java.lang.Integer subscribe) {
		set("subscribe", subscribe);
	}

	public java.lang.Integer getSubscribe() {
		return get("subscribe");
	}

	public void setSex(java.lang.Integer sex) {
		set("sex", sex);
	}

	public java.lang.Integer getSex() {
		return get("sex");
	}

	public void setCity(java.lang.String city) {
		set("city", city);
	}

	public java.lang.String getCity() {
		return get("city");
	}

	public void setCountry(java.lang.String country) {
		set("country", country);
	}

	public java.lang.String getCountry() {
		return get("country");
	}

	public void setProvince(java.lang.String province) {
		set("province", province);
	}

	public java.lang.String getProvince() {
		return get("province");
	}

	public void setLanguage(java.lang.String language) {
		set("language", language);
	}

	public java.lang.String getLanguage() {
		return get("language");
	}

	public void setHeadimgurl(java.lang.String headimgurl) {
		set("headimgurl", headimgurl);
	}

	public java.lang.String getHeadimgurl() {
		return get("headimgurl");
	}

	public void setSubscribeTime(java.lang.String subscribeTime) {
		set("subscribe_time", subscribeTime);
	}

	public java.lang.String getSubscribeTime() {
		return get("subscribe_time");
	}

	public void setGroupid(java.lang.String groupid) {
		set("groupid", groupid);
	}

	public java.lang.String getGroupid() {
		return get("groupid");
	}

	public void setAppid(java.lang.String appid) {
		set("appid", appid);
	}

	public java.lang.String getAppid() {
		return get("appid");
	}

	public void setRecommOpenid(java.lang.String recommOpenid) {
		set("recommOpenid", recommOpenid);
	}

	public java.lang.String getRecommOpenid() {
		return get("recommOpenid");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}

	public java.lang.String getRemark() {
		return get("remark");
	}

	public void setUserid(java.lang.Long userid) {
		set("userid", userid);
	}

	public java.lang.Long getUserid() {
		return get("userid");
	}

}
