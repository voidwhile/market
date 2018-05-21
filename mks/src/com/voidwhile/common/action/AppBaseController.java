package com.voidwhile.common.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.voidwhile.common.bean.baidu.BaiduMapRequest;
import com.voidwhile.common.dto.PositionConfigDTO;
import com.voidwhile.common.mapper.JsonMapper;
import com.voidwhile.common.utils.SysConfigHelper;
import com.voidwhile.core.utils.MD5;
import com.voidwhile.core.utils.Slf4JLogger;
import com.voidwhile.market.constant.MarketConstant;
import com.voidwhile.market.entity.AppSession;
import com.voidwhile.market.service.AppSessionService;
import com.voidwhile.system.entity.SysSupplier;
import com.voidwhile.system.entity.SysUser;
import com.voidwhile.system.service.SysSupplierService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: Controller 基础类
 * @author: xiaowei
 * @Create Date: 2014年11月19日 上午10:17:16
 *
 * @Version: v1.0
 */
public abstract class AppBaseController {
	protected final static Slf4JLogger logger = Slf4JLogger.getLogger(AppBaseController.class);

	protected final static int PAGE_SIZE = 20;

	@Autowired
	protected SysSupplierService sysSupplierService;

	@Autowired
	protected AppSessionService sessionService;

	/**
	 * @MethodName: findSupplier
	 * @Description: 根据企业代码获取企业信息
	 * @param supplierCode
	 *            企业代码
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月19日 上午11:38:10
	 */
	protected SysSupplier findSupplier(String supplierCode) {
		SysSupplier supplier = null;
		supplier = sysSupplierService.getBySupplierCode(supplierCode, true);
		return supplier;
	}

	/**
	 * @MethodName: authenticate
	 * @Description: 用户认证
	 * @param password
	 * @param user
	 * @return
	 *
	 * @Author: xiaowei
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
	 * @MethodName: validateSession
	 * @Description: App会话验证
	 * @param sessionId
	 * @return 会话ID是否验证通过
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月20日 下午2:12:43
	 */
	protected AppSession validateSession(String sessionId) {
		return sessionService.validateSession(sessionId);
	}

	/**
	 * @MethodName: getAddressByLonLan
	 * @Description: 根据经纬度获取实际地址
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月25日 下午2:54:53
	 */
	protected String getAddressByLanLon(String lat, String lng) {
		BaiduMapRequest baiduRequest = new BaiduMapRequest();
		return baiduRequest.getAddress(lat, lng);
	}

	/**
	 * @MethodName: getPositionConfig
	 * @Description: 获取企业的定位配置信息
	 * @param supplierId
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月15日 下午1:30:30
	 */
	protected PositionConfigDTO getPositionConfig(String supplierId) {
		return null;
	}

	public Integer getPageSize(Integer pageSize) {
		if (pageSize == null || pageSize <= 0) {
			pageSize = PAGE_SIZE;
		}
		return pageSize;
	}

	public int getPageNo(Integer pageNo) {
		if (pageNo == null || pageNo <= 0) {
			pageNo = 1;
		}
		return pageNo;
	}

	/**
	 * 统一异常处理
	 */
	@ExceptionHandler
	public String exception(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		JsonMapper jsonMapper = new JsonMapper();

		Map<String, Object> rlt = new HashMap<String, Object>();
		rlt.put("retcode", "2");
		rlt.put("message", "系统异常，" + ex.getMessage());
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(jsonMapper.toJson(rlt));
		} catch (IOException e) {
			logger.error("系统异常" + e.getMessage());
		}
		return null;
	}
}
