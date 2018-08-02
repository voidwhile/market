/** 
 * Project Name:mks 
 * File Name:CmdBrandService.java 
 * Package Name:com.voidwhile.market.service 
 * Date:2017年9月3日下午7:30:02 
 * Copyright (c) 2017, voidwhile@aliyun.com All Rights Reserved. 
 * 
*/

package com.voidwhile.market.service;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.CmdBrand;

/**
 * ClassName:CmdBrandService <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年9月3日 下午7:30:02 <br/>
 * 
 * @author zhangzheng
 * @version
 * @since JDK 1.8
 * @see
 */
public interface CmdBrandService extends IBaseService<CmdBrand> {
	
	public void deleteByIds(String[] ids);
}
