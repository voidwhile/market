package com.voidwhile.system.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.entity.SysSupplier;


/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 企业管理 Mapper
 * @author: zhanzheng
 * @Create Date: 2014年10月24日 上午11:35:44
 *
 * @Version: v1.0
 */
public interface SysSupplierMapper {

	/**
	 * @MethodName: insert
	 * @Description: 添加
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:36:14
	 */
	int insert(SysSupplier entity) throws DataAccessException;

	/**
	 * @MethodName: updateById
	 * @Description: 按ID更新
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:36:27
	 */
	int updateById(SysSupplier entity) throws DataAccessException;

	/**
	 * @MethodName: deleteById
	 * @Description: 按ID删除
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:36:41
	 */
	boolean deleteById(String id) throws DataAccessException;

	/**
	 * @MethodName: getById
	 * @Description: 按ID查询商家信息
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:36:53
	 */
	SysSupplier getById(String id) throws DataAccessException;

	/**
	 * @MethodName: findByMap
	 * @Description: 按条件查询
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:37:15
	 */
	List<SysSupplier> findByMap(Map<String, Object> param) throws DataAccessException;

	/**
	 * @MethodName: countByMap
	 * @Description: 按条件统计数量
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:37:24
	 */
	int countByMap(Map<String, Object> param) throws DataAccessException;
	/**
	 * 
	 * @MethodName: deleteByIds 
	 * @Description: 批量删除公告
	 * @param ids
	 * @throws DataAccessException
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-22 下午2:42:42
	 */
	void deleteByIds(String[] ids) throws DataAccessException;
}