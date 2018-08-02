/** 
 * Project Name:mks 
 * File Name:CfgSpecificationService.java 
 * Package Name:com.voidwhile.market.service 
 * Date:2017年9月21日下午10:18:57 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.voidwhile.market.service;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.CfgSpecification;

/** 
 * ClassName:CfgSpecificationService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年9月21日 下午10:18:57 <br/> 
 * @author   zhangzheng 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
public interface CfgSpecificationService extends IBaseService<CfgSpecification> {
	public void deleteByIds(String[] ids);
}
  