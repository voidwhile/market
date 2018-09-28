package com.voidwhile.common.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.voidwhile.core.utils.MD5;
import com.voidwhile.core.utils.Slf4JLogger;
import com.voidwhile.system.action.LoginController;
import com.voidwhile.system.entity.SysSupplier;
import com.voidwhile.system.entity.SysUser;
import com.voidwhile.system.service.SysSupplierService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: Controller 基础类
 * @author: zhanzheng
 * @Create Date: 2014年11月19日 上午10:17:16
 *
 * @Version: v1.0
 */
public abstract class BaseController {

	protected final static Slf4JLogger logger = Slf4JLogger.getLogger(LoginController.class);
	/**
	 * 部署路径
	 */
	public static final String BASE = "base";

	@Resource
	protected SysSupplierService sysSupplierService;

	/**
	 * @MethodName: commonData
	 * @Description: 为页面设置公共数据
	 * @param request
	 * @param map
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年11月28日 上午10:44:46
	 */
	public static void commonData(HttpServletRequest request, Map<String, Object> map) {
		String ctx = request.getContextPath() == null ? "" : request.getContextPath();
		map.put(BASE, ctx);
	}

	/**
	 * @MethodName: findSupplier
	 * @Description: 根据企业代码获取企业信息
	 * @param supplierCode 为0时为系统管理登陆
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年11月19日 上午11:38:10
	 */
	protected SysSupplier findSupplier(String supplierCode) {
		SysSupplier supplier = null;
		// 系统管理登陆
		if ("0".equals(supplierCode)) {
			supplier = new SysSupplier();
			supplier.setUid("0");
			supplier.setSupplierCode("0");
			supplier.setSupplierLevel("");
			supplier.setSupplierName("向悦万家运营商");
		} else {
			supplier = sysSupplierService.getBySupplierCode(supplierCode, true);
		}
		return supplier;
	}

	/**
	 * @MethodName: authenticate
	 * @Description: 用户认证
	 * @param password
	 * @param user
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年10月24日 下午1:31:55
	 */
	protected boolean authenticate(String password, SysUser user) {
		String plainText = user.getPlainPassword();
		String encrypted = StringUtils.trimToEmpty(user.getPassword());
		if (!"".equals(encrypted)) {
			return MD5.getMD5(password).equals(encrypted);
		} else {
			return password.equals(plainText);
		}
	}

	/**
	 * 统一异常处理
	 */
	@ExceptionHandler
	public String exception(HttpServletRequest request, Exception ex) {
		request.setAttribute("ex", ex);
		return "error";
	}
}
