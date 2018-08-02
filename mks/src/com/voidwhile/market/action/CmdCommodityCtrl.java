/** 
 * Project Name:mks 
 * File Name:CmdCommodityCtrl.java 
 * Package Name:com.voidwhile.market.action 
 * Date:2017年9月24日下午9:14:20 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/

package com.voidwhile.market.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.Tool;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.common.utils.Tools;
import com.voidwhile.core.utils.DateUtils;
import com.voidwhile.market.constant.MarketConstant;
import com.voidwhile.market.entity.CmdCommodity;
import com.voidwhile.market.entity.CmdLabel;
import com.voidwhile.market.entity.CmdPrice;
import com.voidwhile.market.entity.CmdSpecification;
import com.voidwhile.market.entity.PubImage;
import com.voidwhile.market.entity.RepInventory;
import com.voidwhile.market.entity.RunRecommend;
import com.voidwhile.market.entity.RunSale;
import com.voidwhile.market.service.CmdCommodityService;
import com.voidwhile.market.service.CmdLabelService;
import com.voidwhile.market.service.CmdPriceService;
import com.voidwhile.market.service.CmdSpecificationService;
import com.voidwhile.market.service.PubImageService;
import com.voidwhile.market.service.RepInventoryService;
import com.voidwhile.market.service.RunRecomService;
import com.voidwhile.market.service.RunSaleService;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.service.SysParamService;

/**
 * ClassName:CmdCommodityCtrl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年9月24日 下午9:14:20 <br/>
 * 
 * @author zhangzheng
 * @version
 * @since JDK 1.8
 * @see
 */
@Controller
@RequestMapping("/cmd/cmd")
public class CmdCommodityCtrl extends BaseController {

	Logger log = Logger.getLogger(CmdCommodityCtrl.class);

	@Autowired
	private CmdCommodityService service;
	@Autowired
	private CmdSpecificationService sftService;
	@Autowired
	private CmdLabelService labelService;
	@Autowired
	private SysParamService sysParamService;
	@Autowired
	private RepInventoryService inventoryService;
	@Autowired
	private PubImageService imgService;
	@Autowired
	private RunRecomService recomService;
	@Autowired
	private RunSaleService saleService;
	@Autowired
	private CmdPriceService priceService;

