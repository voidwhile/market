package com.voidwhile.market.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.core.jdbc.sql.UUIDHexGenerator;
import com.voidwhile.market.entity.AppSession;
import com.voidwhile.market.mapper.AppSessionMapper;
import com.voidwhile.market.service.AppSessionService;

@Service
public class AppSessionServiceImpl implements AppSessionService {

	private final UUIDHexGenerator idGenarater = UUIDHexGenerator.getInstance();

	@Autowired
	private AppSessionMapper appSessionMapper;

	@Override
	public String saveSession(String supplierId, String supplierCode, String userId, String userName, String clientType) throws DataAccessException {
		if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(userName)) {
			// 首先清除该用户之前登陆的信息
			appSessionMapper.deleteBySupplierAndUserName(userId, userName);
			
			AppSession session = new AppSession();
			session.setSessionId(idGenarater.getNextValue());
			session.setUserId(userId);
			session.setUserName(userName);
			session.setClientType(clientType);
			appSessionMapper.insert(session);
			return session.getSessionId();
		}
		return null;
	}

	@Override
	public AppSession validateSession(String sessionId) throws DataAccessException {
		System.out.println(sessionId);
		return appSessionMapper.getBySessionId(sessionId);
	}

}
