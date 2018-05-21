package com.voidwhile.system.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.voidwhile.common.action.BaseController;
import com.voidwhile.common.mapper.JsonMapper;
import com.voidwhile.common.web.MarketUtils;
import com.voidwhile.system.bean.Admin;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.SysParam;
import com.voidwhile.system.entity.SysParamItem;
import com.voidwhile.system.service.SysParamItemService;
import com.voidwhile.system.service.SysParamService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统编码管理类
 * @author: xiaowei
 * @Create Date: 2014年12月3日 上午11:32:14
 *
 * @Version: v1.0
 */
@Controller
@RequestMapping("/admin/param")
public class SysParamController extends BaseController {

	@Resource
	private SysParamService sysParamService;

	@Resource
	private SysParamItemService sysParamItemService;

	/**
	 * @MethodName: index
	 * @Description: 跳转列表页
	 * @param model
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年11月28日 下午4:04:35
	 */
	@RequestMapping(value = "/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		return "system/param/list";
	}

	@RequestMapping(value = "/list.do")
	@ResponseBody
	public Map<String, Object> listData(PageBean page, String paramCode, String paramName, HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();

		Admin admin = MarketUtils.getAdmin(request);
		param.put("supplierId", admin.getSupplier().getUid());

		if (StringUtils.isNotEmpty(paramCode)) {
			param.put("paramCode", paramCode);
		}

		if (StringUtils.isNotEmpty(paramName)) {
			param.put("paramName", paramName);
		}

		String orderByClause = "create_time desc";
		if (StringUtils.isNoneEmpty(page.getSort())) {
			orderByClause = page.getOrderByClause();
		}

		PageResult<SysParam> pageResult = sysParamService.findPageData(param, page.getPage(), page.getRows(), orderByClause);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pageResult.getTotal());
		map.put("rows", pageResult.getList());
		return map;
	}

	/**
	 * @MethodName: add
	 * @Description: 编码添加页面
	 * @param request
	 * @param model
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月4日 上午10:44:38
	 */
	@RequestMapping("/v_add.do")
	public String add(HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		return "system/param/add";
	}

	/**
	 * @MethodName: edit
	 * @Description: 编码修改页面
	 * @param request
	 * @param model
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月4日 上午10:46:03
	 */
	@RequestMapping("/v_edit.do")
	public String edit(String uid, HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		if (StringUtils.isEmpty(uid)) {
			throw new RuntimeException("参数错误！");
		}
		SysParam param = sysParamService.getById(uid);
		model.addAttribute("model", param);
		return "system/param/edit";
	}

	@RequestMapping(value = "/save.do")
	@ResponseBody
	public Map<String, Object> save(SysParam entity, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(entity.getParamCode())) {
			map.put("status", false);
			map.put("message", "编码不能为空!");
			return map;
		}
		if (StringUtils.isEmpty(entity.getParamName())) {
			map.put("status", false);
			map.put("message", "保存成功!");
			return map;
		}

		try {
			// 新增
			if (StringUtils.isEmpty(entity.getUid())) {
				if (StringUtils.isEmpty(entity.getSupplierId())) {
					map.put("status", false);
					map.put("message", "参数出错!");
					return map;
				}

				SysParam config = sysParamService.getBySupplierIdAndParamCode(entity.getSupplierId(), entity.getParamCode());
				if (config != null) {
					map.put("status", false);
					map.put("message", "编码(<b>" + entity.getParamCode() + "</b>)已经存在!");
					return map;
				}

				entity.setIssystem("0");
				entity.setCreateTime(new Date());
				entity.setUpdateTime(new Date());
				sysParamService.save(entity);
			} else { // 修改
				entity.setUpdateTime(new Date());
				sysParamService.update(entity);
			}
			map.put("status", true);
			map.put("message", "保存成功!");
			return map;
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "保存失败：" + e.getMessage());
			return map;
		}
	}

	@RequestMapping(value = "/del.do")
	@ResponseBody
	public Map<String, Object> delete(String[] ids, HttpServletRequest request) {
		Map<String, Object> rltMap = new HashMap<String, Object>();
		if (ids == null || ids.length == 0) {
			rltMap.put("success", "0");
			rltMap.put("msg", "请选择要处理的数据！");
			return rltMap;
		}

		try {
			sysParamService.deleteByIds(ids);
			rltMap.put("success", "1");
			rltMap.put("msg", "删除成功");
		} catch (Exception e) {
			rltMap.put("success", "0");
			rltMap.put("msg", "出错了！" + e.getMessage());
		}
		return rltMap;
	}

	// =============================编码维护开始==================================

	/**
	 * @MethodName: itemList
	 * @Description: 转向编码列表
	 * @param paramId
	 * @param model
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月3日 下午2:39:23
	 */
	@RequestMapping(value = "/v_item_list.do")
	public String itemList(String paramId, ModelMap model, HttpServletRequest request) {
		commonData(request, model);
		model.put("paramId", paramId);
		return "system/param/item_list";
	}

	/**
	 * @MethodName: itemListData
	 * @Description: 编码数据
	 * @param paramId
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月3日 下午2:51:59
	 */
	@RequestMapping(value = "/item_list.do")
	@ResponseBody
	public Map<String, Object> itemListData(String paramId, HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();

		if (StringUtils.isEmpty(paramId)) {
			throw new RuntimeException("参数错误！");
		}

		param.put("paramId", paramId);
		param.put("orderByClause", "rank");

		List<SysParamItem> itemList = sysParamItemService.findByMap(param);
		int total = sysParamItemService.countByMap(param);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", itemList);
		return map;
	}

	/**
	 * @MethodName: saveItem
	 * @Description: 功能描述
	 * @param inserted
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月3日 下午5:36:44
	 */
	@RequestMapping(value = "/item_save.do")
	@ResponseBody
	public Map<String, Object> saveItem(String paramId, HttpServletRequest request) {
		String inserted = request.getParameter("inserted");
		String deleted = request.getParameter("deleted");
		String updated = request.getParameter("updated");

		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<SysParamItem> insertedList = null;
			List<SysParamItem> deletedList = null;
			List<SysParamItem> updatedList = null;
			JsonMapper jsonMapper = new JsonMapper();
			jsonMapper.setDateFormat("yyyy-MM-dd HH:mm:ss");
			JavaType javaType = jsonMapper.contructCollectionType(ArrayList.class, SysParamItem.class);
			try {
				insertedList = jsonMapper.fromJson(inserted, javaType);
			} catch (Exception e) {
			}
			try {
				deletedList = jsonMapper.fromJson(deleted, javaType);
			} catch (Exception e) {
			}
			try {
				updatedList = jsonMapper.fromJson(updated, javaType);
			} catch (Exception e) {
			}

			sysParamItemService.batchInsert(paramId, insertedList);
			sysParamItemService.batchDelete(deletedList);
			sysParamItemService.batchUpdate(updatedList);

			map.put("status", true);
			map.put("message", "保存成功!");
		} catch (Exception e) {
			map.put("status", false);
			map.put("message", "保存失败：<font color='red'>" + e.getMessage() + "</font>");
		}
		return map;
	}
	
	@RequestMapping("/options.do")
	@ResponseBody
	public Map<String, Object> options(String tableName,String code,String des,String whereClause,String selected){
		Map<String, Object> map = new HashMap<>();
		String opt = sysParamService.options(tableName, code, des, whereClause, selected);
		map.put("options", opt);
		return map;
	}
}
