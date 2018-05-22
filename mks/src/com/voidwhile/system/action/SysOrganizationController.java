package com.voidwhile.system.action;

import java.util.ArrayList;
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

import com.voidwhile.common.action.BaseController;
import com.voidwhile.common.bean.tree.TreeNode;
import com.voidwhile.common.web.MarketUtils;
import com.voidwhile.system.bean.Admin;
import com.voidwhile.system.entity.SysOrganization;
import com.voidwhile.system.entity.SysSupplier;
import com.voidwhile.system.service.SysOrganizationService;
import com.voidwhile.system.service.SysUserService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 企业组织机构 controller
 * @author: xiaowei
 * @Create Date: 2014年12月5日 下午1:49:43
 *
 * @Version: v1.0
 */
@Controller
@RequestMapping("/admin/org")
public class SysOrganizationController extends BaseController {

	@Resource
	private SysOrganizationService sysOrganizationService;
	@Resource
	private SysUserService sysUserService;

	/**
	 * @MethodName: tree
	 * @Description: 获取组织机构树
	 * @param parentId
	 * @param exclude 不需要显示的ID
	 * @param noShowRoot 为true时不显示根节点
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月5日 下午4:26:51
	 */
	@RequestMapping("/tree.do")
	@ResponseBody
	public List<TreeNode> tree(String parentId, String exclude, String noShowRoot, HttpServletRequest request) {
		if (StringUtils.isEmpty(parentId)) {
			parentId = "0";
		}
		Admin admin = MarketUtils.getAdmin(request);
		SysSupplier supplier = admin.getSupplier();
		if (supplier == null) {
			return null;
		}

		List<TreeNode> treeNodes = sysOrganizationService.getTree(supplier.getUid(), parentId, exclude);
		
		List<TreeNode> rtl = new ArrayList<TreeNode>();
		if ("true".equals(StringUtils.trimToEmpty(noShowRoot))) {
			//增加根节点 请选择 liups
			TreeNode root = new TreeNode("0", "请选择...", "", false, treeNodes);
			rtl.add(root);
			return rtl;
		} else {
			if ("0".equals(parentId)) {
				TreeNode root = new TreeNode("0", supplier.getSupplierName(), "", false, treeNodes);
				rtl.add(root);
			} else {
				SysOrganization org = sysOrganizationService.getById(parentId);
				TreeNode root = new TreeNode(parentId, org.getOrgName(), "", false, treeNodes);
				rtl.add(root);
			}
			return rtl;
		}
	}

	/**
	 * @MethodName: list
	 * @Description: 转向组织机构列表页面
	 * @param request
	 * @param model
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月5日 下午1:51:21
	 */
	@RequestMapping("/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		return "system/organization/list";
	}

	/**
	 * @MethodName: listData
	 * @Description: 获取数据
	 * @param page
	 * @param orgName
	 * @param status
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月5日 下午1:52:56
	 */
	@RequestMapping("/list.do")
	@ResponseBody
	public Map<String, Object> listData(String orgName, String status, HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();

		Admin admin = MarketUtils.getAdmin(request);
		param.put("supplierId", admin.getSupplier().getUid());

		if (StringUtils.isNotEmpty(orgName)) {
			param.put("orgName", orgName);
		}

		if (StringUtils.isNotEmpty(status)) {
			param.put("status", status);
		}

		List<Map<String, Object>> orgList = sysOrganizationService.getTreeGridData(param);
		int total = sysOrganizationService.countByMap(param);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", orgList);
		return map;
	}

	/**
	 * @MethodName: add
	 * @Description: 转向添加页面
	 * @param pId 上级组织机构ID
	 * @param request
	 * @param model
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月4日 上午10:44:38
	 */
	@RequestMapping("/v_add.do")
	public String add(String pId, HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		model.put("pId", pId);
		return "system/organization/add";
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
		SysOrganization entity = sysOrganizationService.getById(uid);
		model.addAttribute("model", entity);
		return "system/organization/edit";
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
	public Map<String, Object> save(SysOrganization entity, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isEmpty(entity.getOrgName())) {
			map.put("status", false);
			map.put("message", "机构名称不能为空!");
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

				sysOrganizationService.save(entity);
			} else { // 修改
				sysOrganizationService.update(entity);
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
			sysOrganizationService.deleteByIds(ids);
			rltMap.put("success", "1");
			rltMap.put("msg", "删除成功");
		} catch (Exception e) {
			rltMap.put("success", "0");
			rltMap.put("msg", "出错了！" + e.getMessage());
		}
		return rltMap;
	}

}
