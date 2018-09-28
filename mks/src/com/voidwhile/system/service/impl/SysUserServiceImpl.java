package com.voidwhile.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voidwhile.common.utils.SysUserHuanXinService;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.SysUser;
import com.voidwhile.system.mapper.SysUserMapper;
import com.voidwhile.system.service.SysUserService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 用户操作业务逻辑类
 * @author: zhanzheng
 * @Create Date: 2014年10月24日 下午1:16:29
 *
 * @Version: v1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {
	SysUserHuanXinService hx = new SysUserHuanXinService();
	@Autowired
	private SysUserMapper userMapper;

	@Override
	@Transactional
	public void save(SysUser entity) throws DataAccessException {
//		entity.setUserid(this.idGenarater.getNextValue());
		userMapper.insert(entity);
//		hx.addHuanXinUser(entity.getUserid()+"", entity.getPlainPassword(), entity.getRealName());
	}

	@Transactional
	public void save(SysUser entity, List<String> roleIds) throws DataAccessException {
		save(entity);
		String userId = entity.getUid()+"";
		if (roleIds != null && roleIds.size() > 0) {
			for (String roleId : roleIds) {
				if (StringUtils.isNotEmpty(roleId)) {
					userMapper.insertUserRole(userId, roleId);
				}
			}
		}
	}

	@Override
	public void update(SysUser entity) throws DataAccessException {
		userMapper.updateById(entity);
//		hx.putHuanXinUser(entity.getUserid(), entity.getPlainPassword(), entity.getRealName());
	}

	@Transactional
	public void update(SysUser entity, List<String> roleIds) throws DataAccessException {
		update(entity);
		String userId = entity.getUid()+"";
		if (roleIds != null && roleIds.size() > 0) {
			userMapper.deleteRoleByUserId(userId);
			for (String roleId : roleIds) {
				if (StringUtils.isNotEmpty(roleId)) {
					userMapper.insertUserRole(userId, roleId);
				}
			}
		}
	}

	@Transactional
	public void delete(String id) throws DataAccessException {
		userMapper.deleteById(id);
		userMapper.deleteRoleByUserId(id);
		//删除环信账号
		hx.deleteByuid(id);
	}

	public SysUser getById(String id) throws DataAccessException {
		return userMapper.getById(id);
	}

	/**
	 * @Title: getByUserName
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param userName
	 * @return
	 * @throws DataAccessException
	 * @return SysUser
	 * @throws
	 */
	@Override
	public SysUser getBySupplierAndUserName(String supplierId, String userName) throws DataAccessException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplierId", supplierId);
		param.put("userNameLogin", userName);
		List<SysUser> list = this.findByMap(param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public PageResult<SysUser> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<SysUser> pageResult = new PageResult<SysUser>(pageNo, pageSize);
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
	public List<SysUser> findByMap(Map<String, Object> param) throws DataAccessException {
		return userMapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return userMapper.countByMap(param);
	}

	@Override
	public List<SysUser> getUserRoleById(String userId) throws DataAccessException {
		return userMapper.getUserRoleById(userId);
	}

	@Override
	public int deleteRoleByUserIdAndRoleId(Map<String, String> map) throws DataAccessException {
		return userMapper.deleteRoleByUserIdAndRoleId(map);
	}

	@Override
	public SysUser getBySupplierIdAndUserName(String supplierId, String userName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplierId", supplierId);
		param.put("userName", userName);
		List<SysUser> list = userMapper.findByMap(param);
		if (!list.isEmpty()) {
			SysUser u = list.get(0);
			return u;
		}
		return null;
	}

	@Override
	@Transactional
	public void deleteByIds(String[] ids) {
		if (ids != null && ids.length > 0) {
			for (String userId : ids) {
				delete(userId);
			}
		}
	}


	@Override
	public SysUser getByPhone(String phone) throws DataAccessException {
		return userMapper.getByPhone(phone);
	}

	@Override
	public int updateById(SysUser entity) throws DataAccessException {
		
		return userMapper.updateById(entity);
	}

	@Override
	public List<SysUser> selectAuditor() throws DataAccessException {
		
		return userMapper.selectAuditor();
	}

	@Override
	public SysUser findById(String uid) {
		
		return userMapper.findById(uid);
	}

	@Override
	public SysUser findByNameAndPwd(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return userMapper.findByNameAndPwd(param);
	}
    

}
