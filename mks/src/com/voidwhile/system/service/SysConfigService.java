package com.voidwhile.system.service;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.market.service.IBaseService;
import com.voidwhile.system.entity.SysConfig;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统运行参数管理 Service 接口
 * @author: xiaowei
 * @Create Date: 2014年12月4日 下午3:59:06
 *
 * @Version: v1.0
 */
public interface SysConfigService extends IBaseService<SysConfig> {

	/**
	 * @MethodName: getBySupplierIdAndConfigCode
	 * @Description: 根据企业ID和参数代码查询运行参数
	 * @param supplierId
	 * @param configCode
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月4日 下午3:27:16
	 */
	SysConfig getBySupplierIdAndConfigCode(String supplierId, String configCode) throws DataAccessException;

	/**
	 * @MethodName: deleteByIds
	 * @Description: 运行参数批量删除
	 * @param ids
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 下午8:06:42
	 */
	void deleteByIds(String[] ids) throws DataAccessException;
	
	SysConfig findByParams(Map<String, Object> param) throws DataAccessException;
}
