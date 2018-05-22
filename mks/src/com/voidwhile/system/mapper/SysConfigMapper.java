package com.voidwhile.system.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.entity.SysConfig;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统运行参数管理 Mapper
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午11:31:26
 *
 * @Version: v1.0
 */
public interface SysConfigMapper {

	/**
	 * @MethodName: insert
	 * @Description: 系统运行参数添加
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:31:37
	 */
	int insert(SysConfig entity) throws DataAccessException;

	/**
	 * @MethodName: updateById
	 * @Description: 系统运行参数更新
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:32:54
	 */
	int updateById(SysConfig entity) throws DataAccessException;

	/**
	 * @MethodName: updateByIdSelective
	 * @Description: 按ID更新，只更新有值的字段
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:33:04
	 */
	int updateByIdSelective(SysConfig entity) throws DataAccessException;

	/**
	 * @MethodName: deleteById
	 * @Description: 按ID删除
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:33:14
	 */
	boolean deleteById(String id) throws DataAccessException;

	/**
	 * @MethodName: deleteByIds
	 * @Description: 运行参数批量删除
	 * @param ids
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 下午8:06:07
	 */
	void deleteByIds(String[] ids) throws DataAccessException;

	/**
	 * @MethodName: getById
	 * @Description: 按ID查询
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:33:23
	 */
	SysConfig getById(String id) throws DataAccessException;

	/**
	 * @MethodName: findByMap
	 * @Description: 按条件查询列表
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:33:31
	 */
	List<SysConfig> findByMap(Map<String, Object> param) throws DataAccessException;

	/**
	 * @MethodName: countByMap
	 * @Description: 按条件统计记录数量
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:33:47
	 */
	int countByMap(Map<String, Object> param) throws DataAccessException;
	
	SysConfig findByParams(Map<String, Object> param) throws DataAccessException;
}