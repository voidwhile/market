package com.voidwhile.market.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.market.entity.OdrOrder;
import com.voidwhile.market.entity.OdrOrderDetail;
import com.voidwhile.market.mapper.OdrOrderDetailMapper;
import com.voidwhile.market.mapper.OdrOrderMapper;
import com.voidwhile.market.service.OrderService;
import com.voidwhile.system.bean.PageResult;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OdrOrderMapper mapper;
	@Autowired
	private OdrOrderDetailMapper detailMapper;
	

	@Override
	public void save(OdrOrder entity) throws DataAccessException {
		mapper.insertSelective(entity);
	}

	@Override
	public void update(OdrOrder entity) throws DataAccessException {
		
	}

	@Override
	public void delete(String id) throws DataAccessException {
		
	}

	@Override
	public OdrOrder getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public PageResult<OdrOrder> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause)
			throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<OdrOrder> pageResult = new PageResult<OdrOrder>(pageNo,pageSize);
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
	public List<OdrOrder> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

	@Override
	public PageResult<OdrOrderDetail> getDetail(Map<String, Object> param, int pageNo, int pageSize) {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<OdrOrderDetail> pageResult = new PageResult<OdrOrderDetail>(pageNo,pageSize);
		pageResult.setTotal(detailMapper.countByOrderId(param));
		param.put("offset", pageResult.getOffset());
		param.put("pageSize", pageResult.getPageSize());
		pageResult.setList(detailMapper.findByOrderId(param));
		return pageResult;
	}

	@Override
	public void deliver(Long orderId) {
		mapper.deliver(orderId);
	}

}
