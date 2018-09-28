package com.voidwhile.system.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.entity.vo.VSysUser;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 视图v_sys_user操作类
 * @author: zhanzheng
 * @Create Date: 2014年12月11日 下午5:16:00
 *
 * @Version: v1.0
 */
public interface VSysUserMapper {
	/**
	 * @Title: findByMap
	 * @Description: TODO(按条件查询用户列表)
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return List<SysUser>
	 * @throws
	 */
	List<VSysUser> findByMap(Map<String, Object> param) throws DataAccessException;

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
}