/** 
 * Project Name:mks 
 * File Name:CmdLabelService.java 
 * Package Name:com.voidwhile.market.service 
 * Date:2017年9月24日下午3:36:11 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.voidwhile.market.service;

import java.util.List;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.CmdLabel;

/** 
 * ClassName:CmdLabelService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年9月24日 下午3:36:11 <br/> 
 * @author   zhangzheng 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
public interface CmdLabelService extends IBaseService<CmdLabel>{
	
	int deleteByCmdType(Integer cmdType);
	int deleteByCmdId(Long cmdId);
	List<CmdLabel> findByCmdId(Long cmdId);
}
  