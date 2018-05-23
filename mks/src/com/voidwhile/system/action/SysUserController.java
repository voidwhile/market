package com.voidwhile.system.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.common.bean.tree.TreeNode;
import com.voidwhile.common.utils.SysParamHelper;
import com.voidwhile.common.web.MarketUtils;
import com.voidwhile.system.bean.Admin;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.constant.SysConstant;
import com.voidwhile.system.constant.SysParamConstant;
import com.voidwhile.system.entity.SysOrganization;
import com.voidwhile.system.entity.SysRole;
import com.voidwhile.system.entity.SysSupplier;
import com.voidwhile.system.entity.SysUser;
import com.voidwhile.system.service.SysOrganizationService;
import com.voidwhile.system.service.SysRoleService;
import com.voidwhile.system.service.SysUserService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 用户管理
 * @author: liupengshuang
 * @Create Date: 2014年11月19日 下午1:18:44
 *
 * @Version: v1.0
 */
@Controller
@RequestMapping("/admin/user")
public class SysUserController extends BaseController {
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysOrganizationService sysOrganizationService;
	@Resource
	private SysUserService sysUserService;
	private Map<String, Object> param;

	/**
	 * @MethodName: tree
	 * @Description: 获取用户、部门结构树
	 * @param parentId
	 * @param noShowRoot
	 * @param selected 已选择的用户（以,分隔)
	 * @param request
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月12日 下午4:48:05
	 */
	@RequestMapping("/tree.do")
	@ResponseBody
	public List<TreeNode> tree(String parentId, String noShowRoot, String selected, HttpServletRequest request) {
		Admin admin = MarketUtils.getAdmin(request);
		SysSupplier supplier = admin.getSupplier();
		if (supplier == null) {
			return null;
		}
		parentId = "0";
		List<String> selectedUser = null;
		if (StringUtils.isNotEmpty(selected)) {
			selectedUser = Arrays.asList(selected.split(","));
		}
		List<TreeNode> treeNodes = sysOrganizationService.getUserTree(supplier.getUid(), parentId, selectedUser);
		if ("true".equals(StringUtils.trimToEmpty(noShowRoot))) {
			return treeNodes;
		} else {
			// 显示根节点
			List<TreeNode> rtl = new ArrayList<TreeNode>();
			if ("0".equals(parentId)) {
				TreeNode root = new TreeNode("0", supplier.getSupplierName(), "", false, treeNodes);
				rtl.add(root);
			} else {
				SysOrganization org = sysOrganizationService.getById(parentId);
				TreeNode root = new TreeNode("dept|" + parentId, org.getOrgName(), "", false, treeNodes);
				// 把根目录下的用户也加入
				Map<String, Object> queryParam = new HashMap<String, Object>();
				queryParam.put("supplierId", supplier.getUid());
				queryParam.put("status", SysConstant.YES);
				queryParam.put("isSystem", SysConstant.NO);
				queryParam.put("deptId", parentId);
				List<SysUser> userList = sysUserService.findByMap(queryParam);
				List<TreeNode> childNodes = new ArrayList<TreeNode>();
				if (userList != null && userList.size() > 0) {
					for (SysUser user : userList) {
						boolean checked = false;
						if (selectedUser != null && selectedUser.size() > 0) {
							for (int i = 0; i < selectedUser.size(); i++) {
								if (selectedUser.get(i).toString().contains(user.getUid()+"")) {
									checked = true;
									break;
								}
							}

						}
						childNodes.add(new TreeNode("user|" + user.getUid(), user.getRealName(), "icon-user", checked, null));
					}
				}
				root.addChildren(childNodes);
				rtl.add(root);
			}
			return rtl;
		}
	}
	
	//单选
	@RequestMapping("/comSelList.do")
	public String comSelList(HttpServletRequest request, ModelMap model){
		commonData(request, model);
		String xbm = SysParamConstant.XBM;
		String xbOption = SysParamHelper.getCodeOption(MarketUtils.getAdmin(request).getSupplier().getUid(), xbm, "", true);
		model.addAttribute("xbOption", xbOption);
		return "system/user/comSelList";
	}
	//多选
	@RequestMapping("/comSelListDouble.do")
	public String comSelListDouble(HttpServletRequest request, ModelMap model){
		commonData(request, model);
		String xbm = SysParamConstant.XBM;
		String xbOption = SysParamHelper.getCodeOption(MarketUtils.getAdmin(request).getSupplier().getUid(), xbm, "", true);
		model.addAttribute("xbOption", xbOption);
		return "system/user/comSelListDouble";
	}
	
