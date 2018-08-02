/** 
 * Project Name:mks 
 * File Name:CmdBrandCtrl.java 
 * Package Name:com.voidwhile.market.action 
 * Date:2017年9月19日下午9:21:22 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.voidwhile.market.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.market.entity.CfgSpecification;
import com.voidwhile.market.service.CfgSpecificationService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;

/** 
 * ClassName:CmdBrandCtrl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年9月19日 下午9:21:22 <br/> 
 * @author   zhangzheng 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
@Controller
@RequestMapping("/cfg/spec")
public class CfgSpecificationCtrl extends BaseController{

	Logger logger = Logger.getLogger(CfgSpecificationCtrl.class);
	
	@Autowired
	private CfgSpecificationService service;
	
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, ModelMap map) {
		commonData(request, map);
		return "market/cfgSpec/list";
	}
	
	@RequestMapping("/list_data.do")
	@ResponseBody
	public Map<String, Object> list_data(PageBean page, HttpServletRequest request, String sftName) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> map = new HashMap<String, Object>();
		String orderByClause = "cfg_sft_id desc";
		param.put("sftName", sftName);
		PageResult<CfgSpecification> result = service.findPageData(param, page.getPage(), page.getRows(), orderByClause);
		map.put("rows", result.getList());
		map.put("total", result.getTotal());
		return map;
	}
	
	@RequestMapping("/add.do")
	public String add(HttpServletRequest request, ModelMap model){
		commonData(request, model);
		return "market/cfgSpec/cfgSpec_add";
	}
	
	@RequestMapping("/edit.do")
	public String edit(HttpServletRequest request, ModelMap model,String uid){
		commonData(request, model);
		CfgSpecification spec = service.getById(uid);
		model.put("spec", spec);
		return "market/cfgSpec/cfgSpec_edit";
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Map<String,Object> save(CfgSpecification entity){
		Map<String, Object> map = new HashMap<>();
		try {
			if (entity.getCfgSftId()!=null) {
				service.update(entity);
			} else {
				service.save(entity);
			}
			map.put("status", true);
			map.put("message", "保存成功!");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", false);
			map.put("message", "出错啦!" + e.getMessage());
			return map;
		}
	}
	
	/**
	 * 
	 * delete:删除. <br/> 
	 * 
	 * @author zhangzheng 
	 * @param id
	 * @return 
	 * @since JDK 1.8
	 */
	@RequestMapping("/del.do")
	@ResponseBody
	public Map<String, Object> del(String[] ids) throws IOException {
		Map<String, Object> rltMap = new HashMap<String, Object>();
		if (ids == null || ids.length == 0) {
			rltMap.put("success", "0");
			rltMap.put("msg", "请选择要处理的数据！");
			return rltMap;
		}
		try {
			service.deleteByIds(ids);
			rltMap.put("success", "1");
			rltMap.put("msg", "删除成功!");
			return rltMap;
		} catch (Exception e) {
			rltMap.put("success", "0");
			rltMap.put("msg", "出错了！" + e.getMessage());
			return rltMap;
		}
	}
}
  