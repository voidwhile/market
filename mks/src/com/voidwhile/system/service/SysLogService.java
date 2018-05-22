package com.voidwhile.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.SysLog;

public interface SysLogService {

	/** 
	 * @Title: save 
	 * @Description: TODO(系统日志 添加) 
	 * @param entity
	 * @throws DataAccessException 
	 * @return void
	 * @throws 
	 */
	public void save(SysLog entity) throws DataAccessException;

	/**
	 * @MethodName: deleteByIds
	 * @Description: 日志批量删除
	 * @param ids
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 下午8:06:42
	 */
	void deleteByIds(String[] ids) throws DataAccessException;

	/**
	 * @Title: findPageData
	 * @Description: TODO(分页数据查询)
	 * @param param (查询条件）
	 * @param pageNo 当前页码
	 * @param pageSize 每页大小
	 * @param orderByClause 排序方式
	 * @return
	 * @throws DataAccessException
	 * @return PageResult<SysLog>
	 * @throws
	 */
	public PageResult<SysLog> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException;

	/** 
	 * <p>Title: findByMap</p> 
	 * <p>Description: 列表查询</p> 
	 * @param param
	 * @return
	 * @throws DataAccessException 
	 */
	public List<SysLog> findByMap(Map<String, Object> param) throws DataAccessException;

	/** 
	 * <p>Title: countByMap</p> 
	 * <p>Description: 统计系统日志记录数</p> 
	 * @param param
	 * @return
	 * @throws DataAccessException 
	 */
	public int countByMap(Map<String, Object> param) throws DataAccessException;
}
