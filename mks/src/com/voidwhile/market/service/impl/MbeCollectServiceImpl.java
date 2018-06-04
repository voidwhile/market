package com.voidwhile.market.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.market.entity.MbeCollect;
import com.voidwhile.market.mapper.MbeCollectMapper;
import com.voidwhile.market.service.MbeCollectService;
import com.voidwhile.system.bean.PageResult;

@Service
public class MbeCollectServiceImpl implements MbeCollectService {
	
	@Autowired
	private MbeCollectMapper mapper;

	@Override
	public void save(MbeCollect entity) throws DataAccessException {
		mapper.insertSelective(entity);
	}

	@Override
	public void update(MbeCollect entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public MbeCollect getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public PageResult<MbeCollect> findPageData(Map<String, Object> param, int pageNo, int pageSize,
			String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<MbeCollect> pageResult = new PageResult<MbeCollect>(pageNo,pageSize);
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
	public List<MbeCollect> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.selectByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

}
