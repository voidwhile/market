package com.voidwhile.market.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.utils.Tools;
import com.voidwhile.market.entity.MbeMember;
import com.voidwhile.market.mapper.MbeMemberMapper;
import com.voidwhile.market.service.MbeMemberService;
import com.voidwhile.system.bean.PageResult;

@Service
public class MbeMemberServiceImpl implements MbeMemberService {
	
	@Autowired
	private MbeMemberMapper mapper;

	@Override
	public void save(MbeMember entity) throws DataAccessException {
		mapper.insert(entity);
	}

	@Override
	public void update(MbeMember entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public MbeMember getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public PageResult<MbeMember> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause)
			throws DataAccessException {
		return null;
	}

	@Override
	public List<MbeMember> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.selectByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

}
