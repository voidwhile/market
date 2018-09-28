package com.voidwhile.market.service;

import org.springframework.dao.DataAccessException;

import com.voidwhile.market.entity.AppSession;

public interface AppSessionService {

	/**
	 * @MethodName: saveSession
	 * @Description: 保存用户登陆Session
	 * @param supplierId 企业ID
	 * @param supplierCode 企业代码
	 * @param userId 登陆用户ID
	 * @param userName 登陆用户名
	 * @param clientType 客户端类型
	 * @return 返回SessionID
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年11月19日 上午9:48:48
	 */
	public String saveSession(String supplierId, String supplierCode, String userId, String userName, String clientType) throws DataAccessException;

	/**
	 * @MethodName: validateSession
	 * @Description: 验证当前Session是否有效
	 * @param sessionId
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年11月19日 上午9:44:54
	 */
	public AppSession validateSession(String sessionId) throws DataAccessException;


}
