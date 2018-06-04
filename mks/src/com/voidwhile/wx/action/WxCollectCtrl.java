package com.voidwhile.wx.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.market.entity.MbeCollect;
import com.voidwhile.market.service.MbeCollectService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;

@Controller
@RequestMapping("/wx/collect")
public class WxCollectCtrl {
	
	@Autowired
	private MbeCollectService service;
	
	@RequestMapping("/save.wx")
	@ResponseBody
	public Map<String, Object> save(MbeCollect collect){
		Map<String, Object> map = new HashMap<>();
		try {
			collect.setCollectTime(new Date());
			service.save(collect);
			map.put("status", true);
			map.put("message", "保存成功");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "保存失败");
		}
		return map;
	}
	
	@RequestMapping("/myCollet.wx")
	public String myCollet(PageBean page, String memberId,ModelMap map){
		Map<String, Object> param = new HashMap<>();
		param.put("memberId", memberId);
		PageResult<MbeCollect> result = service.findPageData(param, page.getPage(), page.getRows(), "");
		map.put("cmdList", result.getList());
		map.put("memberId", memberId);
		return "weixin/collect/collect_list";
	}
}
