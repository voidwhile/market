package com.voidwhile.market.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.utils.DateUtils;
import com.voidwhile.market.entity.RunSale;
import com.voidwhile.market.mapper.RunSaleMapper;
import com.voidwhile.market.service.RunSaleService;
import com.voidwhile.system.bean.PageResult;

@Service
public class RunSaleServiceImpl implements RunSaleService {
	
	@Autowired
	private RunSaleMapper mapper;

	@Override
	public void save(RunSale entity) throws DataAccessException {
		mapper.insertSelective(entity);

	}

	@Override
	public void update(RunSale entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);

	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public RunSale getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public PageResult<RunSale> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause)
			throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<RunSale> pageResult = new PageResult<RunSale>(pageNo,pageSize);
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
	public List<RunSale> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.selectByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

	@Override
	public RunSale getTodaySale() {
		String today = DateUtils.dateToString(new Date());
		return mapper.getTodaySale(today);
	}

}
