package com.voidwhile.system.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.common.web.MarketUtils;
import com.voidwhile.system.bean.Admin;

@Controller
@RequestMapping("/admin")
public class MainController extends BaseController{
	

	private Map<String, Object> param;

	/** 
	 * @MethodName: index 
	 * @Description: 框架首页 
	 * @param model
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月28日 下午4:04:35
	 */
	@RequestMapping(value = "/index.do")
	public String index(ModelMap model, HttpServletRequest request) {
		commonData(request, model);
		return "system/index";
	}

	/**
	 * @MethodName: main
	 * @Description: 默认页
	 * @param model
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月28日 下午4:05:19
	 */
	@RequestMapping(value = "/main.do")
	public String main(ModelMap model, HttpServletRequest request) {
		commonData(request, model);
		Admin admin=MarketUtils.getAdmin(request);
		param=new HashMap<String, Object>();
		Long notifyUser=admin.getUser().getUid();
        if(notifyUser!=null){
        	param.put("notifyUser", notifyUser);
        }   
      
		
		
		return "system/main";
	}
}
