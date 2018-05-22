package com.voidwhile.system.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.voidwhile.market.service.IBaseService;
import com.voidwhile.system.entity.SysParam;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 编码管理 Service接口
 * @author: xiaowei
 * @Create Date: 2014年12月3日 上午10:44:04
 *
 * @Version: v1.0
 */
public interface SysParamService extends IBaseService<SysParam> {

	/**
	 * @MethodName: getBySupplierAndParamCode
	 * @Description: 根据企业ID和字典代码获取
	 * @param supplierId
	 * @param paramCode
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月4日 下午3:27:16
	 */
	SysParam getBySupplierIdAndParamCode(String supplierId, String paramCode) throws DataAccessException;

	void deleteByIds(String[] ids) throws DataAccessException;
	
	/**
	 * 获取选择项
	 * @param tableName 表名
	 * @param code 选择项代码
	 * @param des 选择项展示
	 * @param whereClause 选择条件
	 * @param selected 选中值
	 * @return
	 */
	String options(String tableName,String code,String des,String whereClause,String selected);
}
