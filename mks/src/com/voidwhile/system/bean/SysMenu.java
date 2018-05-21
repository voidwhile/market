package com.voidwhile.system.bean;

import java.util.List;
import java.util.Set;





/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统菜单实体类
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午10:48:00
 *
 * @Version: v1.0
 */
public class SysMenu {

	/*private Long uid;
	*//**
	 * @Fields menuName : 菜单名称
	 */
	private String menuName;

	/**
	 * @Fields menuCode : 菜单代码
	 */ 
	private String menuCode;

	

	/**
	 * @Fields childs : 子菜单
	 */
	//private List<SysMenu> childs;

	public SysMenu() {
	}

	/*public SysMenu(Long uid) {
		this.uid = uid;
	}
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}*/
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode == null ? null : menuCode.trim();
	}

	/*public List<SysMenu> getChilds() {
		return childs;
	}

	public void setChilds(List<SysMenu> childs) {
		this.childs = childs;
	}*/
	
	
}