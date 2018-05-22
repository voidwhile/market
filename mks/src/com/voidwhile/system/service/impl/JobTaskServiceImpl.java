package com.voidwhile.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.JobTask;
import com.voidwhile.system.mapper.JobTaskMapper;
import com.voidwhile.system.service.JobTaskService;


@Service
public class JobTaskServiceImpl implements JobTaskService {
	
	@Autowired
	private JobTaskMapper mapper;

	@Override
	public void save(JobTask entity) throws DataAccessException {
		mapper.insert(entity);
	}
	
	

	@Override
	public void update(JobTask entity) throws DataAccessException {
		mapper.updateByPrimaryKey(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {

	}

	@Override
	public JobTask getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public PageResult<JobTask> findPageData(Map<String, Object> param, int pageNo, int pageSize,
			String orderByClause) throws DataAccessException {
		return null;
	}

	@Override
	public List<JobTask> findByMap(Map<String, Object> param) throws DataAccessException {
		return null;
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<JobTask> execList() {
		return mapper.findExecList();
	}



	@Override
	public void createTask(Long jobId) {
		mapper.insertSelective(jobId);
	}

}
