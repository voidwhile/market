/** 
 * Project Name:mks 
 * File Name:WechatUserService.java 
 * Package Name:com.voidwhile.market.service 
 * Date:2018年9月2日下午2:44:00 
 * Copyright (c) 2018, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.voidwhile.market.service;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.WechatUser;

/** 
 * ClassName:WechatUserService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2018年9月2日 下午2:44:00 <br/> 
 * @author   zhangzheng 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
public interface WechatUserService extends IBaseService<WechatUser> {

	WechatUser findByMemberId(Long memberId);
}
  