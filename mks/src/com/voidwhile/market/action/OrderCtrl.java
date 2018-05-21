package com.voidwhile.market.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.core.utils.Logger;
import com.voidwhile.core.utils.Slf4JLogger;
import com.voidwhile.market.entity.OdrOrder;
import com.voidwhile.market.entity.OdrOrderDetail;
import com.voidwhile.market.service.OrderService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;

@Controller
@RequestMapping("/odr/order")
public class OrderCtrl {

	private Logger log = Slf4JLogger.getLogger(OrderCtrl.class);
	
	@Autowired
	private OrderService service;
	
	@RequestMapping("/list.do")
	public String list(){
		log.info("/odr/order/list.do");
		
		return "market/order/list";
	}

	@ResponseBody
	@RequestMapping("/list_data.do")
	public Map<String, Object> list_data(PageBean page,OdrOrder entity,String cmdName,String memberName,String startTime,String endTime){
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		log.info("/odr/order/list.do");
		try {
			param.put("status", entity.getStatus());
			param.put("orderCode", entity.getOrderCode());
			param.put("payStatus", entity.getPayStatus());
			param.put("memberName", memberName);
			param.put("cmdName", cmdName);
			param.put("startTime", startTime);
			param.put("endTime", endTime);
			PageResult<OdrOrder> result = service.findPageData(param, page.getPage(), page.getRows(), " create_time desc ");
			map.put("rows", result.getList());
			map.put("total", result.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/list_detail_data.do")
	public Map<String, Object> list_detail_data(PageBean page,String orderId){
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		param.put("orderId", orderId);
		try {
			PageResult<OdrOrderDetail> result = service.getDetail(param, page.getPage(), page.getRows());
			map.put("rows", result.getList());
			map.put("total", result.getTotal());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/deliver.do")
	public Map<String, Object> deliver(Long orderId){
		Map<String, Object> map = new HashMap<>();
		try {
			service.deliver(orderId);
			map.put("status", true);
			map.put("message", "状态已变更");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "状态变更失败");
		}
		return map;
	}
}
