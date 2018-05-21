package com.voidwhile.market.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.utils.Tools;
import com.voidwhile.market.entity.RepInventory;
import com.voidwhile.market.mapper.RepInventoryMapper;
import com.voidwhile.market.service.RepInventoryService;
import com.voidwhile.system.bean.PageResult;

@Service
public class RepInventoryServiceImpl implements RepInventoryService {
	
	@Autowired
	private RepInventoryMapper mapper;

	@Override
	public void save(RepInventory entity) throws DataAccessException {
		mapper.insertSelective(entity);
	}

	@Override
	public void update(RepInventory entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);

	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Tools.toLong(id));

	}

	@Override
	public RepInventory getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public PageResult<RepInventory> findPageData(Map<String, Object> param, int pageNo, int pageSize,
			String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<RepInventory> pageResult = new PageResult<RepInventory>(pageNo,pageSize);
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
	public List<RepInventory> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.selectByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

}
