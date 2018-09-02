/** 
 * Project Name:mks 
 * File Name:WechatUserServiceImpl.java 
 * Package Name:com.voidwhile.market.service.impl 
 * Date:2018年9月2日下午2:44:54 
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.voidwhile.market.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.market.entity.WechatUser;
import com.voidwhile.market.mapper.WechatUserMapper;
import com.voidwhile.market.service.WechatUserService;
import com.voidwhile.system.bean.PageResult;

/** 
 * ClassName:WechatUserServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2018年9月2日 下午2:44:54 <br/> 
 * @author   zhangzheng 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
@Service
public class WechatUserServiceImpl implements WechatUserService {
	
	@Autowired
	private WechatUserMapper mapper;

	@Override
	public void save(WechatUser entity) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(WechatUser entity) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public WechatUser getById(String id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult<WechatUser> findPageData(Map<String, Object> param, int pageNo, int pageSize,
			String orderByClause) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WechatUser> findByMap(Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public WechatUser findByMemberId(Long memberId) {
		return mapper.findByMemberId(memberId);
	}

}
  