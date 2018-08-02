/** 
 * Project Name:mks 
 * File Name:CmdTypeCtrl.java 
 * Package Name:com.voidwhile.market.action 
 * Date:2017年9月12日下午10:52:26 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/

package com.voidwhile.market.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.common.bean.tree.TreeNode;
import com.voidwhile.common.utils.Tools;
import com.voidwhile.market.entity.CmdType;
import com.voidwhile.market.service.CmdTypeService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.constant.SysConstant;

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
@RequestMapping("/cmd/type")
public class CmdTypeCtrl extends BaseController {

	@Autowired
	private CmdTypeService cmdTypeService;

	/**
	 * 
	 * manager:进入商品类型管理页面. <br/>
	 * 
	 * @author zhangzheng
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping("/manager.do")
	public String manager(HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		return "market/cmdType/manager";
	}

	/**
	 * 
	 * list:查询子分类. <br/> 
	 * 
	 * @author zhangzheng 
	 * @param page
	 * @param request
	 * @param parentId
	 * @return 
	 * @since JDK 1.8
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public Map<String, Object> list(PageBean page, String parentId,Model model) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> map = new HashMap<String, Object>();
		String orderByClause = "create_time desc";
		if (parentId==null) {
			parentId = "0";
		}
		param.put("parentId", parentId);
		PageResult<CmdType> result = cmdTypeService.findPageData(param, page.getPage(), page.getRows(), orderByClause);
		map.put("rows", result.getList());
		map.put("total", result.getTotal());
		model.addAttribute("imgUrl", SysConstant.IMG_URL);
		return map;
	}

	/**
	 * 
	 * tree:获取商品类型树型结构. <br/>
	 * 
	 * @author zhangzheng
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping("/tree.do")
	@ResponseBody
	public List<TreeNode> tree() {
		Map<String, Object> map = new HashMap<>();
		List<TreeNode> list = cmdTypeService.getTree(0);
		map.put("data", list);
		return list;
	}
	
	@RequestMapping("/add.do")
	public String add(HttpServletRequest request, ModelMap model, String parentId){
		commonData(request, model);
		if (!"".equals(parentId)) {
			CmdType type = cmdTypeService.getById(parentId);
			model.put("parentName", type.getTypeName());
			model.put("parentId", parentId);
		}
		return "market/cmdType/type_add";
	}
	@RequestMapping("/edit.do")
	public String edit(HttpServletRequest request, ModelMap model, String id){
		commonData(request, model);
		CmdType type = cmdTypeService.getById(id);
		CmdType parent = cmdTypeService.getById(Tools.toString(type.getParentId()));
		model.put("imgUrl", SysConstant.IMG_URL);
		model.put("type", type);
		model.put("parent", parent);
		return "market/cmdType/type_edit";
	}
	
	@RequestMapping(value = "/save.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	@Transactional
	public Map<String, Object> save(CmdType entity){
		Map<String, Object> map = new HashMap<>();
		try {
			if (entity.getCmdType()!=null) {
				cmdTypeService.update(entity);
			} else {
				cmdTypeService.save(entity);
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
			cmdTypeService.deleteByIds(ids);
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
