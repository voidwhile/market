package com.voidwhile.system.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.entity.SysLog;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统日志操作 Mapper
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午11:16:38
 *
 * @Version: v1.0
 */
public interface SysLogMapper {

	/**
	 * @MethodName: insert
	 * @Description: 插入日志
	 * @param entity
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:16:51
	 */
	void insert(SysLog entity) throws DataAccessException;

	/**
	 * @MethodName: deleteByIds
	 * @Description: 日志批量删除
	 * @param ids
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 下午8:06:07
	 */
	void deleteByIds(String[] ids) throws DataAccessException;

	/**
	 * @MethodName: findByMap
	 * @Description: 日志查询
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:17:00
	 */
	List<SysLog> findByMap(Map<String, Object> param) throws DataAccessException;

	/**
	 * @MethodName: countByMap
	 * @Description: 统计日志数量
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:17:08
	 */
	int countByMap(Map<String, Object> param) throws DataAccessException;
}