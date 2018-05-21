package com.voidwhile.system.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.entity.SysMenus;
import com.voidwhile.system.entity.SysRole;
import com.voidwhile.system.entity.vo.SysRoleVo;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统角色管理 Mapper
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午10:57:41
 *
 * @Version: v1.0
 */
public interface SysRoleMapper {

	/**
	 * @MethodName: insert
	 * @Description: 添加角色
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午10:57:31
	 */
	int insert(SysRole entity) throws DataAccessException;
	
	/**
	 * @MethodName: insertPermissions
	 * @Description: 保存角色权限
	 * @param roleId
	 * @param menuId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午10:57:22
	 */
	int insertPermissions(String roleId, long menuId) throws DataAccessException;

	/**
	 * @MethodName: updateById
	 * @Description: 按ID更新角色
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午10:56:57
	 */
	int updateById(SysRole entity) throws DataAccessException;

	/**
	 * @MethodName: deleteById
	 * @Description: 按角色ID删除角色
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午10:56:38
	 */
	boolean deleteById(String roleId) throws DataAccessException;
	
	/**
	 * @MethodName: deletePermissionsById
	 * @Description: 删除指定角色的权限
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午10:56:28
	 */
	boolean deletePermissionsById(String roleId) throws DataAccessException;
	/**
	 * 
	 * @MethodName: deleteUserById 
	 * @Description: 删除指定角色的用户 
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-9 下午5:56:58
	 */
	boolean deleteUserById(String roleId) throws DataAccessException;

	/**
	 * @MethodName: getById
	 * @Description: 按角色ID查询角色
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午10:56:13
	 */
	SysRole getById(String roleId) throws DataAccessException;
	
	/**
	 * @MethodName: getPermissionsByRoleid
	 * @Description: 获取角色信息，包括角色的权限
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午10:56:04
	 */
	SysRoleVo getPermissionsByRoleid(String roleId) throws DataAccessException;
	
	/**
	 * @MethodName: getRoleByUserId
	 * @Description: 获取指定用户的角色列表
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午10:55:41
	 */
	List<SysRole> getRoleByUserId(String userId) throws DataAccessException;
	
	
	/**
	 * @MethodName: getUserPermissionsByUserId
	 * @Description: 获取指定用户的权限信息
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午10:55:32
	 */
	List<SysRoleVo> getUserPermissionsByUserId(String userId) throws DataAccessException;

	/**
	 * @MethodName: findByMap
	 * @Description: 按条件查询角色列表
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午10:55:24
	 */
	List<SysRole> findByMap(Map<String, Object> param) throws DataAccessException;

	/**
	 * @MethodName: countByMap
	 * @Description: 按条件查询角色数量
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午10:55:14
	 */
	int countByMap(Map<String, Object> param) throws DataAccessException;
	/**
	 * 
	 * @MethodName: getMenuByRoleId 
	 * @Description: 按条件查出角色的菜单权限
	 * @param param
	 * @return
	 *
	 * @Author: Administrator
	 * @Create Date: 2014-12-5 下午1:42:35
	 */
	List<SysRole> getMenuByRoleId(Map<String, Object> param);
	/**
	 * 
	 * @MethodName: getMenuCountByRoleId 
	 * @Description: 获取菜单数量
	 * @param uids
	 * @return
	 *
	 * @Author: Administrator
	 * @Create Date: 2014-12-5 下午1:50:02
	 */
	List<SysRole> getMenuCountByRoleId(List<String> uids);
	/**
	 * 
	 * @MethodName: getUserByRoleId 
	 * @Description: 获取拥有的该角色的用户
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: Administrator
	 * @Create Date: 2014-12-5 下午2:13:52
	 */
	List<SysRole> getUserByRoleId(Map<String, Object> param) throws DataAccessException; 
	/**
	 * 
	 * @MethodName: getUserCountByRoleId 
	 * @Description: 获取拥有的该角色的用户数量
	 * @param uids
	 * @return
	 *
	 * @Author: Administrator
	 * @Create Date: 2014-12-5 下午2:14:55
	 */
	List<SysRole> getUserCountByRoleId(List<String> uids);
	/**
	 * 
	 * @MethodName: insertRoleUser 
	 * @Description:插入用户角色
	 * @param param
	 * @return
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-11 下午3:22:18
	 */
	int insertRoleUser(Map<String, Object> param);
	/**
	 * 
	 * @MethodName: deleteRoleUser 
	 * @Description: 删除用户角色 
	 * @param param
	 * @return
	 *
	 * @Author: Administrator
	 * @Create Date: 2014-12-11 下午3:22:46
	 */
	int deleteRoleUser(Map<String, Object> param);
	/**
	 * 
	 * @MethodName: deletePermissions 
	 * @Description: 删除菜单权限 
	 * @param param
	 *
	 * @Author: Administrator
	 * @Create Date: 2014-12-11 下午3:21:48
	 */
	void deletePermissions(Map<String, Object> param);
	
	/**
	 * @DESC 根据角色查询用户
	 * @param param
	 * @return
	 * @throws DataAccessException
	 */
	List<SysRole> findByRoleId(Map<String, Object> param) throws DataAccessException;
	
}