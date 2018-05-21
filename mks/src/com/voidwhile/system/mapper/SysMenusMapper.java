package com.voidwhile.system.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.entity.SysMenus;


/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统菜单管理 Mapper
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午11:14:42
 *
 * @Version: v1.0
 */
public interface SysMenusMapper {
	
	
	/**
	 * @MethodName: getById
	 * @Description: 根据ID获取菜单信息
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:15:01
	 */
	SysMenus getById(long id) throws DataAccessException;
	
	/**
	 * @MethodName: findByMap
	 * @Description: 根据条件获取菜单列表
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:15:18
	 */
	List<SysMenus> findByMap(Map<String, Object> param) throws DataAccessException;
	
	/**
	 * @MethodName: insert
	 * @Description: 保存菜单
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:15:25
	 */
	int insert(SysMenus entity) throws DataAccessException;
	
	/**
	 * @MethodName: updateById
	 * @Description: 按ID更新菜单
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:15:35
	 */
	int updateById(SysMenus entity) throws DataAccessException;
	
	/**
	 * @MethodName: deleteById
	 * @Description: 按ID删除菜单
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:15:50
	 */
	int deleteById(long id) throws DataAccessException;
	
	/**
	 * @MethodName: findMenusByRoleId
	 * @Description: 获取指定角色的菜单列表
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:16:23
	 */
	List<SysMenus> findMenusByRoleId(String roleId) throws DataAccessException;
	
	/**
	 * @MethodName: findMenusByPIdAndRoleId
	 * @Description: 根据父菜单ID和用户ID获取菜单列表
	 * @param parentId
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月28日 下午5:57:26
	 */
	Set<SysMenus> findMenusByPIdAndUserId(long parentId, String userId) throws DataAccessException;
	/**
	 * 
	 * @MethodName: countByMap 
	 * @Description: 按条件统计数量 
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-23 下午1:16:14
	 */
	
	int countByMap(Map<String, Object> param) throws DataAccessException;
}