package com.voidwhile.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.SysConfig;
import com.voidwhile.system.mapper.SysConfigMapper;
import com.voidwhile.system.service.SysConfigService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统运行参数业务处理类
 * @author: zhanzheng
 * @Create Date: 2014年12月4日 下午3:59:54
 *
 * @Version: v1.0
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

	@Autowired
	private SysConfigMapper sysConfigMapper;
	@Override
	public void save(SysConfig entity) throws DataAccessException {
		entity.setUid(idGenarater.getNextValue());
		sysConfigMapper.insert(entity);

	}

	@Override
	public void update(SysConfig entity) throws DataAccessException {
		sysConfigMapper.updateById(entity);

	}

	@Override
	public void delete(String id) throws DataAccessException {
		sysConfigMapper.deleteById(id);
	}

	@Override
	public void deleteByIds(String[] ids) throws DataAccessException {
		sysConfigMapper.deleteByIds(ids);
	}

	@Override
	public SysConfig getById(String id) throws DataAccessException {
		return sysConfigMapper.getById(id);
	}

	@Override
	public PageResult<SysConfig> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<SysConfig> pageResult = new PageResult<SysConfig>(pageNo, pageSize);
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
	public List<SysConfig> findByMap(Map<String, Object> param) throws DataAccessException {
		return sysConfigMapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return sysConfigMapper.countByMap(param);
	}

	@Override
	public SysConfig getBySupplierIdAndConfigCode(String supplierId, String configCode) throws DataAccessException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplierId", supplierId);
		param.put("configCode", configCode);
		List<SysConfig> list = findByMap(param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.shangyu.service.system.SysConfigService#findByParams(java.util.Map)
	 */
	@Override
	public SysConfig findByParams(Map<String, Object> param) throws DataAccessException {
		// TODO Auto-generated method stub
		return sysConfigMapper.findByParams(param);
	}

}
