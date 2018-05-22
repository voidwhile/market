package com.voidwhile.market.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.web.MarketUtils;
import com.voidwhile.market.entity.RepInventory;
import com.voidwhile.market.entity.RepTransact;
import com.voidwhile.market.service.RepInventoryService;
import com.voidwhile.market.service.RepTransactService;
import com.voidwhile.system.bean.Admin;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.service.SysParamService;

@Controller
@RequestMapping("/rep/trans")
public class RepTransactCtrl {

	@Autowired
	private RepTransactService service;
	
	@Autowired
	private RepInventoryService invService;
	
	@Autowired
	private SysParamService sysParamService;
	
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@RequestMapping("/list_data.do")
	@ResponseBody
	public Map<String, Object> list_data(PageBean page, String inventoryId,String startTime,String endTime){
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> param = new HashMap<>();
		try {
			param.put("inventoryId", inventoryId);
			param.put("startTime", startTime);
			param.put("endTime", endTime);
			PageResult<RepTransact> result = service.findPageData(param, page.getPage(), page.getRows(), "");
			map.put("rows", result.getList());
			map.put("total", result.getTotal());
		} catch (Exception e) {
			map.put("", "");
		}
		
		return map;
	}
	
	@RequestMapping("/add.do")
	public String add(String inventoryId,ModelMap map){
		String cmdId = "";
				
		if (inventoryId!=null&&!"".equals(inventoryId)) {
			RepInventory inv = invService.getById(inventoryId);
			map.put("inventory", inv);
			cmdId = inv.getCmdId().toString();
		}
		String brandOptions = sysParamService.options("mk_cmd_brand", "brand_id", "brand_name", "1=1","");
		String cmdOptions = sysParamService.options("mk_cmd_commodity", "cmd_id", "cmd_name", "1=1",cmdId);
		String supplierOptions = sysParamService.options("mk_rep_supplier", "supplier_id", "supplier_name", "1=1","");
		map.put("brandOptions", brandOptions);
		map.put("supplierOptions", supplierOptions);
		map.put("cmdOptions", cmdOptions);
		
		return "market/inventory/inventory_add";
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, RepTransact trans,Long cmdId){
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); // 事物隔离级别，开启新事务，这样会比较安全些。
		TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
		Admin admin = MarketUtils.getAdmin(request);
		Map<String, Object> map = new HashMap<>();
		
		try {
			if (trans.getInventoryId()==null) {
				RepInventory inv = new RepInventory();
				inv.setCmdId(cmdId);
				inv.setStock(trans.getAmount());
				invService.save(inv);
				trans.setInventoryId(inv.getInventoryId());
			} else {
				RepInventory inv = invService.getById(trans.getInventoryId().toString());
				inv.setStock(inv.getStock()+trans.getAmount());
				invService.update(inv);
			}
			trans.setTransTime(new Date());
			trans.setFcd(new Date());
			trans.setUserId(admin.getUser().getUid());
			service.save(trans);
			map.put("status", true);
			map.put("message", "保存成功!");
			transactionManager.commit(status);
			return map;
		} catch (Exception e) {
			transactionManager.rollback(status);
			e.printStackTrace();
			map.put("status", false);
			map.put("message", "保存失败!" + e.getMessage());
			return map;
		}
	}
}
