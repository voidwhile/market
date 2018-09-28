package com.voidwhile.system.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.system.entity.SysOrganization;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 企业组织机构管理Mapper
 * @author: zhanzheng
 * @Create Date: 2014年12月5日 下午1:27:32
 *
 * @Version: v1.0
 */
public interface SysOrganizationMapper {
	/**
	 * @MethodName: insert
	 * @Description: 组织机构添加
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:31:37
	 */
	int insert(SysOrganization entity) throws DataAccessException;

	/**
	 * @MethodName: updateById
	 * @Description: 组织机构更新
	 * @param entity
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:32:54
	 */
	int updateById(SysOrganization entity) throws DataAccessException;

	/**
	 * @MethodName: deleteById
	 * @Description: 按ID删除
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:33:14
	 */
	boolean deleteById(String id) throws DataAccessException;

	/**
	 * @MethodName: getById
	 * @Description: 按ID查询
	 * @param id
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:33:23
	 */
	SysOrganization getById(String id) throws DataAccessException;

	/**
	 * @MethodName: findByMap
	 * @Description: 按条件查询列表
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:33:31
	 */
	List<SysOrganization> findByMap(Map<String, Object> param) throws DataAccessException;

	/**
	 * @MethodName: countByMap
	 * @Description: 按条件统计记录数量
	 * @param param
	 * @return
	 * @throws DataAccessException
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 上午11:33:47
	 */
	int countByMap(Map<String, Object> param) throws DataAccessException;
}