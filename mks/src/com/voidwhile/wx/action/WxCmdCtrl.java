/** 
 * Project Name:mks 
 * File Name:CmdCommodityCtrl.java 
 * Package Name:com.voidwhile.market.action 
 * Date:2017年9月24日下午9:14:20 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/

package com.voidwhile.wx.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.market.entity.CmdCommodity;
import com.voidwhile.market.service.CmdCommodityService;
import com.voidwhile.market.service.CmdLabelService;
import com.voidwhile.market.service.CmdSpecificationService;
import com.voidwhile.market.service.PubImageService;
import com.voidwhile.market.service.RepInventoryService;
import com.voidwhile.system.service.SysParamService;

/**
 * ClassName:CmdCommodityCtrl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年9月24日 下午9:14:20 <br/>
 * 
 * @author zhangzheng
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
@RequestMapping("/wx/cmd")
public class WxCmdCtrl extends BaseController {

	Logger log = Logger.getLogger(WxCmdCtrl.class);

	@Autowired
	private CmdCommodityService service;
	@Autowired
	private CmdSpecificationService sftService;
	@Autowired
	private CmdLabelService labelService;
	@Autowired
	private SysParamService sysParamService;
	@Autowired
	private RepInventoryService inventoryService;
	@Autowired
	private PubImageService imgService;

	@RequestMapping("/list.wx")
	public String list(ModelMap map,String cmdType,CmdCommodity cmd,String memberId) throws Exception {
		Map<String, Object> param = new HashMap<>();
		param.put("cmdType", cmdType);
		param.put("cmdName", cmd.getCmdName());
		List<CmdCommodity> cmdList = service.findByMap(param);
		map.put("cmdList", cmdList);
		map.put("memberId", memberId);
		return "weixin/cmd/cmd_list";
	}

	
	
	@RequestMapping("/detail.wx")
	public String detail(String cmdId,String memberId,ModelMap map){
		CmdCommodity cmd = service.getById(cmdId);
		map.put("cmd", cmd);
		map.put("memberId", memberId);
		return "weixin/cmd/cmd_detail";
	}
	
	
}
