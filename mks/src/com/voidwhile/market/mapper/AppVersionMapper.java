package com.voidwhile.market.mapper;

import org.springframework.dao.DataAccessException;

import com.voidwhile.market.entity.AppVersion;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 版本管理 Mapper
 * @author: zhanzheng
 * @Create Date: 2014年12月22日 上午11:18:11
 *
 * @Version: v1.0
 */
public interface AppVersionMapper {
	/**
	 * @MethodName: insert
	 * @Description: 添加APP版本
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月22日 上午11:19:19
	 */
	int insert(AppVersion entity) throws DataAccessException;

	/**
	 * @MethodName: update
	 * @Description: 更新APP版本信息
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月22日 上午11:23:23
	 */
	int update(AppVersion entity) throws DataAccessException;

	/**
	 * @MethodName: getBySupplierId
	 * @Description: 获取企业APP版本信息
	 * @param supplierid
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月22日 上午11:23:45
	 */
	AppVersion getBySupplierId(String supplierid) throws DataAccessException;

}