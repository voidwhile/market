package com.voidwhile.market.mapper;

import com.voidwhile.market.entity.AppSession;

/**
 * 
 * CopyRright (c) 2017: 
 * 
 * @Description: 掌上APP登陆Session控制 Mapper
 * @author: xiaowei
 * @Create Date: 2014年11月18日 上午11:15:09
 *
 * @Version: v1.0
 */
public interface AppSessionMapper {

	/**
	 * @MethodName: insert
	 * @Description: 添加登陆Session
	 * @param entity
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月18日 上午11:17:22
	 */
	int insert(AppSession entity);

	/**
	 * @MethodName: getBySessionId
	 * @Description: 根据sessionId查询登陆Session
	 * @param sessionId
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月19日 上午9:36:22
	 */
	AppSession getBySessionId(String sessionId);

	/**
	 * @MethodName: deleteBySupplierAndUserName
	 * @Description: 删除登陆用户的登陆Session信息
	 * @param supplierId
	 * @param userName
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月19日 上午9:38:29
	 */
	int deleteBySupplierAndUserName(String supplierId, String userName);

}