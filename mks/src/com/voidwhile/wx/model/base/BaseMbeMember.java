package com.voidwhile.wx.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseMbeMember<M extends BaseMbeMember<M>> extends Model<M> implements IBean {

	public void setMemberId(java.lang.Long memberId) {
		set("member_id", memberId);
	}

	public java.lang.Long getMemberId() {
		return get("member_id");
	}

	public void setUserName(java.lang.String userName) {
		set("user_name", userName);
	}

	public java.lang.String getUserName() {
		return get("user_name");
	}

	public void setPasswd(java.lang.String passwd) {
		set("passwd", passwd);
	}

	public java.lang.String getPasswd() {
		return get("passwd");
	}

	public void setRealName(java.lang.String realName) {
		set("real_name", realName);
	}

	public java.lang.String getRealName() {
		return get("real_name");
	}

	public void setMp(java.lang.String mp) {
		set("mp", mp);
	}

	public java.lang.String getMp() {
		return get("mp");
	}

	public void setLastLoginTime(java.util.Date lastLoginTime) {
		set("last_login_time", lastLoginTime);
	}

	public java.util.Date getLastLoginTime() {
		return get("last_login_time");
	}

}
