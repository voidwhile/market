package com.voidwhile.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.constant.SysConstant;
import com.voidwhile.system.entity.SysMenus;
import com.voidwhile.system.entity.SysRole;
import com.voidwhile.system.entity.vo.SysRoleVo;
import com.voidwhile.system.mapper.SysMenusMapper;
import com.voidwhile.system.mapper.SysRoleMapper;
import com.voidwhile.system.service.SysRoleService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统角色管理 Service
 * @author: zhanzheng
 * @Create Date: 2014年10月24日 上午11:47:18
 *
 * @Version: v1.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysMenusMapper sysMenusMapper;

	/**
	 * @MethodName: save
	 * @Description: 角色添加
	 * @param entity
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.IBaseService#save(java.lang.Object)
	 */
	@Override
	public void save(SysRole entity) throws DataAccessException {
		entity.setUid(idGenarater.getNextValue());
		roleMapper.insert(entity);
	}

	/**
	 * @MethodName: update
	 * @Description: 角色更新
	 * @param entity
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.IBaseService#update(java.lang.Object)
	 */
	@Override
	public void update(SysRole entity) throws DataAccessException {
		roleMapper.updateById(entity);
	}


	/**
	 * @MethodName: save
	 * @Description: 角色添加：menuIds为角色拥有的权限列表
	 * @param entity
	 * @param menuIds
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysRoleService#save(com.shangyu.mall.domain.SysRole,
	 *      java.util.List)
	 */
	@Transactional
	public void save(SysRole entity, List<Long> menuIds) throws DataAccessException {
		save(entity);
		String roleId = entity.getUid();
		if (menuIds != null) {
			for (Long menuId : menuIds) {
				if (menuId > 0) {
					roleMapper.insertPermissions(roleId, menuId);
				}
			}
		}
	}
	/**
	 * @MethodName: update
	 * @Description: 角色更新,menuIds为角色拥有的权限列表，menuIds为null时只保存角色信息
	 * @param entity
	 * @param menuIds
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysRoleService#update(com.shangyu.mall.domain.SysRole,
	 *      java.util.List)
	 */
	@Transactional
	public void update(SysRole entity, List<Long> menuIds) throws DataAccessException {
		roleMapper.updateById(entity);
		String roleId = entity.getUid();
		if (menuIds != null) {
			roleMapper.deletePermissionsById(roleId);
			for (Long menuId : menuIds) {
				if (menuId > 0) {
					roleMapper.insertPermissions(roleId, menuId);
				}
			}
		}
	}

	/** 
	 * @Title: delete 
	 * @Description: TODO(角色删除) 
	 * @param roleId
	 * @throws DataAccessException 
	 * @return void
	 * @throws 
	 */
	@Transactional
	public void delete(String roleId) throws DataAccessException {
		roleMapper.deleteById(roleId);
		roleMapper.deletePermissionsById(roleId);
		roleMapper.deleteUserById(roleId);
	}
	
	/**
	 * @MethodName: getById
	 * @Description: 获取角色信息
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.IBaseService#getById(long)
	 */
	@Override
	public SysRole getById(String roleId) throws DataAccessException {
		return roleMapper.getById(roleId);
	}
	
	public List<SysRole> getByUserId(String userId) throws DataAccessException {
		return roleMapper.getRoleByUserId(userId);
	}

	/**
	 * @MethodName: getPermissionsByRoleid
	 * @Description: 获取角色信息，包括角色的权限
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysRoleService#getPermissionsByRoleid(long)
	 */
	@Override
	public SysRoleVo getPermissionsByRoleid(String roleId) throws DataAccessException {
		return roleMapper.getPermissionsByRoleid(roleId);
	}
	
	/**
	 * @MethodName: getUserPermissionsByUserId
	 * @Description: 获取用户的权限信息
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysRoleService#getUserPermissionsByUserId(long)
	 */
	@Override
	public List<SysRoleVo> getUserPermissionsByUserId(String userId) throws DataAccessException {
		return roleMapper.getUserPermissionsByUserId(userId);
	}
	
	/**
	 * @MethodName: findPageData
	 * @Description: 查询分页数据
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @param orderByClause
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.IBaseService#findPageData(java.util.Map,
	 *      int, int, java.lang.String)
	 */
	@Override
	public PageResult<SysRole> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<SysRole> pageResult = new PageResult<SysRole>(pageNo, pageSize);
		pageResult.setTotal(this.countByMap(param));

		param.put("offset", pageResult.getOffset());
		param.put("pageSize", pageResult.getPageSize());
		if (!"".equals(StringUtils.trimToEmpty(orderByClause))) {
			param.put("orderByClause", orderByClause);
		}
		pageResult.setList(this.findByMap(param));
		return pageResult;
	}

	/**
	 * @MethodName: findByMap
	 * @Description: 按条件查询角色
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.IBaseService#findByMap(java.util.Map)
	 */
	@Override
	public List<SysRole> findByMap(Map<String, Object> param) throws DataAccessException {
		return roleMapper.findByMap(param);
	}

	/**
	 * @MethodName: countByMap
	 * @Description: 按条件统计角色数量
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.IBaseService#countByMap(java.util.Map)
	 */
	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return roleMapper.countByMap(param);
	}

	@Override
	public String appAuthorization(String userId) throws DataAccessException {
		List<SysRole> roleList = this.getByUserId(userId);
		String rlt = "";
		if (roleList != null && roleList.size() > 0) {
			for(SysRole role : roleList){
				String roleCode = StringUtils.trimToEmpty(role.getRoleCode());
				rlt = roleCode;
				break;
			}
		}
		return rlt;
	}
   /**
    * 
    * @MethodName: getMenuByRoleId 
    * @Description: 得到菜单 
    * @param param
    * @param pageNo
    * @param pageSize
    * @param orderByClause
    * @return
    *
    * @see com.voidwhile.system.service.SysRoleService#getMenuByRoleId(java.util.Map, int, int, java.lang.String)
    */
	@Override
	public List<Map<String, Object>> getMenuTreeByRoleId(Map<String, Object> param) {
		if(param==null){
			param=new HashMap<String, Object>();
		}
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		List<SysRole> roleList=roleMapper.getMenuByRoleId(param);
		Map<String,Object> map=null;
		if(!roleList.isEmpty()){
		 for(SysRole e:roleList){
			 map=new HashMap<String, Object>();
			 map.put("uid", e.getUid());
//			 map.put("attr2", e.getAttr2());
//			 if(e.getAttr2().indexOf(e.getAttr1())>=0){
//				 map.put("_parentId", e.getAttr1());
//			 }
			 map.put("menuName", e.getRoleName());
			 map.put("url", e.getRoleCode());
			 map.put("status", e.getIsSystem());
			 map.put("createTime", e.getCreateTime());
			 treeList.add(map);
		 }
		}
		return treeList;
	}
   @Override
   public PageResult<SysRole> getUserByRoleId(Map<String, Object> param,
		int pageNo, int pageSize, String orderByClause) {
			if(param==null){
				param=new HashMap<String, Object>();
			}
			PageResult<SysRole> pageResult = new PageResult<SysRole>(pageNo, pageSize);
		
			pageResult.setTotal(roleMapper.getUserCountByRoleId((List)param.get("uids")).size());
		
			param.put("offset", pageResult.getOffset());
			param.put("pageSize", pageResult.getPageSize());
			if (!"".equals(StringUtils.trimToEmpty(orderByClause))) {
				param.put("orderByClause", orderByClause);
			}
			pageResult.setList(roleMapper.getUserByRoleId(param));
			return pageResult;
  }
   
   @Override
   public List<SysRole> getByRoleId(Map<String, Object> param){
	   return roleMapper.getUserByRoleId(param);
   }
	@Override
	public int insertRoleUser(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.roleMapper.insertRoleUser(param);
	}
	
	@Override
	public int deleteRoleUser(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return this.roleMapper.deleteRoleUser(param);
	}
	
	public SysRole getBySupplierIdAndRoleCode(String supplierId,String roleCode){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("supplierId", supplierId);
		map.put("roleCode", roleCode);
		List<SysRole> list=findByMap(map);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
	
	@Transactional
	public void deleteByIds(String[] ids) throws DataAccessException {
		if (ids != null && ids.length > 0) {
			for (String roleId : ids) {
				delete(roleId);
			}
		}
	}

	@Override
	@Transactional
	public void allotMenus(String roleId, String menuIds) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("roleId", roleId);
		this.roleMapper.deletePermissions(param);
		String[] mis=menuIds.split(",");
		for(String menuId:mis){
			SysMenus menu=sysMenusMapper.getById(Long.valueOf(menuId));
			this.roleMapper.insertPermissions(roleId, Long.valueOf(menuId));
			//自动更新父菜单
			if(menu.getParentId()!=0){
				this.roleMapper.insertPermissions(roleId, menu.getParentId());
			}
		}
	}

	@Override
	public List<SysRole> findByRoleId(Map<String, Object> param) throws DataAccessException {
		return roleMapper.findByRoleId(param);
	}
}