	//多选
	@RequestMapping("/comUserSelList.do")
	public String comUserSelList(HttpServletRequest request, ModelMap model){
		commonData(request, model);
		String uid = request.getParameter("uid");
		String driverUserId = request.getParameter("driverUserIds");
		String xbm = SysParamConstant.XBM;
		String xbOption = SysParamHelper.getCodeOption(MarketUtils.getAdmin(request).getSupplier().getUid(), xbm, "", true);
		model.addAttribute("xbOption", xbOption);
		if(uid!=""&&uid!=null){
			model.addAttribute("uid", uid);
		}
		if(driverUserId!=""&&driverUserId!=null){
			model.addAttribute("driverUserIds", driverUserId);
		}
		return "system/user/comUserSelList";
	}
	
	@RequestMapping("/list.do")
	public String userList(HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		String xbm = SysParamConstant.XBM;
		String xbOption = SysParamHelper.getCodeOption(MarketUtils.getAdmin(request).getSupplier().getUid(), xbm, "", true);
		model.addAttribute("xbOption", xbOption);
		return "system/user/list";
	}
	@RequestMapping("/list_data.do")
	@ResponseBody
	public Map<String, Object> list_d(PageBean page, HttpServletRequest request, String orgId, String roleId) {
		// 查询条件
		String gender = request.getParameter("gender");
		String userName = request.getParameter("userName");
		String mobile = request.getParameter("mobile");
		param = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(mobile)) {
			param.put("phone", mobile);
		}
		if (StringUtils.isNotEmpty(gender)) {
			param.put("gender", gender);
		}
		if (StringUtils.isNotEmpty(userName)) {
			param.put("userName", userName);
		}
		if (StringUtils.isNotEmpty(orgId) && !"0".equals(orgId)) {
			param.put("deptId", orgId);
		}
		Admin admin = MarketUtils.getAdmin(request);
		Map<String, Object> map = new HashMap<String, Object>();
		String orderByClause = "create_time desc";
		param.put("orderByClause", orderByClause);
		PageResult<SysUser> sysUserResult = null;
		param.put("supplierId", admin.getSupplier().getUid());
		param.put("isSystem", 0);
		//该员工是否有效(1.有效0.失效)
		param.put("status", "1");  //只显示有效员工
		sysUserResult = sysUserService.findPageData(param, page.getPage(), page.getRows(), orderByClause);
		List<SysUser> contents = sysUserResult.getList();
		// 过滤有该角色的用户
		if (StringUtils.isNotEmpty(roleId)) {
			List<String> uids = this.getUids(admin, roleId);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("uids", uids);
			List<SysRole> listUser = sysRoleService.getByRoleId(params);
			List<SysUser> tmpList = new ArrayList<SysUser>();
			for (SysRole u : listUser) {
				SysUser user = sysUserService.getById(u.getUid());
				for (int i = 0; i < contents.size(); i++) {
					if (user != null) {
						if (user.getUid().equals(contents.get(i).getUid())) {
							tmpList.add(contents.get(i));
							break;
						}
					}
				}
			}
			contents.removeAll(tmpList);
		}
		map.put("rows", contents);
		map.put("total", sysUserResult.getTotal());
		return map;
	}

	@RequestMapping("user_role_list.do")
	@ResponseBody
	public Map<String, Object> userRoleList(@RequestParam String uid) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<SysUser> list = sysUserService.getUserRoleById(uid);
			map.put("rows", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", "数据加载错误！");
		}
		return map;
	}

	/**
	 * 
	 * @MethodName: getUids
	 * @Description: 获取角色id
	 * @param admin
	 * @param roleId
	 * @return
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-5 下午2:25:20
	 */
	private List<String> getUids(Admin admin, String roleId) {
		param = new HashMap<String, Object>();
		param.put("supplierId", admin.getSupplier().getUid());
		List<String> uids = new ArrayList<String>();
		if (StringUtils.isNotEmpty(roleId)) {
			uids.add(roleId);
		} else {
			List<SysRole> sysRoleList = sysRoleService.findByMap(param);
			if (!sysRoleList.isEmpty()) {
				for (int i = 0; i < sysRoleList.size(); i++) {
					uids.add(sysRoleList.get(i).getUid());
				}
			}
		}
		return uids;
	}

	@RequestMapping(value = "del_role.do")
	@ResponseBody
	public Map<String, Object> delRole(String userid, String roleid) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> param = new HashMap<String, String>();
		if(StringUtils.isEmpty(userid)||StringUtils.isEmpty(roleid)){
			map.put("success", "0");
			map.put("msg", "请选择要处理的数据！");
			return map;
		}
		try {
			param.put("userId", userid);
			param.put("roleId", roleid);
			int result = sysUserService.deleteRoleByUserIdAndRoleId(param);
			map.put("success", "1");
			map.put("msg", "删除成功!");
			return map;
		}catch(Exception e){
			map.put("success", "0");
			map.put("msg", "出错了！");
			return map;
		}
	}

	/**
	 * 
	 * @MethodName: toAdd
	 * @Description: 跳转到添加角色页面
	 * @param model
	 * @param request
	 * @return
	 *
	 * @Author: Administrator
	 * @Create Date: 2014-12-10 下午1:43:36
	 */
	@RequestMapping(value = "/role.do")
	public String toRole(ModelMap model, HttpServletRequest request) {
		commonData(request, model);
		return "system/user/add_role";
	}

	/**
	 * 
	 * @MethodName: toAdd
	 * @Description: 添加用户页面
	 * @param model
	 * @param request
	 * @return
	 *
	 * @Author: liups
	 * @Create Date: 2014-12-10 下午1:53:06
	 */
	@RequestMapping(value = "/add.do")
	public String toAdd(ModelMap model, HttpServletRequest request) {
		commonData(request, model);
		Admin admin = MarketUtils.getAdmin(request);
		String code = SysParamConstant.XBM;
		String xbOption = SysParamHelper.getCodeOption(admin.getSupplier().getUid(), code, "", false);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplierId", admin.getSupplier().getUid());
		List<SysRole> roleList = sysRoleService.findByMap(param);
		model.put("roleList", roleList);
		model.put("xbOption", xbOption);
		return "system/user/user_add";
	}

	/**
	 * 
	 * @MethodName: doAdd
	 * @Description:添加用户及角色
	 * @param sysUser
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 *
	 * @Author:liu
	 * @Create Date: 2014-11-19 下午3:39:39
	 */
	@RequestMapping(value = "/save.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> doAdd(SysUser sysUser,String roleIds, ModelMap model, String Ubirthday) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<String> roleIdsList = null;
			String[] roleStrs = null;
			if (StringUtils.isNotEmpty(roleIds)) {
				roleStrs = roleIds.split(",");
				roleIdsList = new ArrayList<String>();
				for (String roleId : roleStrs) {
					if (!roleIdsList.contains(roleId) && StringUtils.isNoneEmpty(roleId)) {
						roleIdsList.add(roleId);
					}
				}
			}
			Date birthday = com.voidwhile.core.utils.DateUtils.parseDate(Ubirthday);
			sysUser.setBirthday(birthday);
			// 新增
			if (sysUser.getUid()==null) {
				if (StringUtils.isEmpty(sysUser.getSupplierId())) {
					map.put("status", false);
					map.put("message", "获取企业参数出错!");
					return map;
				}
				SysUser sysU = sysUserService.getBySupplierIdAndUserName(sysUser.getSupplierId(), sysUser.getUserName());
				if (sysU != null) {
					map.put("status", false);
					map.put("message", "用户名(<b>" + sysU.getUserName() + "</b>)存在!");
					return map;
				}
				sysUser.setIsSystem("0");
				sysUser.setStatus("1");
				sysUser.setCreateTime(new Date());
				sysUser.setUpdateTime(new Date());
				sysUserService.save(sysUser, roleIdsList);
			} else {
				// 修改
				sysUser.setUpdateTime(new Date());
				sysUserService.update(sysUser, roleIdsList);
			}
			map.put("status", true);
			map.put("message", "保存成功!");
			return map;
		} catch (Exception e) {
			map.put("message", "出错啦!" + e.getMessage());
			return map;
		}
	}

	@RequestMapping(value = "/edit.do", method = RequestMethod.GET)
	public String toEdit(ModelMap model, String uid, HttpServletRequest request) {
		commonData(request, model);
		List<SysRole> sysRoleList = sysRoleService.getByUserId(uid);
		SysUser sysUser = sysUserService.getById(uid);
		model.addAttribute("sysRoleList", sysRoleList);
		if (!sysRoleList.isEmpty()) {
			StringBuffer sb = new StringBuffer();
			for (SysRole entity : sysRoleList) {
				sb.append(entity.getUid() + ",");
			}
			String roleIds = sb.toString();
			roleIds.substring(0, roleIds.length() - 1);
			model.put("roleIds",roleIds);
		}
		// 查询角色
		Map<String, Object> param = new HashMap<String, Object>();
		Admin admin = MarketUtils.getAdmin(request);
		param.put("supplierId", admin.getSupplier().getUid());
		List<SysRole> roleList = sysRoleService.findByMap(param);
		model.put("roleList", roleList);
		String code = SysParamConstant.XBM;
		String xbOption = SysParamHelper.getCodeOption(sysUser.getSupplierId(), code, sysUser.getGender(), false);
		model.put("xbOption", xbOption);
		model.put("sysUser", sysUser);
		return "system/user/user_edit";
	}

	@RequestMapping(value = "/del.do")
	@ResponseBody
	public Map<String, Object> del(String[] ids) throws IOException {
		Map<String, Object> rltMap = new HashMap<String, Object>();
		if (ids == null || ids.length == 0) {
			rltMap.put("success", "0");
			rltMap.put("msg", "请选择要处理的数据！");
			return rltMap;
		}
		try {
			sysUserService.deleteByIds(ids);
			rltMap.put("success", "1");
			rltMap.put("msg", "删除成功!");
			return rltMap;
		} catch (Exception e) {
			rltMap.put("success", "0");
			rltMap.put("msg", "出错了！" + e.getMessage());
			return rltMap;
		}
	}

	@RequestMapping("/v_empower.do")
	public String toEmpower(HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		return "system/user/empower";
	}

	@RequestMapping("/v_chanceRole.do")
	public String toChanceRole(HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		return "system/user/chance_role";
	}


	@RequestMapping("/index_password_edit.do")
	public String UpdatePwd(HttpServletRequest request, ModelMap model) {
		commonData(request, model);
		return "system/user/index_password_edit";
	}
	
	/**
	 * 
	 * @author hekang
	 * @param request
	 * @param uid
	 * @param model
	 * @return map
	 */
	@RequestMapping(value = "/updatepwd.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> updatePwd(HttpServletRequest request,String uid,ModelMap model){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Admin admin=MarketUtils.getAdmin(request);
			String password = request.getParameter("oldpwd");
			String newpwd = request.getParameter("newpwd");
			SysUser sysUser = sysUserService.getById(admin.getUser().getUid()+"");
			if(password.equals(sysUser.getPlainPassword())){
				sysUser.setPlainPassword(newpwd);
				sysUserService.update(sysUser);
				map.put("status", true);
				map.put("code", 0);
				map.put("message", "保存成功！");
				System.out.println(map.toString());
			}else{
				map.put("status", false);
				map.put("code", 1);
				map.put("message", "原始密码不正确！");
				System.out.println(map.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@RequestMapping(value = "/indexEdit.do", method = RequestMethod.GET)
	public String indexUserEdit(ModelMap model, String uid, HttpServletRequest request) {
		commonData(request, model);
		SysUser sysUser = sysUserService.getById(uid);
		String code = SysParamConstant.XBM;
		String xbOption = SysParamHelper.getCodeOption(sysUser.getSupplierId(), code, sysUser.getGender(), false);
		model.put("xbOption", xbOption);
		model.put("sysUser", sysUser);
		return "system/user/index_user_edit";
	}
	
}
