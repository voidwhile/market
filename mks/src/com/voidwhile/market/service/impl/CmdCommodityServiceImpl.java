/** 
 * Project Name:mks 
 * File Name:CmdCommodityServiceImpl.java 
 * Package Name:com.voidwhile.market.service.impl 
 * Date:2017年9月24日下午9:09:42 
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
import com.voidwhile.market.entity.CmdCommodity;
import com.voidwhile.market.mapper.CmdCommodityMapper;
import com.voidwhile.market.mapper.CmdPriceMapper;
import com.voidwhile.market.service.CmdCommodityService;
import com.voidwhile.system.bean.PageResult;

/** 
 * ClassName:CmdCommodityServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年9月24日 下午9:09:42 <br/> 
 * @author   zhangzheng 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
@Service
public class CmdCommodityServiceImpl implements CmdCommodityService {
	
	@Autowired
	private CmdCommodityMapper mapper;
	
	@Autowired
	private CmdPriceMapper priceMapper;

	@Override
	public void save(CmdCommodity entity) throws DataAccessException {
		mapper.insert(entity);
	}

	@Override
	public void update(CmdCommodity entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);

	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Tools.toLong(id));

	}

	@Override
	public CmdCommodity getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public PageResult<CmdCommodity> findPageData(Map<String, Object> param, int pageNo, int pageSize,
			String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<CmdCommodity> pageResult = new PageResult<CmdCommodity>(pageNo,pageSize);
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
	public List<CmdCommodity> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.selectByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

	@Override
	public void deleteByIds(String[] ids) {
		for(String cmdId:ids){
			priceMapper.deleteByCmdId(Long.valueOf(cmdId));
		}
		mapper.deleteByIds(ids);
	}

	@Override
	public List<CmdCommodity> getOption() {
		return mapper.findOption();
	}

}
  