package com.voidwhile.market.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.utils.Tools;
import com.voidwhile.market.entity.PubImage;
import com.voidwhile.market.mapper.PubImageMapper;
import com.voidwhile.market.service.PubImageService;
import com.voidwhile.system.bean.PageResult;

@Service
public class PubImageServiceImpl implements PubImageService {
	
	@Autowired
	private PubImageMapper mapper;

	

	@Override
	public List<PubImage> findByMap(Map<String, Object> param) {
		return mapper.selectByMap(param);
	}


	@Override
	public void insertimage(PubImage image) {
		// TODO Auto-generated method stub
		mapper.insertSelective(image);
	}


	@Override
	public void save(PubImage entity) throws DataAccessException {
		mapper.insert(entity);
		
	}


	@Override
	public void update(PubImage entity) throws DataAccessException {
		mapper.updateByPrimaryKey(entity);
	}


	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(id);
	}


	@Override
	public PubImage getById(String id) throws DataAccessException {
		return mapper.findById(Tools.toInteger(id));
	}


	@Override
	public PageResult<PubImage> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void deleteByBizId(PubImage record) {
		mapper.deleteByBizId(record);
		
	}

	 
}
