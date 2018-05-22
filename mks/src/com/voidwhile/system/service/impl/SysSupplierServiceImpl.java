package com.voidwhile.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.SysSupplier;
import com.voidwhile.system.mapper.SysSupplierMapper;
import com.voidwhile.system.service.SysSupplierService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 企业管理业务处理类
 * @author: xiaowei
 * @Create Date: 2014年11月18日 下午2:31:42
 *
 * @Version: v1.0
 */
@Service
public class SysSupplierServiceImpl implements SysSupplierService {

	@Autowired
	private SysSupplierMapper supplierMapper;

	public void save(SysSupplier entity) throws DataAccessException {
		entity.setUid(idGenarater.getNextValue());
		supplierMapper.insert(entity);
	}

	public void update(SysSupplier entity) throws DataAccessException {
		supplierMapper.updateById(entity);
	}

	public void delete(String id) throws DataAccessException {
		supplierMapper.deleteById(id);
	}

	@Override
	public SysSupplier getById(String id) throws DataAccessException {
		return supplierMapper.getById(id);
	}

	@Override
	public SysSupplier getBySupplierCode(String supplierCode, boolean isOfficial) throws DataAccessException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplierCode", supplierCode);
		if (isOfficial) {
			param.put("official", true);
		}
		List<SysSupplier> list = this.findByMap(param);
		if (null != list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public PageResult<SysSupplier> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<SysSupplier> pageResult = new PageResult<SysSupplier>(pageNo, pageSize);
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
	public List<SysSupplier> findByMap(Map<String, Object> param) throws DataAccessException {
		return supplierMapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return supplierMapper.countByMap(param);
	}

	@Override
	public void deleteByIds(String[] ids) throws DataAccessException {
		supplierMapper.deleteByIds(ids);
	}


}
