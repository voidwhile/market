package com.voidwhile.wx.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wx/index")
public class IndexCtrl {
	
	@RequestMapping("/index.wx")
	public String index(){
		return "weixin/index";
	}
}
