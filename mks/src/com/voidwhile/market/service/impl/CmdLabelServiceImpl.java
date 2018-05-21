/** 
 * Project Name:mks 
 * File Name:CmdLabelServiceImpl.java 
 * Package Name:com.voidwhile.market.service.impl 
 * Date:2017年9月24日下午3:39:00 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.voidwhile.market.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.utils.Tools;
import com.voidwhile.market.entity.CmdLabel;
import com.voidwhile.market.mapper.CmdLabelMapper;
import com.voidwhile.market.service.CmdLabelService;
import com.voidwhile.system.bean.PageResult;

/** 
 * ClassName:CmdLabelServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年9月24日 下午3:39:00 <br/> 
 * @author   zhangzheng 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
@Service
public class CmdLabelServiceImpl implements CmdLabelService{
	
	@Autowired
	private CmdLabelMapper mapper;

	@Override
	public void save(CmdLabel entity) throws DataAccessException {
		mapper.insert(entity);
	}

	@Override
	public void update(CmdLabel entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public CmdLabel getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public PageResult<CmdLabel> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause)
			throws DataAccessException {
		return null;
	}

	@Override
	public List<CmdLabel> findByMap(Map<String, Object> param) throws DataAccessException {
		return null;
	}
	
	@Override
	public List<CmdLabel> findByCmdId(Long cmdId) throws DataAccessException {
		return mapper.selectByCmdId(cmdId);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return 0;
	}

	@Override
	public int deleteByCmdType(Integer cmdType) {
		return mapper.deleteByCmdType(cmdType);
	}

	@Override
	public int deleteByCmdId(Long cmdId) {
		return mapper.deleteByCmdId(cmdId);
	}

}
  