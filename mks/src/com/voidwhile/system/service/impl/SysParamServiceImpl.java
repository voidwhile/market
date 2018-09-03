package com.voidwhile.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.SysParam;
import com.voidwhile.system.mapper.SysParamItemMapper;
import com.voidwhile.system.mapper.SysParamMapper;
import com.voidwhile.system.service.SysParamService;

import ch.qos.logback.classic.db.names.TableName;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 编码管理业务类
 * @author: xiaowei
 * @Create Date: 2014年12月3日 上午10:44:54
 *
 * @Version: v1.0
 */

@Service
public class SysParamServiceImpl implements SysParamService {
	@Autowired
	private SysParamMapper sysParamMapper;

	@Autowired
	private SysParamItemMapper sysParamItemMapper;

	@Override
	public void save(SysParam entity) throws DataAccessException {
		entity.setUid(idGenarater.getNextValue());
		sysParamMapper.insert(entity);
	}

	@Override
	public void update(SysParam entity) throws DataAccessException {
		sysParamMapper.updateById(entity);

	}

	@Override
	@Transactional
	public void delete(String id) throws DataAccessException {
		SysParam entity = getById(id);
		if (entity != null) {
			if ("1".equals(entity.getIssystem())) {
				throw new RuntimeException("不能删除系统默认的编码！");
			}
			sysParamMapper.deleteById(id);
			// 级联删除编码值
			sysParamItemMapper.deleteByParamId(entity.getUid());
		}
	}

	@Override
	@Transactional
	public void deleteByIds(String[] ids) throws DataAccessException {
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				SysParam entity = getById(id);
				if (entity != null) {
					if (!"1".equals(entity.getIssystem())) {
						sysParamMapper.deleteById(id);
						// 级联删除编码值
						sysParamItemMapper.deleteByParamId(entity.getUid());
					}
				}
			}
		}
	}

	@Override
	public SysParam getById(String id) throws DataAccessException {
		return sysParamMapper.getById(id);
	}

	@Override
	public PageResult<SysParam> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<SysParam> pageResult = new PageResult<SysParam>(pageNo, pageSize);
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
	public List<SysParam> findByMap(Map<String, Object> param) throws DataAccessException {
		return sysParamMapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return sysParamMapper.countByMap(param);
	}

	@Override
	public SysParam getBySupplierIdAndParamCode(String supplierId, String paramCode) throws DataAccessException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplierId", supplierId);
		param.put("paramCode", paramCode);
		List<SysParam> list = findByMap(param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public String options(String tableName,String code,String des,String whereClause,String selected) {
		String sql = "select CONCAT("+code+",':',"+des+") as val from "+tableName+" where "+whereClause+" order by "+code;
		List<String> options = sysParamMapper.options(sql);
		StringBuffer buffer = new StringBuffer();
		for (String str : options) {
			String[] opt = str.split(":");
			if (selected!=null&&!"".equals(selected)&&opt[0].equals(selected)) {
				buffer.append("<option value='").append(opt[0]).append("' selected='selected'>").append(opt[1]).append("</option>");
			} else {
				buffer.append("<option value='").append(opt[0]).append("'>").append(opt[1]).append("</option>");
			}
		}
		return buffer.toString();
	}

	
}
