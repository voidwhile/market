package com.voidwhile.market.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.market.entity.RepInventory;
import com.voidwhile.market.service.RepInventoryService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;

@Controller
@RequestMapping("/rep/inventory")
public class RepInventoryCtrl extends BaseController {
	
	@Autowired
	private RepInventoryService service;
	
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, ModelMap map) {
		commonData(request, map);
		return "market/inventory/list";
	}
	
	@RequestMapping("/list_data.do")
	@ResponseBody
	public Map<String, Object> list_data(PageBean page, HttpServletRequest request, String cmdName) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> map = new HashMap<String, Object>();
		param.put("cmdName", cmdName);
		PageResult<RepInventory> result = service.findPageData(param, page.getPage(), page.getRows(), null);
		map.put("rows", result.getList());
		map.put("total", result.getTotal());
		return map;
	}
	
	
	@RequestMapping("/editStock.do")
	@ResponseBody
	public Map<String, Object> editStock(String inventoryId,Double stock){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			RepInventory inv = service.getById(inventoryId);
			inv.setStock(stock);
			service.update(inv);
			map.put("status", true);
			map.put("message", "库存修改成功");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "库存修改失败:"+e.getMessage());
		}
		
		return map;
	}
	
	
}
