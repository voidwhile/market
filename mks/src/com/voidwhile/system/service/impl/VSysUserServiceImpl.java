package com.voidwhile.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.vo.VSysUser;
import com.voidwhile.system.mapper.VSysUserMapper;
import com.voidwhile.system.service.VSysUserService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 视图v_sys_user 业务操作类
 * @author: xiaowei
 * @Create Date: 2014年12月12日 上午9:30:09
 *
 * @Version: v1.0
 */
@Service
public class VSysUserServiceImpl implements VSysUserService {
	@Autowired
	private VSysUserMapper userMapper;

	@Override
	public PageResult<VSysUser> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<VSysUser> pageResult = new PageResult<VSysUser>(pageNo, pageSize);
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
	public List<VSysUser> findByMap(Map<String, Object> param) throws DataAccessException {
		return userMapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return userMapper.countByMap(param);
	}

}
