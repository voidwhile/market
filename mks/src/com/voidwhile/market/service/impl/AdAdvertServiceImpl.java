package com.voidwhile.market.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.market.entity.AdAdvert;
import com.voidwhile.market.mapper.AdAdvertMapper;
import com.voidwhile.market.service.AdAdvertService;
import com.voidwhile.system.bean.PageResult;

@Service
public class AdAdvertServiceImpl implements AdAdvertService {
	
	@Autowired
	private AdAdvertMapper mapper;

	@Override
	public void save(AdAdvert entity) throws DataAccessException {
		mapper.insertSelective(entity);
	}

	@Override
	public void update(AdAdvert entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public AdAdvert getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public PageResult<AdAdvert> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause)
			throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<AdAdvert> pageResult = new PageResult<AdAdvert>(pageNo,pageSize);
		pageResult.setTotal(this.countByMap(param));
		param.put("offset", pageResult.getOffset());
		param.put("pageSize", pageResult.getPageSize());
		pageResult.setList(this.findByMap(param));
		return pageResult;
	}

	@Override
	public List<AdAdvert> findByMap(Map<String, Object> param) throws DataAccessException {
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
