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
import com.voidwhile.common.web.MarketUtils;
import com.voidwhile.system.bean.Admin;
import com.voidwhile.system.constant.SysConstant;
import com.voidwhile.system.entity.SysMenus;
import com.voidwhile.system.service.SysMenusService;
import com.voidwhile.system.service.SysRoleService;

/**
 * 
 * CopyRright (c) 2017: 
 * 
 * @Description: 菜单管理
 * @author: liu
 * @Create Date: 2014-11-19 下午3:52:05
 *
 * @Version: v1.0
 */
@Controller
@RequestMapping("/admin/menus")
public class SysMenusController extends BaseController {
	@Resource
	private SysMenusService menusService;
	@Resource
	private SysRoleService sysRoleService;

	@RequestMapping("/menus_tree.do")
	@ResponseBody
	public Map<String, Object> menusTree(HttpServletRequest request, String roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			Admin admin = MarketUtils.getAdmin(request);
			String orderByClause = "menu_order asc";
			param.put("orderByClause", orderByClause);
			param.put("supplierLevel" + admin.getSupplier().getSupplierLevel(), "1");

			// 根据角色id得到角色的菜单
			List<SysMenus> menus = new ArrayList<SysMenus>();
			if (StringUtils.isNotEmpty(roleId)) {
				menus = menusService.findMenusByRoleId(roleId);
			}
			// 设置选中
			List<Map<String, Object>> list = menusService.getMenuTree(param);
			List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
			if (!list.isEmpty()) {
				for (Map<String, Object> m : list) {
					if (menus.size() > 0) {
						for (int i = 0; i < menus.size(); i++) {
							if (m.get("uid").equals(menus.get(i).getUid())) {
								m.put("flag", m.get("uid"));
							}
						}
					}
					newList.add(m);
				}
			}
			map.put("rows", newList);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @MethodName: list
	 * @Description: 功能描述
	 * @param request
	 * @param model
	 * @return
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-23 上午11:37:58
	 */
	@RequestMapping(value = "/v_list.do")
	public String list(HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		return "system/menu/list";
	}

	/**
	 * 
	 * @MethodName: menuList
	 * @Description: 菜单list
	 * @return
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-23 下午1:24:13
	 */
	@RequestMapping(value = "/list.do")
	@ResponseBody
	public Map<String, Object> listData(HttpServletRequest request) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("status", SysConstant.YES);
		List<Map<String, Object>> menuList = menusService.getTreeGridData(param);
		int total = menusService.countByMap(param);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", menuList);
		map.put("total", total);
		return map;
	}

	@RequestMapping(value = "/level_save.do")
	public String saveLevel(String[] supplierLevel0, String[] supplierLevel1, String[] supplierLevel2, String[] supplierLevel3, String[] supplierLevel4,
			String[] supplierLevel5, HttpServletRequest request, ModelMap model) {
		menusService.saveMenuLevel(supplierLevel0, supplierLevel1, supplierLevel2, supplierLevel3, supplierLevel4, supplierLevel5);
		return list(request, model);
	}

}
