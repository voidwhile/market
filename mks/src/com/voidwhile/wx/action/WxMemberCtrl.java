package com.voidwhile.wx.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voidwhile.market.entity.OdrOrder;
import com.voidwhile.market.entity.WechatUser;
import com.voidwhile.market.service.OdrCartService;
import com.voidwhile.market.service.OrderService;
import com.voidwhile.market.service.WechatUserService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;

@Controller
@RequestMapping("/wx/mbe")
public class WxMemberCtrl {

	@Autowired
	private OdrCartService service;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WechatUserService wechatUserService;
	
	
	@RequestMapping("/mine.wx")
	public String mine(Long memberId,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		WechatUser wxUser = wechatUserService.findByMemberId(memberId);
		map.put("memberId", memberId);
		map.put("wxUser", wxUser);
		return "weixin/mine/mine";
	}
	
	@RequestMapping("/order.wx")
	public String order(OdrOrder entity,PageBean page,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		try {
			param.put("memberId", entity.getMemberId());
			param.put("status", entity.getStatus());
			param.put("orderId", entity.getOrderId());
			
			PageResult<OdrOrder> result = orderService.findPageData(param, page.getPage(), page.getRows(), " create_time desc ");
			map.put("orderList", result.getList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "weixin/mine/order";
	}
	
	@RequestMapping("/feedback.wx")
	public String feedback(String memberId,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		
		map.put("memberId", memberId);
		return "weixin/mine/feedback";
	}
	
	@RequestMapping("/browse.wx")
	public String browse(String memberId,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		
		map.put("memberId", memberId);
		return "weixin/mine/browse";
	}
	
	@RequestMapping("/info.wx")
	public String info(String memberId,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		
		map.put("memberId", memberId);
		return "weixin/mine/info";
	}
	
	
	
	
}
