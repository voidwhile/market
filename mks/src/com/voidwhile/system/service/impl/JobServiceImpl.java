package com.voidwhile.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.Job;
import com.voidwhile.system.mapper.JobMapper;
import com.voidwhile.system.service.JobService;


@Service
public class JobServiceImpl implements JobService {
	
	@Autowired
	private JobMapper mapper;

	@Override
	public void save(Job entity) throws DataAccessException {
		mapper.insert(entity);
	}

	@Override
	public void update(Job entity) throws DataAccessException {

	}

	@Override
	public void delete(String id) throws DataAccessException {

	}

	@Override
	public Job getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public PageResult<Job> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause)
			throws DataAccessException {
		return null;
	}

	@Override
	public List<Job> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return 0;
	}

	@Override
	public List<Job> execList() {
		return mapper.findExecList();
	}

	@Override
	public void updateExecTime(Long jobId) {
		mapper.updateExecTime(jobId);
	}

	@Override
	public void start(Long jobId) {
		mapper.start(jobId);
	}

	@Override
	public void stop(Long jobId) {
		mapper.stop(jobId);
	}

}
