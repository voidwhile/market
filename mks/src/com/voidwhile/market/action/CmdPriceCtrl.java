package com.voidwhile.market.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.core.utils.Slf4JLogger;
import com.voidwhile.market.entity.CmdPrice;
import com.voidwhile.market.entity.EveEvent;
import com.voidwhile.market.service.CmdPriceService;
import com.voidwhile.market.service.EveEventService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;

@Controller
@RequestMapping("/cmd/price")
public class CmdPriceCtrl extends BaseController {
	
	private final static Slf4JLogger logger = Slf4JLogger.getLogger(CmdPriceCtrl.class);
	
	@Autowired
	private CmdPriceService service;
	
	@Autowired
	private EveEventService eventService;
	
	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map,Integer cmdId) {
		commonData(request, map);
		Map<String, Object> param = new HashMap<>();
		param.put("cmdId", cmdId);
		List<CmdPrice> list  = service.findByMap(param);
		map.put("priceList", list);
		return "market/cmd/cmd_price_add";
	}
	
	@RequestMapping("/add.do")
	public String add(ModelMap map,Integer cmdId) {
		map.put("cmdId", cmdId);
		List<EveEvent> eventList = eventService.findEvent();
		map.put("eventList", eventList);
		return "market/price/price_add";
	}
	
	@RequestMapping("/edit.do")
	public String edit(ModelMap map,String priceId) {
		CmdPrice price = service.getById(priceId);
		List<EveEvent> eventList = eventService.findEvent();
		map.put("price", price);
		map.put("eventList", eventList);
		return "market/price/price_edit";
	}
	
	@RequestMapping("/del.do")
	@ResponseBody
	public Map<String, Object> del(String[] ids){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			service.deleteByIds(ids);
			map.put("status", true);
			map.put("message", "删除成功");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "删除失败："+e.getMessage());
		}
		return map;
	}
	
	

	@RequestMapping("/list_data.do")
	@ResponseBody
	public Map<String, Object> list_data(PageBean page, HttpServletRequest request, CmdPrice entity) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			logger.debug("查询商品价格体系");
			param.put("cmdId", entity.getCmdId());
			param.put("status", entity.getStatus());
			param.put("priceType", entity.getPriceType());
			param.put("eventId", entity.getEventId());
			param.put("price", entity.getPrice());
			PageResult<CmdPrice> result = service.findPageData(param, page.getPage(), page.getRows(), null);
			map.put("rows", result.getList());
			map.put("total", result.getTotal());
		} catch (Exception e) {
			logger.debug("查询商品价格体系失败"+e.getMessage());
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/save.do")
	public Map<String, Object> save( CmdPrice entity){
		logger.debug("保存商品价格");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if (entity.getPriceId()!=null) {
				service.update(entity);
			} else {
				service.save(entity);
			}
			map.put("status", true);
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "价格保存失败："+e.getMessage());
		}
		return map;
	}
}
