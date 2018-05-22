package com.voidwhile.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.core.jdbc.sql.UUIDHexGenerator;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.SysLog;
import com.voidwhile.system.mapper.SysLogMapper;
import com.voidwhile.system.service.SysLogService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统日志管理 Service
 * @author: xiaowei
 * @Create Date: 2014年10月24日 下午1:09:41
 *
 * @Version: v1.0
 */
@Service
public class SysLogServiceImpl implements SysLogService {
	/**
	 * ID生成器
	 * */
	public UUIDHexGenerator idGenarater = UUIDHexGenerator.getInstance();

	@Autowired
	private SysLogMapper sysLogMapper;

	/**
	 * @MethodName: save
	 * @Description: 系统日志保存
	 * @param entity
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysLogService#save(com.shangyu.mall.domain.SysLog)
	 */
	public void save(SysLog entity) throws DataAccessException {
		entity.setUid(this.idGenarater.getNextValue());
		sysLogMapper.insert(entity);
	}

	@Override
	public void deleteByIds(String[] ids) throws DataAccessException {
		sysLogMapper.deleteByIds(ids);
	}

	/**
	 * @MethodName: findPageData
	 * @Description: 分页数据查询
	 * @param param (查询条件）
	 * @param pageNo 当前页码
	 * @param pageSize 每页大小
	 * @param orderByClause 排序方式
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysLogService#findPageData(java.util.Map,
	 *      int, int, java.lang.String)
	 */
	public PageResult<SysLog> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<SysLog> pageResult = new PageResult<SysLog>(pageNo, pageSize);
		pageResult.setTotal(this.countByMap(param));

		param.put("offset", pageResult.getOffset());
		param.put("pageSize", pageResult.getPageSize());
		if (!"".equals(StringUtils.trimToEmpty(orderByClause))) {
			param.put("orderByClause", orderByClause);
		}
		pageResult.setList(this.findByMap(param));
		return pageResult;
	}

	/**
	 * @MethodName: findByMap
	 * @Description: 列表查询
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysLogService#findByMap(java.util.Map)
	 */
	public List<SysLog> findByMap(Map<String, Object> param) throws DataAccessException {
		return sysLogMapper.findByMap(param);
	}

	/**
	 * @MethodName: countByMap
	 * @Description: 统计系统日志记录数
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.shangyu.mall.service.SysLogService#countByMap(java.util.Map)
	 */
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return sysLogMapper.countByMap(param);
	}

	
}