	@RequestMapping("/list.do")
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		commonData(request, map);
		return "market/cmd/list";
	}

	@RequestMapping("/list_data.do")
	@ResponseBody
	public Map<String, Object> list_data(PageBean page, HttpServletRequest request, CmdCommodity entity) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> map = new HashMap<String, Object>();
		String orderByClause = "create_time desc";
		param.put("cmdName", entity.getCmdName());
		param.put("cmdCode", entity.getCmdCode());
		param.put("cmdStatus", entity.getCmdStatus());
		param.put("putawayTime", entity.getPutawayTime());
		param.put("supplierId", entity.getSupplierId());
		if (entity.getBrand()!=null) {
			param.put("brandName", entity.getBrand().getBrandName());
		}
		PageResult<CmdCommodity> result = service.findPageData(param, page.getPage(), page.getRows(), orderByClause);
		map.put("rows", result.getList());
		map.put("total", result.getTotal());
		return map;
	}
	
	@RequestMapping("/detail.do")
	public String detail(String cmdId,ModelMap map){
		CmdCommodity cmd = service.getById(cmdId);
		map.put("cmd", cmd);
		map.put("fileUrl", MarketConstant.FILE_URL);
		return "market/cmd/cmd_detail";
	}
	
	@RequestMapping("/add.do")
	public String add(HttpServletRequest request, ModelMap model){
		commonData(request, model);
		String brandOptions = sysParamService.options("mk_cmd_brand", "brand_id", "brand_name", "1=1","6");
		String typeOptions = sysParamService.options("mk_cmd_type", "cmd_type", "type_name", "level=2",null);
		String statusOptions = sysParamService.options("mk_cmd_status", "cmd_status", "status_name", "1=1","1");
		String supplierOptions = sysParamService.options("mk_rep_supplier", "supplier_id", "supplier_name", "1=1",null);
		String specificationOptions = sysParamService.options("mk_cfg_specification", "cfg_sft_id", "sft_name", "1=1",null);
		
		model.addAttribute("brandOptions", brandOptions);
		model.addAttribute("typeOptions", typeOptions);
		model.addAttribute("statusOptions", statusOptions);
		model.addAttribute("supplierOptions", supplierOptions);
		model.addAttribute("specificationOptions", specificationOptions);
		return "market/cmd/cmd_add";
	}
	
	@RequestMapping("/edit.do")
	public String edit(HttpServletRequest request, ModelMap model,String uid){
		commonData(request, model);
		CmdCommodity cmd = service.getById(uid);
		String brandOptions = sysParamService.options("mk_cmd_brand", "brand_id", "brand_name", "1=1",Tools.toString(cmd.getCmdBrand()));
		String typeOptions = sysParamService.options("mk_cmd_type", "cmd_type", "type_name", "level=3",null);
		String statusOptions = sysParamService.options("mk_cmd_status", "cmd_status", "status_name", "1=1",Tools.toString(cmd.getCmdStatus()));
		String supplierOptions = sysParamService.options("mk_rep_supplier", "supplier_id", "supplier_name", "1=1",Tools.toString(cmd.getSupplierId()));
		String specificationOptions = sysParamService.options("mk_cfg_specification", "cfg_sft_id", "sft_name", "1=1",null);
		
		model.addAttribute("brandOptions", brandOptions);
		model.addAttribute("typeOptions", typeOptions);
		model.addAttribute("statusOptions", statusOptions);
		model.addAttribute("supplierOptions", supplierOptions);
		model.addAttribute("specificationOptions", specificationOptions);
		model.put("cmd", cmd);
		model.put("fileUrl", MarketConstant.FILE_URL);
		return "market/cmd/cmd_edit";
	}
	
	@RequestMapping("/save.do")
	@ResponseBody
	@Transactional
	public Map<String,Object> save(CmdCommodity entity,String dputawayTime,String dproduceDate,String dexpirationDate,String[] specification,String[] sftValue,Integer[] cmdLabel,String[] imgs){
		
		Date putawayTime = DateUtils.parseDate(dputawayTime);
		Date produceDate = DateUtils.parseDate(dproduceDate);
		Date expirationDate = DateUtils.parseDate(dexpirationDate);
		entity.setPutawayTime(putawayTime);
		entity.setProduceDate(produceDate);
		entity.setExpirationDate(expirationDate);
		entity.setCreateTime(new Date());
		
		Map<String, Object> map = new HashMap<>();
		try {
			if (imgs!=null) {
				entity.setImgPath(imgs[0]);
			}
			if (entity.getCmdId()!=null) {
				service.update(entity);
				labelService.deleteByCmdId(entity.getCmdId());
				sftService.deleteByCmdId(entity.getCmdId());
			} else {
				CmdPrice price = new CmdPrice();
				price.setPriceType(1);
				price.setPrice(entity.getPrice());
				priceService.save(price);
				entity.setCcPriceId(price.getPriceId());
				service.save(entity);
				price.setCmdId(entity.getCmdId());
				priceService.update(price);
				RepInventory inventory = new RepInventory();
				inventory.setCmdId(entity.getCmdId());
				inventory.setLcd(new Date());
				inventoryService.save(inventory);
				
			}
			if (specification!=null) {
				for (int i = 0; i < specification.length; i++) {
					if (sftValue[i]!="") {
						CmdSpecification sft = new CmdSpecification();
						sft.setCmdId(entity.getCmdId());
						sft.setCfgSftId(Tools.toLong(specification[i]));
						sft.setSftVal(sftValue[i]);
						sftService.save(sft);
					}
				}
			}
			if (cmdLabel!=null) {
				for (int i = 0; i < cmdLabel.length; i++) {
					CmdLabel label = new CmdLabel();
					label.setCmdId(entity.getCmdId());
					label.setCmdType(cmdLabel[i]);
					labelService.save(label);
				}
			}
			if (imgs!=null) {
				for (int i = 0; i < imgs.length; i++) {
					PubImage img = new PubImage();
					img.setPath(imgs[i]);
					img.setBizid(entity.getCmdId());
					img.setBiztype(2);
					img.setImgType(2);
					imgService.insertimage(img);
				}
			}
			map.put("status", true);
			map.put("message", "保存成功!");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", false);
			map.put("message", "出错啦!" + e.getMessage());
			return map;
		}
	}
	
	/**
	 * 
	 * delete:删除. <br/> 
	 * 
	 * @author zhangzheng 
	 * @param id
	 * @return 
	 * @since JDK 1.8
	 */
	@RequestMapping("/del.do")
	@ResponseBody
	public Map<String, Object> del(String[] ids) throws IOException {
		Map<String, Object> rltMap = new HashMap<String, Object>();
		if (ids == null || ids.length == 0) {
			rltMap.put("success", "0");
			rltMap.put("msg", "请选择要处理的数据！");
			return rltMap;
		}
		try {
			service.deleteByIds(ids);
			rltMap.put("success", "1");
			rltMap.put("msg", "删除成功!");
			return rltMap;
		} catch (Exception e) {
			rltMap.put("success", "0");
			rltMap.put("msg", "出错了！" + e.getMessage());
			return rltMap;
		}
	}
	
	@RequestMapping("/sale.do")
	public String sale(String cmdId,ModelMap model){
		CmdCommodity cmd = service.getById(cmdId);
		model.addAttribute("cmd", cmd);
		return "market/cmd/sale";
	}
	
	@RequestMapping("/recom.do")
	public String recom(String cmdId,ModelMap model){
		CmdCommodity cmd = service.getById(cmdId);
		model.addAttribute("cmd", cmd);
		return "market/cmd/recom";
	}
	
	
	/**
	 * 添加推荐
	 * @param cmdId
	 * @param intro
	 * @param dRecomDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveRecom.do")
	public Map<String, Object> saveRecom(Long cmdId,String intro,String dRecomDay){
		Map<String, Object> rltMap = new HashMap<String, Object>();
		try {
			RunRecommend c = new RunRecommend();
			c.setIntro(intro);
			c.setRrCmdId(cmdId);
			if (dRecomDay!=null) {
				c.setRecomDay(DateUtils.parseDate(dRecomDay));
			}
			recomService.save(c);
			rltMap.put("status", true);
			rltMap.put("message", "保存成功！");
		} catch (Exception e) {
			rltMap.put("status", false);
			rltMap.put("message", "保存失败："+e.getMessage());
		}
		return rltMap;
	}
	
	/**
	 * 添加特价
	 * @param cmdId
	 * @param intro
	 * @param dRecomDay
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveSale.do")
	public Map<String, Object> saveSale(RunSale sale,String dSaleDay){
		Map<String, Object> rltMap = new HashMap<String, Object>();
		try {
			if (dSaleDay!=null) {
				sale.setSaleDay(DateUtils.parseDate(dSaleDay));
			}
			saleService.save(sale);
			rltMap.put("status", true);
			rltMap.put("message", "保存成功！");
		} catch (Exception e) {
			rltMap.put("status", false);
			rltMap.put("message", "保存失败："+e.getMessage());
		}
		return rltMap;
	}
	
	
}
