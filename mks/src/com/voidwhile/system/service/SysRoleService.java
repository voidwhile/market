package com.voidwhile.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.core.IBaseService;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.SysMenus;
import com.voidwhile.system.entity.SysRole;
import com.voidwhile.system.entity.vo.SysRoleVo;

public interface SysRoleService extends IBaseService<SysRole> {

	/**
	 * @Title: save
	 * @Description: TODO(角色添加：menuIds为角色拥有的权限列表)
	 * @param entity
	 * @param menuIds
	 * @throws DataAccessException
	 * @return void
	 * @throws
	 */
	public void save(SysRole entity, List<Long> menuIds) throws DataAccessException;

	/**
	 * @Title: update
	 * @Description: TODO(角色更新,menuIds为角色拥有的权限列表，menuIds为null时只保存角色信息)
	 * @param entity
	 * @param menuIds
	 * @throws DataAccessException
	 * @return void
	 * @throws
	 */
	public void update(SysRole entity, List<Long> menuIds) throws DataAccessException;

	/**
	 * @MethodName: getByUserId
	 * @Description: 获取指定用户的角色列表
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年11月19日 下午2:46:43
	 */
	List<SysRole> getByUserId(String userId) throws DataAccessException;

	/**
	 * @Title: getPermissionsByRoleid
	 * @Description: TODO(获取角色信息，包括角色的权限)
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 * @return SysRoleVo
	 * @throws
	 */
	public SysRoleVo getPermissionsByRoleid(String roleId) throws DataAccessException;

	/**
	 * @Title: getUserPermissionsByUserId
	 * @Description: TODO(获取用户的权限信息)
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 * @return List<SysRoleVo>
	 * @throws
	 */
	public List<SysRoleVo> getUserPermissionsByUserId(String userId) throws DataAccessException;

	/**
	 * @MethodName: appAuthorization
	 * @Description: 验证用户是否可以登陆掌上App
	 * @param userId
	 * @return 返回角色代码
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年11月19日 下午4:57:02
	 */
	public String appAuthorization(String userId) throws DataAccessException;
	
	public List<Map<String, Object>> getMenuTreeByRoleId(Map<String, Object> param);
	
	public PageResult<SysRole> getUserByRoleId(Map<String, Object> param,int pageNo, int pageSize, String orderByClause);
	
    int insertRoleUser(Map<String, Object> param);
	
	int deleteRoleUser(Map<String, Object> param);
	/**
	 * 
	 * @MethodName: getBySupplierIdAndRoleCode 
	 * @Description: 根据企业id和角色编码获得角色对象
	 * @return
	 *
	 * @Author: Administrator
	 * @Create Date: 2014-12-9 下午4:04:09
	 */
	SysRole getBySupplierIdAndRoleCode(String supplierId,String roleCode);
	
	void deleteByIds(String[] ids);
	/**
	 * 
	 * @MethodName: allotMenus 
	 * @Description: 角色分配菜单
	 * @param roleId
	 * @param menuId
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-11 下午3:24:55
	 */
	public void allotMenus(String roleId, String menuIds);
	
	/**
	 * 
	 * @MethodName: getByRoleId 
	 * @Description: 获取用户
	 * @param param
	 * @return
	 *
	 * @Author: Administrator
	 * @Create Date: 2014-12-31 下午6:03:37
	 */
	public List<SysRole> getByRoleId(Map<String, Object> param);
	
	List<SysRole> findByRoleId(Map<String, Object> param) throws DataAccessException;

}
