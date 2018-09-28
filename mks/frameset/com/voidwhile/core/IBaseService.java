package com.voidwhile.core;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.core.jdbc.sql.UUIDHexGenerator;
import com.voidwhile.system.bean.PageResult;

public interface IBaseService<T> {
	/**
	 * ID生成器
	 * */
	public UUIDHexGenerator idGenarater = UUIDHexGenerator.getInstance();

	/**
	 * @MethodName: save
	 * @Description: 保存
	 * @param entity
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午10:09:41
	 */
	public void save(T entity) throws DataAccessException;

	/**
	 * @MethodName: update
	 * @Description: 更新
	 * @param entity
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午10:09:59
	 */
	public void update(T entity) throws DataAccessException;
	
	/**
	 * @MethodName: delete
	 * @Description: 删除
	 * @param id
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午10:12:09
	 */
	public void delete(String id) throws DataAccessException;
	
	/**
	 * @MethodName: getById
	 * @Description: 按ID获取对象
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午10:11:56
	 */
	public T getById(String id) throws DataAccessException;

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
	public PageResult<T> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException;

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
	public List<T> findByMap(Map<String, Object> param) throws DataAccessException;

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
