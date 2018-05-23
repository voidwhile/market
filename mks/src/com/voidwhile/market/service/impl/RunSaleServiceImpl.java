package com.voidwhile.market.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RunSale> findByMap(Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

}
