package com.voidwhile.system.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.entity.SysUser;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 用户管理Mapper
 * @author: xiaowei
 * @Create Date: 2014年11月18日 下午3:00:48
 *
 * @Version: v1.0
 */
public interface SysUserMapper {
	/**
	 * @Title: insert
	 * @Description: TODO(添加用户)
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 * @return int
	 * @throws
	 */
	int insert(SysUser entity) throws DataAccessException;

	/**
	 * @Title: updateById
	 * @Description: TODO(按ID更新用户)
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 * @return int
	 * @throws
	 */
	int updateById(SysUser entity) throws DataAccessException;

	/**
	 * @Title: deleteById
	 * @Description: TODO(按用户ID删除用户)
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @return boolean
	 * @throws
	 */
	boolean deleteById(String id) throws DataAccessException;

	/**
	 * @Title: getById
	 * @Description: TODO(按用户ID查询用户)
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @return SysUser
	 * @throws
	 */
	SysUser getById(String id) throws DataAccessException;

	/**
	 * @Title: findByMap
	 * @Description: TODO(按条件查询用户列表)
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return List<SysUser>
	 * @throws
	 */
	List<SysUser> findByMap(Map<String, Object> param) throws DataAccessException;

	/**
	 * @Title: countByMap
	 * @Description: TODO(按条件查询用户数量)
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return int
	 * @throws
	 */
	int countByMap(Map<String, Object> param) throws DataAccessException;

	/**
	 * @Title: getSysUserRoleById
	 * @Description: TODO(按用户ID查询用户信息以及用户权限)
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 * @return SysUserRole
	 * @throws
	 */
	List<SysUser> getUserRoleById(String userId) throws DataAccessException;
	

	/**
	 * @MethodName: insertUserRole
	 * @Description: 用户角色添加
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月18日 下午4:45:22
	 */
	int insertUserRole(String userId, String roleId) throws DataAccessException;

	/**
	 * @MethodName: deleteRoleByUserId
	 * @Description: 删除用户角色关联表中当前用户的数据
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月18日 下午4:52:26
	 */
	int deleteRoleByUserId(String userId) throws DataAccessException;
	/**
	 * 
	 * @MethodName: deleteRoleByUserIdAndRoleId 
	 * @Description: 删除用户角色关联表中用户的相关角色数据  
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-3 下午5:19:27
	 */
	int deleteRoleByUserIdAndRoleId(Map<String, String> map) throws DataAccessException;
	/**
	 * 
	 * @param phone
	 * @return
	 * @throws DataAccessException
	 */
	SysUser getByPhone(String phone) throws DataAccessException;
	/**
	 * 查询有权利审核的人
	 * @return
	 * @throws DataAccessException
	 */
	List<SysUser> selectAuditor() throws DataAccessException;

	SysUser findById(String uid);

	SysUser findByNameAndPwd(Map<String, Object> param);
}