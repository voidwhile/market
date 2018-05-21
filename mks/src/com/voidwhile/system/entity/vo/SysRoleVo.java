package com.voidwhile.system.entity.vo;

import java.util.List;

import com.voidwhile.system.entity.SysMenus;
import com.voidwhile.system.entity.SysRole;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 角色信息扩展类
 * @author: xiaowei
 * @Create Date: 2014年10月24日 下午2:15:08
 *
 * @Version: v1.0
 */
public class SysRoleVo extends SysRole {


	/**
	 * @Fields menuList : 角色的权限列表
	 */
	private List<SysMenus> menuList;

	public List<SysMenus> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SysMenus> menuList) {
		this.menuList = menuList;
	}

}
