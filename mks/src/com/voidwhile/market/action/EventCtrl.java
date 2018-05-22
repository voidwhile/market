package com.voidwhile.market.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.core.utils.DateUtils;
import com.voidwhile.market.entity.EveEvent;
import com.voidwhile.market.entity.EveEventCmd;
import com.voidwhile.market.entity.PubImage;
import com.voidwhile.market.service.EveEventService;
import com.voidwhile.market.service.PubImageService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.service.SysParamService;

@Controller
@RequestMapping("/evt/event")
public class EventCtrl {
	
	@Autowired
	private EveEventService service;
	@Autowired
	private PubImageService imgService;
	@Autowired
	private SysParamService sysParamService;
	
	@RequestMapping("/list.do")
	public String list(){
		return "market/event/list";
	}
	
	@ResponseBody
	@RequestMapping("/list_data.do")
	public Map<String, Object> list_data(PageBean page,String title,Integer eveStatus,String cmdName,String startTime,String endTime){
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		try {
			param.put("eveStatus", eveStatus);
			param.put("title", title);
			param.put("cmdName", cmdName);
			param.put("startTime", startTime);
			param.put("endTime", endTime);
			PageResult<EveEvent> result = service.findPageData(param, page.getPage(), page.getRows(), "fcd desc");
			map.put("rows", result.getList());
			map.put("total", result.getTotal());
			return map;
		} catch (Exception e) {
			
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/list_cmd_data.do")
	public Map<String, Object> list_cmd_data(PageBean page,Long eventId){
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		try {
			param.put("eventId", eventId);
			PageResult<EveEventCmd> result = service.getCmdPageData(param, page.getPage(), page.getRows(), null);
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
	
	@RequestMapping("/add_cmd.do")
	public String add_cmd(Long eventId,ModelMap map){
		String brandOptions = sysParamService.options("mk_cmd_brand", "brand_id", "brand_name", "1=1","6");
		String typeOptions = sysParamService.options("mk_cmd_type", "cmd_type", "type_name", "level=3",null);
		String cmdOptions = sysParamService.options("mk_cmd_commodity", "cmd_id", "cmd_name", "1=1",null);
		map.put("eventId", eventId);
		map.put("brandOptions", brandOptions);
		map.put("typeOptions", typeOptions);
		map.put("cmdOptions", cmdOptions);
		return "market/event/add_cmd";
	}
	

	@RequestMapping("/edit.do")
	public String edit(Long eventId,ModelMap map){
		try {
			EveEvent evt = service.getById(eventId.toString());
			map.put("evt", evt);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public Map<String,Object> save(EveEvent entity,String dstartTime,String dendTime,String[] imgs){
		Map<String, Object> map = new HashMap<>();
		
		try {
			Date startTime = DateUtils.parseDate(dstartTime);
			Date endTime = DateUtils.parseDate(dendTime);
			entity.setStartTime(startTime);
			entity.setEndTime(endTime);
			entity.setFcd(new Date());
			if (entity.getEventId()!=null) {
				service.update(entity);
			} else {
				service.save(entity);
			}
			if (imgs!=null) {
				PubImage record = new PubImage();
				record.setBizid(entity.getEventId());
				record.setBiztype(3);
				imgService.deleteByBizId(record);
				for (int i = 0; i < imgs.length; i++) {
					PubImage img = new PubImage();
					img.setPath(imgs[i]);
					img.setBizid(entity.getEventId());
					img.setBiztype(3);
					img.setImgType(2);
					imgService.insertimage(img);
				}
			}
			map.put("status", true);
			map.put("message", "保存成功！");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "保存失败！"+e.getMessage());
		}
		return map;
	}
	
	@RequestMapping("/saveCmd.do")
	@ResponseBody
	@Transactional
	public Map<String,Object> saveCmd(Long eventId,Long[] cmdIds,BigDecimal[] prices){
		Map<String, Object> map = new HashMap<>();
		try {
			service.save(eventId, cmdIds, prices);
			map.put("status", true);
			map.put("message", "保存成功！");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "保存失败："+e.getMessage());
		}
		
		return map;
	}
	
	
}
