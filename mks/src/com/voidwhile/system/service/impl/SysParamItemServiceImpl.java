package com.voidwhile.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.SysParamItem;
import com.voidwhile.system.mapper.SysParamItemMapper;
import com.voidwhile.system.service.SysParamItemService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 编码处理业务类
 * @author: xiaowei
 * @Create Date: 2014年12月3日 下午2:53:42
 *
 * @Version: v1.0
 */
@Service
public class SysParamItemServiceImpl implements SysParamItemService {

	@Autowired
	private SysParamItemMapper sysParamItemMapper;

	@Override
	public void save(SysParamItem entity) throws DataAccessException {
		entity.setUid(idGenarater.getNextValue());
		sysParamItemMapper.insert(entity);
	}

	@Override
	public void update(SysParamItem entity) throws DataAccessException {
		sysParamItemMapper.updateById(entity);

	}

	@Override
	public void delete(String id) throws DataAccessException {
		sysParamItemMapper.deleteById(id);
	}

	@Override
	public int batchInsert(String paramId, List<SysParamItem> entities) throws DataAccessException {
		if (entities != null && entities.size() > 0) {
			for (SysParamItem entity : entities) {
				entity.setParamId(paramId);
				save(entity);
			}
			return entities.size();
		}
		return 0;
	}

	@Override
	public int batchDelete(List<SysParamItem> entities) throws DataAccessException {
		if (entities != null && entities.size() > 0) {
			for (SysParamItem entity : entities) {
				delete(entity.getUid());
			}
			return entities.size();
		}
		return 0;
	}

	@Override
	public int batchUpdate(List<SysParamItem> entities) throws DataAccessException {
		if (entities != null && entities.size() > 0) {
			for (SysParamItem entity : entities) {
				update(entity);
			}
			return entities.size();
		}
		return 0;
	}

	@Override
	public SysParamItem getById(String id) throws DataAccessException {
		return sysParamItemMapper.getById(id);
	}

	/**
	 * @MethodName: findPageData
	 * @Description:该方法不使用，没有分页方法
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @param orderByClause
	 * @return
	 * @throws DataAccessException
	 *
	 * @see com.voidwhile.market.service.IBaseService#findPageData(java.util.Map, int,
	 *      int, java.lang.String)
	 */
	@Override
	@Deprecated
	public PageResult<SysParamItem> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException {
		return null;
	}

	@Override
	public List<SysParamItem> findByMap(Map<String, Object> param) throws DataAccessException {
		return sysParamItemMapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return sysParamItemMapper.countByMap(param);
	}

	@Override
	public List<SysParamItem> findByParamId(String paramId, boolean isValid) throws DataAccessException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("paramId", paramId);
		param.put("status", isValid ? "1" : "0");
		param.put("orderByClause", "rank asc");
		return findByMap(param);
	}

}
