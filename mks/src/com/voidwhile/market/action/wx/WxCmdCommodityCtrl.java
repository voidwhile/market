/** 
 * Project Name:mks 
 * File Name:CmdCommodityCtrl.java 
 * Package Name:com.voidwhile.market.action 
 * Date:2017年9月24日下午9:14:20 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/

package com.voidwhile.market.action.wx;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Tool;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.common.utils.Tools;
import com.voidwhile.core.utils.DateUtils;
import com.voidwhile.market.constant.MarketConstant;
import com.voidwhile.market.entity.CmdCommodity;
import com.voidwhile.market.entity.CmdLabel;
import com.voidwhile.market.entity.CmdSpecification;
import com.voidwhile.market.entity.PubImage;
import com.voidwhile.market.entity.RepInventory;
import com.voidwhile.market.service.CmdCommodityService;
import com.voidwhile.market.service.CmdLabelService;
import com.voidwhile.market.service.CmdSpecificationService;
import com.voidwhile.market.service.PubImageService;
import com.voidwhile.market.service.RepInventoryService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;
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
public class WxCmdCommodityCtrl extends BaseController {

	Logger log = Logger.getLogger(WxCmdCommodityCtrl.class);

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

	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		commonData(request, map);
		return "market/cmd/list";
	}

	@RequestMapping("/list_data.do")
	@ResponseBody
	public Map<String, Object> list_data(PageBean page, HttpServletRequest request, CmdCommodity entity) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> map = new HashMap<String, Object>();
		String orderByClause = "create_time desc";
		param.put("cmdName", entity.getCmdName());
		param.put("cmdCode", entity.getCmdCode());
		param.put("cmdStatus", entity.getCmdStatus());
		param.put("putawayTime", entity.getPutawayTime());
		param.put("supplierId", entity.getSupplierId());
		if (entity.getBrand()!=null) {
			param.put("brandName", entity.getBrand().getBrandName());
		}
		PageResult<CmdCommodity> result = service.findPageData(param, page.getPage(), page.getRows(), orderByClause);
		map.put("rows", result.getList());
		map.put("total", result.getTotal());
		return map;
	}
	
	@RequestMapping("/detail.do")
	public String detail(String cmdId,ModelMap map){
		CmdCommodity cmd = service.getById(cmdId);
		map.put("cmd", cmd);
		return "weixin/cmd/cmd_detail";
	}
	
	
}
