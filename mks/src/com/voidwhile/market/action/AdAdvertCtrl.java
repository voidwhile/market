package com.voidwhile.market.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.core.utils.DateUtils;
import com.voidwhile.market.entity.AdAdvert;
import com.voidwhile.market.service.AdAdvertService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.constant.SysConstant;
import com.voidwhile.system.service.SysParamService;

@Controller
@RequestMapping("/evt/ad")
public class AdAdvertCtrl {
	
	@Autowired
	private AdAdvertService service;
	@Autowired
	private SysParamService sysParamService;
	
	@RequestMapping("/list.do")
	public String list(){
		return "market/advert/list";
	}
	
	@ResponseBody
	@RequestMapping("/list_data.do")
	public Map<String, Object> list_data(PageBean page, String title,String startTime,String endTime){
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("title", title);
		param.put("startTime", startTime);
		param.put("endTime", endTime);
		
		try {
			PageResult<AdAdvert> result = service.findPageData(param, page.getPage(), page.getRows(), "start_time");
			map.put("rows", result.getList());
			map.put("total", result.getTotal());
			map.put("imgUrl", SysConstant.IMG_URL);
		} catch (Exception e) {
		}
		return map;
	}
	
	@RequestMapping("/add.do")
	public String add(ModelMap map){
		String cmdOptions = sysParamService.options("mk_cmd_commodity", "cmd_id", "cmd_name", "1=1",null);
		map.put("cmdOptions", cmdOptions);
		return "market/advert/add";
	}
	
	@RequestMapping("/edit.do")
	public String edit(String uid,ModelMap map){
		AdAdvert advert = service.getById(uid);
		map.put("advert", advert);
		map.put("imgUrl", SysConstant.IMG_URL);
		return "market/advert/edit";
	}
	
	@RequestMapping("/del.do")
	@ResponseBody
	public Map<String, Object> del(String[] ids) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			service.deleteByIds(ids);
			map.put("status", true);
			map.put("msg", "删除成功！");
		} catch (Exception e) {
			map.put("status", false);
			map.put("msg", "删除失败！");
		}
		return map;
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, AdAdvert advert,String dstartTime,String dendTime){
		String ctx = request.getContextPath() == null ? "" : request.getContextPath();
		Map<String, Object> map = new HashMap<String, Object>();
		Date startTime = null;
		Date endTime = null;
		if (dstartTime!=null) {
			startTime = DateUtils.parseDate(dstartTime);
		}
		if (dendTime!=null) {
			endTime = DateUtils.parseDate(dendTime);
		}
		try {
			advert.setStartTime(startTime);
			advert.setEndTime(endTime);
			if (advert.getAdvertId()==null) {
				service.save(advert);
				if (advert.getUrl()==null||"".equals(advert.getUrl())) {
					advert.setUrl(ctx+"/wx/index/advert.wx?advertId="+advert.getAdvertId());
					service.update(advert);
				}
			} else {
				service.update(advert);
			}
			map.put("status", true);
			map.put("message", "保存成功！");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "保存失败！");
		}
		return map;
	}
	
}
