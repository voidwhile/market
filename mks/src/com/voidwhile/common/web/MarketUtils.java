package com.voidwhile.common.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.voidwhile.system.bean.Admin;
import com.voidwhile.system.constant.SysConstant;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 提供一些系统中使用到的共用方法，比如获得用户信息
 * @author: xiaowei
 * @Create Date: 2014年11月29日 下午2:08:15
 *
 * @Version: v1.0
 */
public class MarketUtils {

	/**
	 * 获得用户
	 * 
	 * @param request
	 * @return
	 */
	/**
	 * @MethodName: getAdmin
	 * @Description: 功能描述
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月29日 下午2:08:05
	 */
	public static Admin getAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (Admin) session.getAttribute(SysConstant.SESSION_ADMIN);
	}
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
