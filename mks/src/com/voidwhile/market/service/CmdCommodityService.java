/** 
 * Project Name:mks 
 * File Name:CmdCommodityService.java 
 * Package Name:com.voidwhile.market.service 
 * Date:2017年9月24日下午9:07:59 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.voidwhile.market.service;

import java.util.List;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.CmdCommodity;

/** 
 * ClassName:CmdCommodityService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年9月24日 下午9:07:59 <br/> 
 * @author   zhangzheng 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
public interface CmdCommodityService extends IBaseService<CmdCommodity> {
	void deleteByIds(String[] ids);
	List<CmdCommodity> getOption();
}
  