package com.voidwhile.common.utils;

import com.voidwhile.common.util.SpringContextHelper;
import com.voidwhile.system.entity.SysConfig;
import com.voidwhile.system.service.SysConfigService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统运行参数工具类
 * @author: xiaowei
 * @Create Date: 2014年12月4日 下午3:08:05
 *
 * @Version: v1.0
 */
public class SysConfigHelper {
	public static SysConfigService configService;

	static {
		configService = SpringContextHelper.getBean(SysConfigService.class);
	}

	/**
	 * @MethodName: getSysConfig
	 * @Description: 获取系统运行参数
	 * @param code
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月11日 下午3:21:52
	 */
	public static SysConfig getSysConfig(String code) {
		return configService.getBySupplierIdAndConfigCode("0", code);
	}

	/**
	 * @MethodName: getCodeList
	 * @Description: 获取企业内部系统运行参数
	 * @param supplierId
	 * @param code
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月4日 下午3:39:11
	 */
	public static SysConfig getSysConfig(String supplierId, String code) {
		SysConfig entity = configService.getBySupplierIdAndConfigCode(supplierId, code);
		if (entity == null) {
			entity = configService.getBySupplierIdAndConfigCode("0", code);
		}

		return entity;
	}

	/**
	 * @MethodName: getConfigValue
	 * @Description: 获取系统运行参数的值，如果为空返回默认值
	 * @param code
	 * @param defaultValue
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月11日 下午3:22:32
	 */
	public static String getConfigValue(String code, String defaultValue) {
		String rltValue = null;
		SysConfig config = getSysConfig(code);
		if (config != null) {
			rltValue = config.getConfigValue();
		}

		if (rltValue == null) {
			rltValue = defaultValue;
		}
		return rltValue;
	}

	/**
	 * @MethodName: getConfigValue
	 * @Description: 获取企业内部系统运行参数的值，如果为空返回默认值
	 * @param supplierId
	 * @param code
	 * @param defaultValue
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月9日 下午2:01:52
	 */
	public static String getConfigValue(String supplierId, String code, String defaultValue) {
		String rltValue = null;
		SysConfig config = getSysConfig(supplierId, code);
		if(config!=null){
			rltValue = config.getConfigValue();
		}
		
		if (rltValue == null) {
			rltValue = defaultValue;
		}
		return rltValue;
	}

}
