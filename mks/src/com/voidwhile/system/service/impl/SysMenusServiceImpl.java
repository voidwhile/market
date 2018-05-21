package com.voidwhile.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voidwhile.system.entity.SysMenus;
import com.voidwhile.system.mapper.SysMenusMapper;
import com.voidwhile.system.service.SysMenusService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 菜单管理Service
 * @author: xiaowei
 * @Create Date: 2014年10月24日 下午1:08:23
 *
 * @Version: v1.0
 */
/** 
 * CopyRright (c) 2017: 
 * @Description: 类的描述
 * @author: xiaowei
 * @Create Date: 2014年12月26日 下午1:15:23 
 *
 * @Version: v1.0
 */
/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 类的描述
 * @author: xiaowei
 * @Create Date: 2014年12月26日 下午1:15:25
 *
 * @Version: v1.0
 */
@Service
public class SysMenusServiceImpl implements SysMenusService {

	@Autowired
	private SysMenusMapper menusMapper;

	/**
	 * @MethodName: save
	 * @Description: 保存
	 * @param entity
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysMenusService#save(com.shangyu.SysMenu.domain.SysMenus)
	 */
	public void save(SysMenus entity) throws DataAccessException {
		menusMapper.insert(entity);

	}

	/**
	 * @MethodName: update
	 * @Description: 更新
	 * @param entity
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysMenusService#update(com.shangyu.SysMenu.domain.SysMenus)
	 */
	public void update(SysMenus entity) throws DataAccessException {
		menusMapper.updateById(entity);

	}

	@Override
	@Transactional
	public void saveMenuLevel(String[] supplierLevel0, String[] supplierLevel1, String[] supplierLevel2, String[] supplierLevel3, String[] supplierLevel4,
			String[] supplierLevel5) throws DataAccessException {
	}

	/**
	 * @MethodName: delete
	 * @Description: 删除
	 * @param id
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysMenusService#delete(long)
	 */
	public void delete(long id) throws DataAccessException {
		menusMapper.deleteById(id);
	}

	/**
	 * @MethodName: getById
	 * @Description: 获取菜单信息
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysMenusService#getById(long)
	 */
	@Override
	public SysMenus getById(long id) throws DataAccessException {
		return menusMapper.getById(id);
	}

	/**
	 * @MethodName: findMenusByRoleId
	 * @Description: 查询角色权限列表
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysMenusService#findMenusByRoleId(long)
	 */
	public List<SysMenus> findMenusByRoleId(String roleId) throws DataAccessException {
		return menusMapper.findMenusByRoleId(roleId);
	}

	/**
	 * @MethodName: findMenusByPIdAndUserId
	 * @Description: 根据用户ID和父菜单ID查询子菜单列表
	 * @param parentId
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.voidwhile.system.service.SysMenusService#findMenusByPIdAndUserId(long,
	 *      java.lang.String)
	 */
	public Set<SysMenus> findMenusByPIdAndUserId(long parentId, String userId) throws DataAccessException {
		Set<SysMenus> topMenus = menusMapper.findMenusByPIdAndUserId(parentId, userId);
		if (topMenus == null || topMenus.isEmpty()) {
			return null;
		}
		Set<SysMenus> rltList = new TreeSet<SysMenus>();
		Set<SysMenus> childs = null;
		for (SysMenus menu : topMenus) {
			childs = menusMapper.findMenusByPIdAndUserId(menu.getUid(), userId);
			menu.setChilds(childs);
			rltList.add(menu);
		}
		return rltList;
	}

	/**
	 * @MethodName: findMenusByPIdAndSupplierLevel
	 * @Description: 根据企业等级和父菜单ID获取菜单列表
	 * @param parentId
	 * @param supplierLevel
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.voidwhile.system.service.SysMenusService#findMenusByPIdAndSupplierLevel(long,
	 *      java.lang.String)
	 */
	@Override
	public Set<SysMenus> findMenusByPIdAndSupplierLevel(long parentId, String supplierLevel) throws DataAccessException {
		Map<String, Object> param = new HashMap<String, Object>();
		if (!"".equals(StringUtils.trimToEmpty(supplierLevel))) {
			int level = 0;
			try {
				level = Integer.parseInt(supplierLevel);
				if (level < 0 || level > 5) {
					level = 0;
				}
			} catch (Exception e) {
				level = 0;
			}
			param.put("supplierLevel" + level, "1");
		}
		param.put("parentId", parentId);
		param.put("status", "1");
		List<SysMenus> topMenus = findByMap(param);
		if (topMenus == null || topMenus.isEmpty()) {
			return null;
		}
		Set<SysMenus> rltList = new TreeSet<SysMenus>();
		Set<SysMenus> childs = null;
		for (SysMenus menu : topMenus) {
			childs = findMenusByPIdAndSupplierLevel(menu.getUid(), supplierLevel);
			menu.setChilds(childs);
			rltList.add(menu);
		}
		return rltList;
	}

	/**
	 * @MethodName: findByMap
	 * @Description: 菜单查询
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysMenusService#findByMap(java.util.Map)
	 */
	public List<SysMenus> findByMap(Map<String, Object> param) throws DataAccessException {
		return menusMapper.findByMap(param);
	}

	@Override
	public List<Map<String, Object>> getTreeGridData(Map<String, Object> param) throws DataAccessException {
		param.put("orderByClause", "menu_order asc");
		List<SysMenus> menuList = findByMap(param);
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		if (menuList != null && menuList.size() > 0) {
			Map<String, Object> map = null;
			for (SysMenus entity : menuList) {
				map = new HashMap<String, Object>();
				map.put("uid", entity.getUid());

				if (menuList.contains(new SysMenus(entity.getParentId()))) {
					map.put("_parentId", entity.getParentId());
				}
				map.put("menuName", entity.getMenuName());
				map.put("menuCode", entity.getMenuCode());
				map.put("menuOrder", entity.getMenuOrder());
				map.put("url", entity.getMenuUrl());
				treeList.add(map);
			}
		}
		return treeList;
	}

	public List<Map<String, Object>> getMenuTree(Map<String, Object> param) {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		List<SysMenus> menusList = menusMapper.findByMap(param);
		Map<String, Object> map = null;
		if (!menusList.isEmpty()) {
			for (SysMenus e : menusList) {
				map = new HashMap<String, Object>();
				map.put("uid", e.getUid());
				if (e.getParentId()!= 0) {
					map.put("_parentId", e.getParentId());
				}
				map.put("menuName", e.getMenuName());
				map.put("url", e.getMenuUrl());
				map.put("status", e.getStatus());
				map.put("createTime", e.getCreateTime());
				treeList.add(map);
			}
		}
		return treeList;
	}

	@Override
	public int countByMap(Map<String, Object> param) {
		return menusMapper.countByMap(param);
	}

	@Override
	@Transactional
	public int batchUpdate(List<SysMenus> updatedList) {
		if (!updatedList.isEmpty()) {
			for (SysMenus menu : updatedList) {
				update(menu);
				// 如果父菜单没有更新 自动更新
				if (!"0".equals(menu.getParentId())) {
					SysMenus entity = menusMapper.getById(menu.getParentId());
					if (entity != null) {
						update(entity);
					}
				}

			}
			return updatedList.size();
		}
		return 0;
	}
}
