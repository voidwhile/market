package com.voidwhile.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.voidwhile.common.dto.MenuAttachDTO;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统菜单实体类
 * @author: zhanzheng
 * @Create Date: 2014年10月24日 上午10:48:00
 *
 * @Version: v1.0
 */
public class SysMenus implements Comparable{

	private Long uid;

	/**
	 * @Fields parentId : 父菜单ID
	 */ 
	private Long parentId;

	/**
	 * @Fields parentCode : 父菜单代码
	 */ 
	private String parentCode;

	/**
	 * @Fields menuName : 菜单名称
	 */ 
	private String menuName;

	/**
	 * @Fields menuCode : 菜单代码
	 */ 
	private String menuCode;

	/**
	 * @Fields menuUrl : 菜单链接
	 */ 
	private String menuUrl;

	/**
	 * @Fields menuAttr : 菜单操作权限
	 */
	private String menuAttach;

	/**
	 * @Fields menuIcon : 菜单图标
	 */ 
	private String menuIcon;

	/**
	 * @Fields menuBigicon : 菜单大图标
	 */ 
	private String menuBigicon;

	/**
	 * @Fields menuOrder : 菜单排序
	 */ 
	private Integer menuOrder;

	/**
	 * @Fields remark : 备注
	 */ 
	private String remark;

	/**
	 * @Fields status : 菜单状态，1：正常，0不可用
	 */ 
	private String status;

	/**
	 * @Fields validTime : 菜单有效开始时间
	 */ 
	private Date validTime;

	/**
	 * @Fields invalidTime : 菜单有效结束时间
	 */ 
	private Date invalidTime;

	

	private Date createTime;

	private Date updateTime;

	/** 关联 **/

	/**
	 * @Fields childs : 子菜单
	 */
	private Set<SysMenus> childs;

	public SysMenus() {
	}

	public SysMenus(Long uid) {
		this.uid = uid;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode == null ? null : parentCode.trim();
	}

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

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public String getMenuAttach() {
		return menuAttach;
	}

	public void setMenuAttach(String menuAttach) {
		this.menuAttach = menuAttach == null ? null : menuAttach.trim();
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon == null ? null : menuIcon.trim();
	}

	public String getMenuBigicon() {
		return menuBigicon;
	}

	public void setMenuBigicon(String menuBigicon) {
		this.menuBigicon = menuBigicon == null ? null : menuBigicon.trim();
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Date getValidTime() {
		return validTime;
	}

	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
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

	public Set<SysMenus> getChilds() {
		return childs;
	}

	public void setChilds(Set<SysMenus> childs) {
		this.childs = childs;
	}

	public List<MenuAttachDTO> getMenuAttachs() {
		List<MenuAttachDTO> list = null;
		if (StringUtils.isNotEmpty(menuAttach)) {
			list = new ArrayList<MenuAttachDTO>();
			String[] methods = menuAttach.split(";");
			for (String method : methods) {
				String[] args = method.split(":");
				if (args != null && args.length == 2) {
					MenuAttachDTO dto = new MenuAttachDTO();
					dto.setMethodCode(args[0]);
					dto.setMethodName(args[1]);
					list.add(dto);
				}
			}
		}
		return list;
	}

	@Override
	public int hashCode() {
		return (int) (getUid() ^ (getUid() >>> 32));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof SysMenus) {
			return getUid() == ((SysMenus) obj).getUid();
		}
		return false;
	}

	@Override
	public int compareTo(Object o) {
		SysMenus sysmenu=(SysMenus) o;
		if(sysmenu.getMenuOrder() == null){
			System.out.println("-----------------");
			System.out.println(sysmenu.getMenuName());
			System.out.println("-----------------");
		}
		if(this.getMenuOrder() == null){
			System.out.println("--------------------------------------");
			System.out.println(this.getMenuName());//“车辆”菜单没有menuOrder
			System.out.println("-----------------");
		}
		return this.getMenuOrder()-sysmenu.getMenuOrder();
	}

}