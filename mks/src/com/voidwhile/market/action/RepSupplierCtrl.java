package com.voidwhile.market.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.market.entity.RepSupplier;
import com.voidwhile.market.service.RepSupplierService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;

@Controller
@RequestMapping("/rep/supp")
public class RepSupplierCtrl extends BaseController{
	
	
	Logger log = Logger.getLogger(CmdCommodityCtrl.class);
	
	@Autowired
	private RepSupplierService service;
	
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		commonData(request, map);
		return "market/repSupp/list";
	}
	@RequestMapping("/list_data.do")
	@ResponseBody
	public Map<String, Object> list_data(PageBean page, HttpServletRequest request, RepSupplier entity) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> map = new HashMap<String, Object>();
		String orderByClause = "create_time desc";
		param.put("supplierName", entity.getSupplierName());
		param.put("linkMan", entity.getLinkMan());
		param.put("linkMp", entity.getLinkMp());
		PageResult<RepSupplier> result = service.findPageData(param, page.getPage(), page.getRows(), orderByClause);
		map.put("rows", result.getList());
		map.put("total", result.getTotal());
		return map;
	}
	
	@RequestMapping("/add.do")
	public String add(HttpServletRequest request, ModelMap model){
		commonData(request, model);
		return "market/repSupp/supp_add";
	}
	
	@RequestMapping("/edit.do")
	public String edit(HttpServletRequest request, ModelMap model,String uid){
		commonData(request, model);
		RepSupplier supplier = service.getById(uid);
		model.put("supplier", supplier);
		return "market/repSupp/supp_edit";
	}
	@RequestMapping("/save.do")
	@ResponseBody
	public Map<String,Object> save(RepSupplier entity,HttpServletRequest request, ModelMap model,String startTime){
		Date serverStart = com.voidwhile.core.utils.DateUtils.parseDate(startTime);
		entity.setServerStart(serverStart);
		Map<String, Object> map = new HashMap<>();
		try {
			if (entity.getSupplierId()!=null) {
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
