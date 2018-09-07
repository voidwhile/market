package com.voidwhile.wx.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voidwhile.market.entity.MbeAddress;
import com.voidwhile.market.entity.MbeMember;
import com.voidwhile.market.entity.OdrOrder;
import com.voidwhile.market.service.MbeAddressServcie;
import com.voidwhile.market.service.MbeMemberService;
import com.voidwhile.market.service.OrderService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;

@Controller
@RequestMapping("/wx/mbe")
public class WxMemberCtrl {

	@Autowired
	private OrderService orderService;
	@Autowired
	private MbeAddressServcie mbeAddressServcie;
	@Autowired
	private MbeMemberService service;
	
	
	
	@RequestMapping("/mine.wx")
	public String mine(String memberId,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		MbeMember member = service.getById(memberId);
		map.put("memberId", memberId);
		map.put("member", member);
		return "weixin/mine/mine";
	}
	
	@RequestMapping("/order.wx")
	public String order(OdrOrder entity,PageBean page,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		try {
			param.put("memberId", entity.getMemberId());
			if (entity.getStatus()!=null) {
				param.put("status", entity.getStatus());
			}
			if (entity.getOrderId()!=null) {
				param.put("orderId", entity.getOrderId());
			}
			
			PageResult<OdrOrder> result = orderService.findPageData(param, page.getPage(), page.getApprows(), " create_time desc ");
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
		try {
			MbeMember member = service.getById(memberId);
			map.put("member", member);
		} catch (Exception e) {
			// TODO: handle exception
		}
		map.put("memberId", memberId);
		return "weixin/mine/info";
	}
	
	@RequestMapping("/address.wx")
	public String address(String memberId,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		List<MbeAddress> addrList = mbeAddressServcie.findByMap(param);
		map.put("memberId", memberId);
		map.put("addrList", addrList);
		return "weixin/mine/address";
	}
	

	@RequestMapping("/selectAddr.wx")
	public String selectAddr(String memberId,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		List<MbeAddress> addrList = mbeAddressServcie.findByMap(param);
		map.put("memberId", memberId);
		map.put("addrList", addrList);
		return "weixin/mine/selectAddr";
	}
	
	
	
	@RequestMapping("/toAddr.wx")
	public String toAddr(String addrId,String memberId,ModelMap map) {
		if (addrId!=null) {
			MbeAddress addr = mbeAddressServcie.getById(addrId);
			map.put("addr", addr);
		}
		map.put("memberId", memberId);
		return "/weixin/mine/addr_add";
	}
	
	@RequestMapping("/addAddr.wx")
	public String addAddr(MbeAddress entity,ModelMap map) {
		if (entity.getAddrId()==null) {
			mbeAddressServcie.save(entity);
		} else {
			mbeAddressServcie.update(entity);
		}
		map.put("memberId", entity.getMemberId());
		return "redirect:/wx/mbe/address.wx";
	}
	
	
}
