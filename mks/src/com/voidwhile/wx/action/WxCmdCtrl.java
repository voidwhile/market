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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.market.entity.CmdCommodity;
import com.voidwhile.market.service.CmdCommodityService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;

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
	

	@RequestMapping("/list.wx")
	public String list(ModelMap map, CmdCommodity cmd,String memberId,PageBean page) {
		Map<String, Object> param = new HashMap<>();
		try {
			if (cmd.getCcCmdType()!=null) {
				param.put("label", cmd.getCcCmdType());
			}
			if (StringUtils.isNotEmpty(cmd.getCmdName())) {
				param.put("cmdName", cmd.getCmdName());
			}
			PageResult<CmdCommodity> result = service.findPageData(param, page.getPage(), page.getApprows(), "create_time desc");
			map.put("cmdList", result.getList());
			map.put("memberId", memberId);
			map.put("label", cmd.getCcCmdType());
			map.put("cmdName", cmd.getCmdName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "weixin/cmd/cmd_list";
	}

	
	
	@RequestMapping("/detail.wx")
	public String detail(String cmdId,String memberId,ModelMap map){
		CmdCommodity cmd = service.getById(cmdId);
		map.put("cmd", cmd);
		map.put("memberId", memberId);
		return "weixin/cmd/cmd_detail";
	}
	
	@RequestMapping("/toSearch.wx")
	public String toSearch(String memberId,ModelMap map) {
		map.put("memberId", memberId);
		return "weixin/cmd/search";
	}
	
	@ResponseBody
	@RequestMapping("/pulluploading.wx")
	public Map<String, Object> pulluploading(CmdCommodity cmd,PageBean page){
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		try {
			if (cmd.getCcCmdType()!=null) {
				param.put("label", cmd.getCcCmdType());
			}
			if (StringUtils.isNotEmpty(cmd.getCmdName())) {
				param.put("cmdName", cmd.getCmdName());
			}
			PageResult<CmdCommodity> result = service.findPageData(param, page.getPage(), page.getApprows(), "create_time desc");
			map.put("cmdList", result.getList());
			map.put("rltCode", "0000");
		} catch (Exception e) {
			map.put("rltCode", "1111");
		}
		return map;
	}
	
	
}
