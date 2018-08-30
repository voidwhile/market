package com.voidwhile.wx.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.market.entity.OdrCart;
import com.voidwhile.market.service.OdrCartService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;

@Controller
@RequestMapping("/wx/cart")
public class WxCartCtrl {

	@Autowired
	private OdrCartService service;
	
	@RequestMapping("/save.wx")
	@ResponseBody
	public Map<String, Object> save(OdrCart cart){
		Map<String, Object> map = new HashMap<>();
		try {
			cart.setAddTime(new Date());
			service.add(cart);
			map.put("status", true);
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "保存失败");
		}
		return map;
	}
	
	@RequestMapping("/myCart.wx")
	public String myCollet(PageBean page, Long memberId,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		PageResult<OdrCart> result = service.findPageData(param, page.getPage(), page.getRows(), "");
		Double totalPrice = service.sum(memberId);
		map.put("cmdList", result.getList());
		map.put("totalPrice", totalPrice);
		map.put("memberId", memberId);
		return "weixin/cart/cart_list";
	}
}
