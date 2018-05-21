package com.voidwhile.system.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.voidwhile.system.entity.SysMenus;
import com.voidwhile.system.entity.SysRole;
import com.voidwhile.system.entity.SysSupplier;
import com.voidwhile.system.entity.SysUser;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 用户登陆信息
 * @author: xiaowei
 * @Create Date: 2014年10月24日 下午2:16:49
 *
 * @Version: v1.0
 */
public class Admin {

	/**
	 * @Fields member : 登陆用户信息
	 */
	private SysUser user = new SysUser();

	/**
	 * @Fields supplier : 登陆用户企业信息
	 */
	private SysSupplier supplier = new SysSupplier();

	/**
	 * @Fields roleList : 用户的角色信息
	 */
	private List<SysRole> roleList = new ArrayList<SysRole>();

	/**
	 * @Fields menuList : 用户菜单列表（一、二级菜单）
	 */
	private Set<SysMenus> menuList = new HashSet<SysMenus>();

	/**
	 * @Fields dataRange : 用户的数据权限
	 */
	private String dataRange;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public SysSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(SysSupplier supplier) {
		this.supplier = supplier == null ? new SysSupplier() : supplier;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	public Set<SysMenus> getMenuList() {
		return menuList;
	}

	public void setMenuList(Set<SysMenus> menuList) {
		this.menuList = menuList;
	}

	public String getDataRange() {
		return dataRange;
	}

	public void setDataRange(String dataRange) {
		this.dataRange = dataRange;
	}

}
