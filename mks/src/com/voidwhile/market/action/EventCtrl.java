package com.voidwhile.market.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.market.entity.EveEvent;
import com.voidwhile.market.service.EveEventService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;

@Controller
@RequestMapping("/evt/event")
public class EventCtrl {
	
	@Autowired
	private EveEventService service;
	
	@RequestMapping("/list.do")
	public String list(){
		return "market/event/list";
	}
	
	@ResponseBody
	@RequestMapping("/list_data.do")
	public Map<String, Object> list_data(PageBean page){
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		try {
			PageResult<EveEvent> result = service.findPageData(param, page.getPage(), page.getRows(), "fcd desc");
			map.put("rows", result.getList());
			map.put("total", result.getTotal());
			return map;
		} catch (Exception e) {
			
		}
		return map;
	}
	
	@RequestMapping("/add.do")
	public String add(){
		return "market/event/add";
	}

	@RequestMapping("/edit.do")
	public String edit(){
		return "market/event/edit";
	}
	
	@RequestMapping("/del.do")
	@ResponseBody
	public Map<String, Object> del(String[] ids) throws IOException {
		Map<String, Object> map = new HashMap<>();
		try {
			service.deleteByIds(ids);
			map.put("status", true);
			map.put("message", "删除成功！");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "删除失败！");
		}
		return map;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	@Transactional
	public Map<String,Object> save(EveEvent entity,String[] cmdId){
		Map<String, Object> map = new HashMap<>();
		try {
			service.save(entity);
			map.put("status", true);
			map.put("message", "保存成功！");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "保存失败！");
		}
		return map;
	}
	
	
}
