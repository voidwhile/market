package com.voidwhile.market.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.utils.Tools;
import com.voidwhile.market.entity.CmdSpecification;
import com.voidwhile.market.mapper.CmdSpecificationMapper;
import com.voidwhile.market.service.CmdSpecificationService;
import com.voidwhile.system.bean.PageResult;

@Service
public class CmdSpecificationServiceImpl implements CmdSpecificationService {
	
	@Autowired
	private CmdSpecificationMapper mapper;

	@Override
	public void save(CmdSpecification entity) throws DataAccessException {
		mapper.insert(entity);
	}

	@Override
	public void update(CmdSpecification entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public CmdSpecification getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public PageResult<CmdSpecification> findPageData(Map<String, Object> param, int pageNo, int pageSize,
			String orderByClause) throws DataAccessException {
		return null;
	}

	@Override
	public List<CmdSpecification> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.selectByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteByCmdId(Long cmdId) {
		mapper.deleteByCmdId(cmdId);
	}

}
