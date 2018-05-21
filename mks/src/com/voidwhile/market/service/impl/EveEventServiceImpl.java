package com.voidwhile.market.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.core.utils.Logger;
import com.voidwhile.core.utils.Slf4JLogger;
import com.voidwhile.market.entity.EveEvent;
import com.voidwhile.market.mapper.EveEventMapper;
import com.voidwhile.market.service.EveEventService;
import com.voidwhile.system.bean.PageResult;

@Service
public class EveEventServiceImpl implements EveEventService {
	
	private Logger log = Slf4JLogger.getLogger(EveEventServiceImpl.class);
	
	@Autowired
	private EveEventMapper mapper;

	@Override
	public void save(EveEvent entity) throws DataAccessException {
		mapper.insertSelective(entity);
	}

	@Override
	public void update(EveEvent entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public EveEvent getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public PageResult<EveEvent> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause)
			throws DataAccessException {
		return null;
	}

	@Override
	public List<EveEvent> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

	@Override
	public List<EveEvent> findEvent() {
		return mapper.findEvent();
	}

	@Override
	public void deleteByIds(String[] ids) {
		mapper.deleteByIds(ids);
	}

}
