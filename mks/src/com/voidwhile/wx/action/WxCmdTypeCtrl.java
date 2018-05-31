/** 
 * Project Name:mks 
 * File Name:CmdTypeCtrl.java 
 * Package Name:com.voidwhile.market.action 
 * Date:2017年9月12日下午10:52:26 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/

package com.voidwhile.wx.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.market.entity.CmdType;
import com.voidwhile.market.service.CmdTypeService;

/**
 * ClassName:CmdTypeCtrl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年9月12日 下午10:52:26 <br/>
 * 
 * @author zhangzheng
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
@RequestMapping("/wx/type")
public class WxCmdTypeCtrl extends BaseController {

	@Autowired
	private CmdTypeService cmdTypeService;


	/**
	 * 分类
	 * @param typetId
	 * @param map
	 * @return
	 */
	@RequestMapping("/list.wx")
	public String list(ModelMap map) {
		Map<String, Object> param = new HashMap<>();
		param.put("parentId", 0);
		List<CmdType> typeList = cmdTypeService.findByMap(param);
		map.put("typeList", typeList);
		return "weixin/type";
	}

	
	
	
	
}
