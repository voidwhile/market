package com.voidwhile.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.entity.SysMenus;

public interface SysMenusService {
	/**
	 * @Title: save
	 * @Description: TODO(保存)
	 * @param entity
	 * @throws DataAccessException
	 * @return void
	 * @throws
	 */
	public void save(SysMenus entity) throws DataAccessException;

	/**
	 * @Title: update
	 * @Description: TODO(更新)
	 * @param entity
	 * @throws DataAccessException
	 * @return void
	 * @throws
	 */
	public void update(SysMenus entity) throws DataAccessException;
	
	/**
	 * @MethodName: saveMenuLevel
	 * @Description: 保存企业等级菜单权限
	 * @param supplierLevel0
	 * @param supplierLevel1
	 * @param supplierLevel2
	 * @param supplierLevel3
	 * @param supplierLevel4
	 * @param supplierLevel5
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月26日 下午2:30:23
	 */
	public void saveMenuLevel(String[] supplierLevel0, String[] supplierLevel1, String[] supplierLevel2, String[] supplierLevel3, String[] supplierLevel4,
			String[] supplierLevel5) throws DataAccessException;

	/**
	 * @Title: delete
	 * @Description: TODO(删除)
	 * @param id
	 * @throws DataAccessException
	 * @return void
	 * @throws
	 */
	public void delete(long id) throws DataAccessException;

	/**
	 * @Title: getById
	 * @Description: TODO(按ID获取)
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @return SysMenus
	 * @throws
	 */
	public SysMenus getById(long id) throws DataAccessException;

	/**
	 * @Title: findMenusByRoleId
	 * @Description: TODO(查询角色权限列表)
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 * @return List<SysMenus>
	 * @throws
	 */
	public List<SysMenus> findMenusByRoleId(String roleId) throws DataAccessException;

	/**
	 * @MethodName: findMenusBySupplierLevel
	 * @Description: 根据企业等级和父菜单ID获取菜单列表
	 * @param supplierLevel
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月26日 上午9:59:33
	 */
	public Set<SysMenus> findMenusByPIdAndSupplierLevel(long parentId, String supplierLevel) throws DataAccessException;

	/**
	 * @MethodName: findMenusByPIdAndUserId
	 * @Description: 根据用户ID和父菜单ID查询子菜单列表
	 * @param parentId
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月28日 下午6:08:47
	 */
	public Set<SysMenus> findMenusByPIdAndUserId(long parentId, String userId) throws DataAccessException;

	/**
	 * @MethodName: getTreeGridData
	 * @Description: easyui TreeGrid数据处理
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月26日 下午1:15:03
	 */
	public List<Map<String, Object>> getTreeGridData(Map<String, Object> param) throws DataAccessException;

	/**
	 * @Title: findByMap
	 * @Description: TODO(按条件查询列表)
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return List<SysMenus>
	 * @throws
	 */
	public List<SysMenus> findByMap(Map<String, Object> param) throws DataAccessException;
	/**
	 * 
	 * @MethodName: getMenuTree 
	 * @Description: 获得企业等级菜单树 
	 * @param param
	 * @return
	 *
	 * @Author: Administrator
	 * @Create Date: 2014-12-9 下午6:27:32
	 */
	List<Map<String, Object>> getMenuTree(Map<String, Object> param);
	/**
	 * 
	 * @MethodName: countByMap 
	 * @Description:按条件统计数据
	 * @param param
	 * @return
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-23 下午1:20:55
	 */
	int countByMap(Map<String, Object> param);
    /**
     * 
     * @MethodName: batchUpdate 
     * @Description: 更新企业等级 
     * @param updatedList
     *
     * @Author: liups
     * @Create Date: 2014-12-23 下午3:27:56
     */
	public int batchUpdate(List<SysMenus> updatedList);
}
