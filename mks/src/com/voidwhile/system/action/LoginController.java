package com.voidwhile.system.action;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.voidwhile.common.action.BaseController;
import com.voidwhile.common.utils.SysLogHelper;
import com.voidwhile.common.web.RequestUtils;
import com.voidwhile.common.web.springmvc.MessageResolver;
import com.voidwhile.system.bean.Admin;
import com.voidwhile.system.constant.SysConstant;
import com.voidwhile.system.entity.SysMenus;
import com.voidwhile.system.entity.SysRole;
import com.voidwhile.system.entity.SysSupplier;
import com.voidwhile.system.entity.SysUser;
import com.voidwhile.system.service.SysMenusService;
import com.voidwhile.system.service.SysRoleService;
import com.voidwhile.system.service.SysUserService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 管理登陆
 * @author: xiaowei
 * @Create Date: 2014年10月24日 下午1:18:44
 *
 * @Version: v1.0
 */
@Controller
@SessionAttributes(value = { SysConstant.SESSION_ADMIN })
public class LoginController extends BaseController {

	@Resource
	private SysUserService userService;

	@Resource
	private SysRoleService roleService;

	@Resource
	private SysMenusService menuService;

	@Autowired
	private ImageCaptchaService imageCaptchaService;

	/**
	 * @MethodName: input
	 * @Description: 跳转到登陆页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 下午1:19:13
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		return "system/login";
	}

	/**
	 * @MethodName: submit
	 * @Description: 登陆
	 * @param username
	 * @param password
	 * @param captcha
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 下午1:19:23
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String submit(String supplierCode, String username, String password, String captcha, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		supplierCode = SysConstant.SUPPLIER;
		username = StringUtils.trimToEmpty(username);

		if ("".equals(supplierCode)) {
			return loginFailure(request, supplierCode, username, "error.supplierIsEmpty", model);
		}

		if ("".equals(username)) {
			return loginFailure(request, supplierCode, username, "error.usernameIsEmpty", model);
		}

		if ("".equals(StringUtils.trimToEmpty(password))) {
			return loginFailure(request, supplierCode, username, "error.passwordIsEmpty", model);
		}
//		if ("".equals(StringUtils.trimToEmpty(captcha))) {
//			return loginFailure(request, supplierCode, username, "error.captchaIsEmpty", model);
//		}

		// 验证验证码
//		if (!validateCaptcha(request, captcha)) {
//			return loginFailure(request, supplierCode, username, "error.invalidCaptcha", model);
//		}
		try {
			
			SysSupplier supplier = findSupplier(supplierCode);
			if (supplier == null) {
				logger.error("登陆出错：({}){} - {}", username, MessageResolver.getMessage(request, "error.supplierNotExist", supplierCode),
						RequestUtils.getIpAddr(request));
				SysLogHelper.error("0", username, "系统登陆", MessageResolver.getMessage(request, "error.supplierNotExist", supplierCode),
						RequestUtils.getIpAddr(request));
				return loginFailure(request, supplierCode, username, "error.supplierNotExist", model);
			} else {
				SysUser user = userService.getBySupplierAndUserName(supplier.getUid(), username);
				// 认证失败
				if (user == null || !authenticate(password, user)) {
					logger.error("登陆失败：({}){} - {}", username, MessageResolver.getMessage(request, "error.badCredentials"), RequestUtils.getIpAddr(request));
					SysLogHelper.error(supplier.getUid(), username, "系统登陆", MessageResolver.getMessage(request, "error.badCredentials"),
							RequestUtils.getIpAddr(request));
					return loginFailure(request, supplierCode, username, "error.badCredentials", model);
				} else {
					// 认证成功
					// 查询用户角色信息
					List<SysRole> roleList = roleService.getByUserId(user.getUid()+"");
					// 用户的权限信息
					Set<SysMenus> menuList = null;
					String dataRang = SysConstant.ROLE_DATARANG_OWNER;
					if (SysConstant.YES.equals(user.getIsSystem())) {
						menuList = menuService.findMenusByPIdAndSupplierLevel(0, supplier.getSupplierLevel());
						dataRang = SysConstant.ROLE_DATARANG_FULL;
					} else {
						menuList = menuService.findMenusByPIdAndUserId(0, user.getUid()+"");
						dataRang = getDataRang(roleList);
					}
					
					Admin admin = new Admin();
					admin.setUser(user);
					admin.setSupplier(supplier);
					admin.setRoleList(roleList);
					admin.setMenuList(menuList);
					admin.setDataRange(dataRang);
					
					logger.info("登陆成功：{} - {}", username, RequestUtils.getIpAddr(request));
					SysLogHelper.info(supplier.getUid(), username, "系统登陆", "登陆成功", RequestUtils.getIpAddr(request));
					model.addAttribute(SysConstant.SESSION_ADMIN, admin);
					return "redirect:/admin/index.do";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("ex",e.getMessage());
			return "error";
		}
	}

	/**
	 * @MethodName: logout
	 * @Description: 登出
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 下午1:23:58
	 */
	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		for (String key : model.keySet()) {
			model.remove(key);
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/login.do";
	}

	/**
	 * @MethodName: validateCaptcha
	 * @Description: 验证验证码是否正确
	 * @param sessionId
	 * @param captcha
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 下午1:32:11
	 */
	protected boolean validateCaptcha(HttpServletRequest request, String captcha) {
		boolean rlt = false;
		try {
			rlt = imageCaptchaService.validateResponseForID(request.getSession().getId(), captcha);
		} catch (Exception e) {

		}
		return rlt;
	}


	/**
	 * @MethodName: loginFailure
	 * @Description: 登陆出错处理方法
	 * @param request
	 * @param username
	 * @param errorCode
	 * @param model
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 下午1:32:26
	 */
	protected String loginFailure(HttpServletRequest request, String supplierCode, String username, String errorCode, ModelMap model) {
		model.addAttribute("supplierCode", supplierCode);
		model.addAttribute("username", username);
		model.addAttribute("error", MessageResolver.getMessage(request, errorCode, supplierCode));
		return "system/login";
	}

	/**
	 * @MethodName: getDataRang
	 * @Description: 获取用户数据范围
	 * @param roleList
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月12日 下午5:04:24
	 */
	private String getDataRang(List<SysRole> roleList) {
		String rlt = "";
		if (roleList != null && roleList.size() > 0) {
			for (SysRole role : roleList) {
				String datarange = StringUtils.trimToEmpty(role.getDataRange());
				if (StringUtils.isEmpty(rlt) && SysConstant.ROLE_DATARANG_OWNER.equals(datarange)) {
					rlt = datarange;
					continue;
				}
				if (SysConstant.ROLE_DATARANG_DEPT.equals(datarange)) {
					rlt = datarange;
					continue;
				}
				if (SysConstant.ROLE_DATARANG_FULL.equals(datarange)) {
					rlt = datarange;
					break;
				}
			}
		}
		return rlt;
	}

}
