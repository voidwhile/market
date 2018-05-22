package com.voidwhile.system.action;

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
import com.voidwhile.system.entity.SysLog;
import com.voidwhile.system.service.SysLogService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统日志 Controller
 * @author: xiaowei
 * @Create Date: 2014年10月24日 下午1:18:15
 *
 * @Version: v1.0
 */
@Controller
@RequestMapping("/admin/log")
public class SysLogController extends BaseController {

	@Resource
	private SysLogService sysLogService;

	@RequestMapping(value = "/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		boolean showTree = false;
		Admin admin = MarketUtils.getAdmin(request);
		if ("0".equals(admin.getSupplier().getUid())) {
			showTree = true;
		}
		model.addAttribute("showTree", showTree);
		return "system/log/list";
	}

	@RequestMapping(value = "/list.do")
	@ResponseBody
	public Map<String, Object> listData(PageBean page, String supplierId, String operator, String logLevel, String logType, String startTime, String endTime,
			HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();

		Admin admin = MarketUtils.getAdmin(request);
		if ("0".equals(admin.getSupplier().getUid())) {
			if (StringUtils.isNotEmpty(supplierId)) {
				param.put("supplierId", supplierId);
			}
		} else {
			param.put("supplierId", admin.getSupplier().getUid());
		}

		if (StringUtils.isNotEmpty(operator)) {
			param.put("operator", operator);
		}

		if (StringUtils.isNotEmpty(logLevel)) {
			param.put("logLevel", logLevel);
		}

		if (StringUtils.isNotEmpty(logType)) {
			param.put("logType", logType);
		}

		if (StringUtils.isNotEmpty(startTime)) {
			param.put("startTime", startTime);
		}

		if (StringUtils.isNotEmpty(endTime)) {
			param.put("endTime", endTime);
		}

		String orderByClause = "create_time desc";
		if (StringUtils.isNoneEmpty(page.getSort())) {
			orderByClause = page.getOrderByClause();
		}

		PageResult<SysLog> pageResult = sysLogService.findPageData(param, page.getPage(), page.getRows(), orderByClause);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", pageResult.getTotal());
		map.put("rows", pageResult.getList());
		return map;
	}

	@RequestMapping(value = "/del.do")
	@ResponseBody
	public Map<String, Object> del(String[] ids) {
		Map<String, Object> rltMap = new HashMap<String, Object>();
		if (ids == null || ids.length == 0) {
			rltMap.put("success", "0");
			rltMap.put("msg", "请选择要处理的数据！");
			return rltMap;
		}

		try {
			sysLogService.deleteByIds(ids);
			rltMap.put("success", "1");
			rltMap.put("msg", "删除成功");
		} catch (Exception e) {
			rltMap.put("success", "0");
			rltMap.put("msg", "出错了！" + e.getMessage());
		}
		return rltMap;
	}
}
