package com.voidwhile.system.service;

import org.springframework.dao.DataAccessException;

import com.voidwhile.core.IBaseService;
import com.voidwhile.system.entity.SysSupplier;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 企业管理Service
 * @author: xiaowei
 * @Create Date: 2014年11月18日 下午2:31:13
 *
 * @Version: v1.0
 */
public interface SysSupplierService extends IBaseService<SysSupplier> {

	/**
	 * @MethodName: getByCode
	 * @Description: 根据企业代码查询企业信息
	 * @param supplierCode
	 * @param isOfficial 为true时只查询正式客户
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月19日 上午11:00:15
	 */
	public SysSupplier getBySupplierCode(String supplierCode, boolean isOfficial) throws DataAccessException;
	/**
	 * 
	 * @MethodName: deleteByIds 
	 * @Description: 删除企业 
	 * @param ids
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-22 下午2:41:23
	 */
	public void deleteByIds(String[] ids);
}
