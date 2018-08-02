/** 
 * Project Name:mks 
 * File Name:CfgSpecificationServiceImpl.java 
 * Package Name:com.voidwhile.market.service.impl 
 * Date:2017年9月21日下午10:20:00 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.voidwhile.market.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.utils.Tools;
import com.voidwhile.market.entity.CfgSpecification;
import com.voidwhile.market.mapper.CfgSpecificationMapper;
import com.voidwhile.market.service.CfgSpecificationService;
import com.voidwhile.system.bean.PageResult;

/** 
 * ClassName:CfgSpecificationServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年9月21日 下午10:20:00 <br/> 
 * @author   zhangzheng 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
@Service
public class CfgSpecificationServiceImpl implements CfgSpecificationService {
	
	@Autowired
	private CfgSpecificationMapper mapper;

	@Override
	public void save(CfgSpecification entity) throws DataAccessException {
		mapper.insert(entity);
	}

	@Override
	public void update(CfgSpecification entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public CfgSpecification getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public PageResult<CfgSpecification> findPageData(Map<String, Object> param, int pageNo, int pageSize,
			String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<CfgSpecification> pageResult = new PageResult<CfgSpecification>(pageNo,pageSize);
		pageResult.setTotal(this.countByMap(param));
		param.put("offset", pageResult.getOffset());
		param.put("pageSize", pageResult.getPageSize());
		if (!"".equals(StringUtils.trimToEmpty(orderByClause))) {
			param.put("orderByClause", orderByClause);
		}
		pageResult.setList(this.findByMap(param));
		return pageResult;
	}

	@Override
	public List<CfgSpecification> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.selectByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

	@Override
	public void deleteByIds(String[] ids) {
		mapper.deleteByIds(ids);
	}

}
  