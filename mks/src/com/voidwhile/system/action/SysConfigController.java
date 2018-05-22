package com.voidwhile.system.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.common.web.MarketUtils;
import com.voidwhile.system.bean.Admin;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.SysConfig;
import com.voidwhile.system.service.SysConfigService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统运行参数设置Controller
 * @author: xiaowei
 * @Create Date: 2014年12月4日 下午3:56:30
 *
 * @Version: v1.0
 */
@Controller
@RequestMapping("/admin/config")
public class SysConfigController extends BaseController {

	@Resource
	private SysConfigService sysConfigService;
	/**
	 * @MethodName: index
	 * @Description: 转向列表页
	 * @param model
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月4日 下午3:57:20
	 */
	@RequestMapping(value = "/v_list.do")
	public String index(ModelMap model, HttpServletRequest request) {
		commonData(request, model);
		return "system/config/list";
	}

	/**
	 * @MethodName: listData
	 * @Description: 列表数据获取方法
	 * @param page
	 * @param paramCode
	 * @param paramName
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月4日 下午4:05:50
	 */
	@RequestMapping(value = "/list.do")
	@ResponseBody
	public Map<String, Object> listData(PageBean page, String configCode, String configDesc, HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();

		Admin admin = MarketUtils.getAdmin(request);
		param.put("supplierId", admin.getSupplier().getUid());

		if (StringUtils.isNotEmpty(configCode)) {
			param.put("configCode", configCode);
		}

		if (StringUtils.isNotEmpty(configDesc)) {
			param.put("configDesc", configDesc);
		}

		String orderByClause = "create_time desc";
		if (StringUtils.isNoneEmpty(page.getSort())) {
			orderByClause = page.getOrderByClause();
		}

		PageResult<SysConfig> pageResult = sysConfigService.findPageData(param, page.getPage(), page.getRows(), orderByClause);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pageResult.getTotal());
		map.put("rows", pageResult.getList());
		return map;
	}

	/**
	 * @MethodName: add
	 * @Description: 参数添加页面
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
		return "system/config/add";
	}

	/**
	 * @MethodName: edit
	 * @Description: 参数修改页面
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
		SysConfig entity = sysConfigService.getById(uid);
		model.addAttribute("model", entity);
		return "system/config/edit";
	}

	/**
	 * @MethodName: save
	 * @Description: 保存
	 * @param entity
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月4日 下午4:06:12
	 */
	@RequestMapping(value = "/save.do")
	@ResponseBody
	public Map<String, Object> save(SysConfig entity, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(entity.getConfigCode())) {
			map.put("status", false);
			map.put("message", "编码不能为空!");
			return map;
		}
		if (StringUtils.isEmpty(entity.getConfigValue())) {
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

				SysConfig config = sysConfigService.getBySupplierIdAndConfigCode(entity.getSupplierId(), entity.getConfigCode());
				if (config != null) {
					map.put("status", false);
					map.put("message", "参数(<b>" + entity.getConfigCode() + "</b>)已经存在!");
					return map;
				}

				entity.setCreateTime(new Date());
				entity.setUpdateTime(new Date());
				sysConfigService.save(entity);
			} else { // 修改
				entity.setUpdateTime(new Date());
				sysConfigService.update(entity);
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

	/**
	 * @MethodName: delete
	 * @Description: 删除
	 * @param ids
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月4日 下午4:06:20
	 */
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
			sysConfigService.deleteByIds(ids);
			rltMap.put("success", "1");
			rltMap.put("msg", "删除成功");
		} catch (Exception e) {
			rltMap.put("success", "0");
			rltMap.put("msg", "出错了！" + e.getMessage());
		}
		return rltMap;
	}
}
