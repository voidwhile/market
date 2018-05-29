package com.voidwhile.market.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.utils.DateUtils;
import com.voidwhile.market.entity.RunRecommend;
import com.voidwhile.market.mapper.RunRecommendMapper;
import com.voidwhile.market.service.RunRecomService;
import com.voidwhile.system.bean.PageResult;

@Service
public class RunRecomServiceImpl implements RunRecomService {
	
	@Autowired
	private RunRecommendMapper mapper;

	@Override
	public void save(RunRecommend entity) throws DataAccessException {
		mapper.insert(entity);
	}

	@Override
	public void update(RunRecommend entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public RunRecommend getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public PageResult<RunRecommend> findPageData(Map<String, Object> param, int pageNo, int pageSize,
			String orderByClause) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RunRecommend> findByMap(Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RunRecommend getTodayRecom() {
		String today = DateUtils.dateToString(new Date());
		return mapper.getTodayRecom(today);
	}

}
