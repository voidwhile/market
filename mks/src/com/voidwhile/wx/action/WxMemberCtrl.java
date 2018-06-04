package com.voidwhile.wx.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voidwhile.market.service.OdrCartService;

@Controller
@RequestMapping("/wx/mbe")
public class WxMemberCtrl {

	@Autowired
	private OdrCartService service;
	
	@RequestMapping("/mine.wx")
	public String mine(String memberId,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		map.put("memberId", memberId);
		return "weixin/cart/cmd_list";
	}
}
