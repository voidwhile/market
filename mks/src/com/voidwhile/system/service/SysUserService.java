package com.voidwhile.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.market.service.IBaseService;
import com.voidwhile.system.entity.SysUser;


public interface SysUserService extends IBaseService<SysUser> {
	
	/**
	 * @MethodName: save
	 * @Description: 添加用户，同时给用户设置权限（角色）
	 * @param entity
	 * @param roleIds
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月18日 下午4:48:18
	 */
	public void save(SysUser entity, List<String> roleIds) throws DataAccessException;
	
	/**
	 * @MethodName: update
	 * @Description: 更新用户信息，同时更新用户的权限（角色）
	 * @param entity
	 * @param roleIds
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月18日 下午4:53:08
	 */
	public void update(SysUser entity, List<String> roleIds) throws DataAccessException;

	/**
	 * @MethodName: userLogin
	 * @Description: 根据企业ID、用户名查询用户
	 * @param supplierId
	 * @param userName
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月19日 上午10:33:14
	 */
	public SysUser getBySupplierAndUserName(String supplierId, String userName) throws DataAccessException;
	
	/**
	 * @MethodName: getUserRoleById
	 * @Description: 功能描述
	 * @param userId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月18日 下午4:02:14
	 */
	public List<SysUser> getUserRoleById(String userId) throws DataAccessException;
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
	 * @Create Date: 2014-12-3 下午5:20:38
	 */
	int deleteRoleByUserIdAndRoleId(Map<String, String> map) throws DataAccessException;
	/**
	 * 
	 * @MethodName: getSysUserByUserName 
	 * @Description: 根据用户名获取用户对象
	 * @param userName
	 * @return
	 *
	 * @Author: Administrator
	 * @Create Date: 2014-12-10 下午6:52:03
	 */
	SysUser getBySupplierIdAndUserName(String supplierId,String userName);
	void deleteByIds(String[] ids);

	/**
	 * 根据手机号查找用户
	 * @param phone
	 * @return
	 * @throws DataAccessException
	 */
	SysUser getByPhone(String phone) throws DataAccessException;
	/**
	 * 根据用户id修改用户信息
	 * @param uid
	 * @return
	 * @throws DataAccessException
	 */
	int updateById(SysUser entity) throws DataAccessException;
	/**
	 * 查询有权利的审核人
	 * @return
	 * @throws DataAccessException
	 */
	List<SysUser> selectAuditor() throws DataAccessException;

	public SysUser findById(String uid);

	public SysUser findByNameAndPwd(Map<String, Object> rlt);

}
