package com.voidwhile.system.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.voidwhile.core.IBaseService;
import com.voidwhile.system.entity.SysParamItem;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 编码管理 Service接口
 * @author: zhanzheng
 * @Create Date: 2014年12月3日 上午10:44:04
 *
 * @Version: v1.0
 */
public interface SysParamItemService extends IBaseService<SysParamItem> {

	/**
	 * @MethodName: findItemsByParamCode
	 * @Description: 根据编码ID获取字典值，如果 isValid为真只查询有效的字典值
	 * @param paramId
	 * @param isValid
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月3日 上午11:25:29
	 */
	public List<SysParamItem> findByParamId(String paramId, boolean isValid) throws DataAccessException;

	/**
	 * @MethodName: batchInsert
	 * @Description: 批量添加
	 * @param entities
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月4日 上午10:09:55
	 */
	int batchInsert(String paramId, List<SysParamItem> entities) throws DataAccessException;

	/**
	 * @MethodName: batchDelete
	 * @Description: 批量删除
	 * @param entities
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月4日 上午10:10:08
	 */
	int batchDelete(List<SysParamItem> entities) throws DataAccessException;

	/**
	 * @MethodName: batchUpdate
	 * @Description: 批量修改
	 * @param entities
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月4日 上午10:10:11
	 */
	int batchUpdate(List<SysParamItem> entities) throws DataAccessException;

}
