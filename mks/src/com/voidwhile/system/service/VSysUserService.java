package com.voidwhile.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.vo.VSysUser;

public interface VSysUserService {

	/**
	 * @MethodName: findPageData
	 * @Description: 分页数据查询
	 * @param param 查询条件
	 * @param pageNo 当前页码
	 * @param pageSize 每页大小
	 * @param orderByClause 排序方式
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午10:11:27
	 */
	public PageResult<VSysUser> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException;

	/**
	 * @MethodName: findByMap
	 * @Description: 按条件查询列表
	 * @param param 查询条件，其中 offset : 分页偏移 rowNum ： 每页显示条数 orderByClause :
	 *            排序（如：create_time desc)
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午10:10:33
	 */
	public List<VSysUser> findByMap(Map<String, Object> param) throws DataAccessException;

	/**
	 * @MethodName: countByMap
	 * @Description: 按条件统计记录数量
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午10:10:12
	 */
	public int countByMap(Map<String, Object> param) throws DataAccessException;

}
