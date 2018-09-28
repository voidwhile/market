package com.voidwhile.common.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.voidwhile.common.web.AbstractConfigurationFilter;
import com.voidwhile.system.bean.Admin;
import com.voidwhile.system.constant.SysConstant;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 管理端过滤器
 * @author: zhanzheng
 * @Create Date: 2014年10月24日 下午1:48:19
 *
 * @Version: v1.0
 */
public class SystemManageFilter extends AbstractConfigurationFilter {
	
	private List<String> noCheckPaths = new ArrayList<String>();
	private final static String INIT_PARAM_NOCHECK="noCheck";
	
	private String loginURL = "/login.do";

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String contextPath = request.getContextPath();
		//检查是否为不需要登录的地址
		logger.info(request.getRequestURI());
		if(check(request.getRequestURI(), contextPath)){
			chain.doFilter(request, response);
		} else {
			Admin admin = (Admin) request.getSession().getAttribute(SysConstant.SESSION_ADMIN);
			
			if (admin == null) {
				String basePath = request.getScheme() + "://"
						+ request.getServerName() + ":" + request.getServerPort()
						+ contextPath;
				response.sendRedirect(basePath + loginURL);
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// 不过滤路径
		String noCheckPath = getPropertyFromInitParams(filterConfig, INIT_PARAM_NOCHECK, "");
		if ("".equals(noCheckPath))
			return;
		// 根据；, 制表符 回车符 换行符 对值进行分段
		String[] stmp = noCheckPath.split(";|,|\n|\r|\t");
		for (String s : stmp){
			if (s != null) {
				s = s.trim();
				if (s.length() > 0)
					noCheckPaths.add(s);
			}
		}
	}
	
	private boolean check(String path, String contextPath) {
		if("/".equals(contextPath)){
			contextPath="";
		}
		if (noCheckPaths == null || noCheckPaths.size() <= 0){
			return false;
		}
		for (String s : noCheckPaths) {
			s = StringUtils.trimToEmpty(s);
			if (s.indexOf("/") == 0) {
				s = contextPath + s;
			} else {
				s = contextPath + "/" + s;
			}
			if (s.indexOf(path) == 0) {
				return true;
			}
		}
		return false;
	}


}
