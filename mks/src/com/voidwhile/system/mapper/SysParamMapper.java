package com.voidwhile.system.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.entity.SysParam;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统字典管理 mapper
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午11:08:17
 *
 * @Version: v1.0
 */
public interface SysParamMapper {

	/**
	 * @MethodName: insert
	 * @Description: 添加方法
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:08:30
	 */
	int insert(SysParam entity) throws DataAccessException;

	/**
	 * @MethodName: updateById
	 * @Description: 按ID更新
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:08:58
	 */
	int updateById(SysParam entity) throws DataAccessException;

	/**
	 * @MethodName: deleteById
	 * @Description: 按ID删除
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:09:11
	 */
	boolean deleteById(String id) throws DataAccessException;

	/**
	 * @MethodName: getById
	 * @Description: 按ID查询
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:09:18
	 */
	SysParam getById(String id) throws DataAccessException;

	/**
	 * @MethodName: findByMap
	 * @Description: 按条件查询列表
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:09:27
	 */
	List<SysParam> findByMap(Map<String, Object> param) throws DataAccessException;

	/**
	 * @MethodName: countByMap
	 * @Description: 按条件统计记录数量
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:09:40
	 */
	int countByMap(Map<String, Object> param) throws DataAccessException;

	List<String> options(String sql);
}