package com.voidwhile.system.entity.vo;

import java.util.List;

import com.voidwhile.system.entity.SysRole;
import com.voidwhile.system.entity.SysUser;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 用户扩展信息
 * @author: zhanzheng
 * @Create Date: 2014年10月24日 下午2:14:48
 *
 * @Version: v1.0
 */
public class SysUserRole extends SysUser {

	/**
	 * @Fields roleList : 用户角色列表
	 */
	private List<SysRole> roleList;

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

}