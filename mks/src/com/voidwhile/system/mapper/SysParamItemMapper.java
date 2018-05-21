package com.voidwhile.system.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.entity.SysParamItem;


/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统字典内容管理 Mapper
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午11:10:11
 *
 * @Version: v1.0
 */
public interface SysParamItemMapper {

	/**
	 * @MethodName: insert
	 * @Description: 添加
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:10:20
	 */
	int insert(SysParamItem entity) throws DataAccessException;

	/**
	 * @MethodName: updateById
	 * @Description: 按ID更新
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:10:28
	 */
	int updateById(SysParamItem entity) throws DataAccessException;

	/**
	 * @MethodName: deleteById
	 * @Description: 按ID删除
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:10:38
	 */
	boolean deleteById(String id) throws DataAccessException;

	/**
	 * @MethodName: deleteByParamId
	 * @Description: 根据编码ID删除编码值
	 * @param paramId
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月3日 上午11:13:21
	 */
	int deleteByParamId(String paramId) throws DataAccessException;

	/**
	 * @MethodName: getById
	 * @Description: 按ID查询
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:10:45
	 */
	SysParamItem getById(String id) throws DataAccessException;


	/**
	 * @MethodName: findByMap
	 * @Description: 按条件查询列表
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 上午11:10:52
	 */
	List<SysParamItem> findByMap(Map<String, Object> param) throws DataAccessException;

	/**
	 * @Title: countByMap
	 * @Description: TODO(按统计记录数量)
	 * @param param
	 * @return
	 * @throws DataAccessException
	 * @return int
	 * @throws
	 */
	int countByMap(Map<String, Object> param) throws DataAccessException;
}