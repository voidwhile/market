package com.voidwhile.market.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.utils.Tools;
import com.voidwhile.market.entity.MbeAddress;
import com.voidwhile.market.mapper.MbeAddressMapper;
import com.voidwhile.market.service.MbeAddressServcie;
import com.voidwhile.system.bean.PageResult;

@Service
public class MbeAddressServcieImpl implements MbeAddressServcie {
	
	@Autowired
	private MbeAddressMapper mapper;

	@Override
	public void save(MbeAddress entity) throws DataAccessException {
		if (entity.getIsDefault()==null) {
			entity.setIsDefault(false);
		}
		if (entity.getIsDefault()) {
			this.setDefault(entity.getMemberId());
		}
		mapper.insert(entity);
	}

	@Override
	public void update(MbeAddress entity) throws DataAccessException {
		if (entity.getIsDefault()==null) {
			entity.setIsDefault(false);
		}
		if (entity.getIsDefault()) {
			this.setDefault(entity.getMemberId());
		}
		mapper.updateByPrimaryKey(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public MbeAddress getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Tools.toLong(id));
	}

	@Override
	public PageResult<MbeAddress> findPageData(Map<String, Object> param, int pageNo, int pageSize,
			String orderByClause) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MbeAddress> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.selectByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MbeAddress getDefault(Long memberId) {
		return mapper.selectDefault(memberId);
	}

	@Override
	public void setDefault(Long memberId) {
		mapper.setDefault(memberId);
		
	}

}
