package com.voidwhile.system.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.voidwhile.common.action.BaseController;
import com.voidwhile.common.web.MarketUtils;
import com.voidwhile.system.bean.Admin;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.constant.SysConstant;
import com.voidwhile.system.entity.SysMenus;
import com.voidwhile.system.entity.SysRole;
import com.voidwhile.system.entity.SysUser;
import com.voidwhile.system.service.SysMenusService;
import com.voidwhile.system.service.SysRoleService;
/**
 * 
 * CopyRright (c) 2017: 
 * @Description: 角色菜单管理
 * @author: liu
 * @Create Date: 2014-11-19 下午3:52:05 
 *
 * @Version: v1.0
 */
@Controller
@RequestMapping("/admin/role")
public class SysRoleController extends BaseController{
	private Map<String, Object> param;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysMenusService sysMenusService;
    @RequestMapping("/list.do")
	public String list(HttpServletRequest request, ModelMap model){
			commonData(request, model);
		    return "system/role/list";
	}
    
    @RequestMapping("/roleList.do")
    @ResponseBody
    public Map<String, Object> roleList(HttpServletRequest request,String uid,ModelMap model){
    	Map<String, Object> map=new HashMap<String, Object>();
    	Admin admin=MarketUtils.getAdmin(request);
    	if(param==null){
    		param=new HashMap<String, Object>();
    	}
    	try {
    		param.put("supplierId", admin.getSupplier().getUid());	
    		String orderByClause = "level asc";
        	param.put("orderByClause", orderByClause);
        	List<SysRole> result=sysRoleService.findByMap(param);
        	 List<SysRole> tmpList=new ArrayList<SysRole>();
        	//过滤有该用户的角色
        	if(StringUtils.isNotEmpty(uid)){
        		List<SysRole> sysRoleList = sysRoleService.getByUserId(uid);
        		  for(SysRole role:sysRoleList){
      	 	    	for(int i=0;i<result.size();i++){
      	 	    			if(role.getUid().equals(result.get(i).getUid()))
      	 	    			{
      	 	    				tmpList.add(result.get(i));
      	 	    				break;
      	 	    			}
      	 	    	}
      	 	    }
        		result.removeAll(tmpList);	
        	}

        	map.put("rows", result);
        	map.put("total", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
    }
    /**
     * 
     * @MethodName: toAllot 
     * @Description: 分配菜单页面
     * @param request
     * @param model
     * @return
     *
     * @Author: Administrator
     * @Create Date: 2014-12-8 上午11:01:45
     */
    @RequestMapping("/v_allot.do")
    public String toAllot(HttpServletRequest request,ModelMap model){
    	commonData(request, model);
    	return "system/role/allot_menu";
    }
    /**
     * 
     * @MethodName: toAllotUser 
     * @Description: 分配用户页面
     * @param request
     * @param model
     * @return
     *
     * @Author: Administrator
     * @Create Date: 2014-12-8 下午2:28:17
     */
    @RequestMapping("/toAllotUser.do")
    public String toAllotUser(HttpServletRequest request,ModelMap model,String roleId){
    	commonData(request, model);
    	return "system/role/allot_user";
    }
    /**
     * 
     * @MethodName: listUserData 
     * @Description: 获取角色用户
     * @param page
     * @param request
     * @param roleId
     * @return
     *
     * @Author: liups
     * @Create Date: 2014-12-5 下午2:27:51
     */
    @RequestMapping("/list_ud.do")
    @ResponseBody
    public Map<String, Object> listUserData(PageBean page,HttpServletRequest request,String roleId){
    	    Map<String, Object> map = new HashMap<String, Object>();
    	    PageResult<SysRole> result = null;
    	    try {
    	    Admin admin=MarketUtils.getAdmin(request);
    	    List<String> uids=this.getUids(admin, roleId);
    		String orderByClause = "create_time desc";
    		Map<String, Object> params=new HashMap<String, Object>();
    		params.put("uids", uids);
    		params.put("orderByClause", orderByClause);
    		if(StringUtils.isNotEmpty(roleId)){
    			result=sysRoleService.getUserByRoleId(params, page.getPage(), page.getRows(), orderByClause);
    		}
			} catch (Exception e) {
				e.printStackTrace();
			}
    	    if(result==null){
    	    	result=new PageResult<SysRole>(page.getPage(), page.getRows());
    	    }
			map.put("rows", result.getList());
			map.put("total", result.getTotal());
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
    private List<String> getUids(Admin admin,String roleId){
		param = new HashMap<String, Object>();
		param.put("supplierId", admin.getSupplier().getUid());
		List<String> uids=new ArrayList<String>();
		if(StringUtils.isNotEmpty(roleId)){
			uids.add(roleId);
		}else{
			List<SysRole> sysRoleList=sysRoleService.findByMap(param);
    		if(!sysRoleList.isEmpty()){
    			for (int i = 0; i < sysRoleList.size(); i++) {
    			 uids.add(sysRoleList.get(i).getUid());	
    			}	
    		}	
		}
		return uids;
    }
    /**
     * 
     * @MethodName: list_t 
     * @Description: 获取企业内部角色树
     * @param request
     * @param response
     * @return
     *
     * @Author: liups
     * @Create Date: 2014-12-5 上午9:24:43
     */
    @RequestMapping("/tree.do")
    @ResponseBody
    public List<Map<String, Object>> list_t(HttpServletRequest request,HttpServletResponse response){
    	List<Map<String, Object>> rlt = new ArrayList<Map<String, Object>>();   
    	try{
			param = new HashMap<String, Object>();
    		String orderByClause = "create_time desc";
    		Admin admin=MarketUtils.getAdmin(request);
    		List<SysRole> paramList=null;
			param.put("orderByClause", orderByClause);
    		param.put("supplierId", admin.getSupplier().getUid());
    		
    		paramList=sysRoleService.findByMap(param);
    		if(!paramList.isEmpty()){
    			for(SysRole it:paramList){
    				Map<String, Object> r = new HashMap<String, Object>();
    				r.put("id", it.getUid());
    				r.put("text",it.getRoleName());
    				rlt.add(r);
    			}
    		}
            } catch (Exception e) {
            	Map<String, Object> map = new HashMap<String, Object>();
    			map.put("text", "加载数据出错了");
    			rlt.add(map);
            }
            return rlt;
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
		return "system/role/add";
	}
  /**
   * 
   * @MethodName: doAdd 
   * @Description: 添加角色
   * @param sysRole
   * @param request
   * @return
   *
   * @Author: liups
   * @Create Date: 2014-11-19 下午5:14:09
   */
  @RequestMapping(value="/save.do")
  @ResponseBody
  public Map<String, Object> doAdd(SysRole sysRole,HttpServletRequest request){
	  Map<String, Object> map=new HashMap<String, Object>();
	  try {
		//新增
		if(StringUtils.isEmpty(sysRole.getUid())){
			if (StringUtils.isEmpty(sysRole.getSupplierId())) {
				map.put("status", false);
				map.put("message", "参数出错!");
				return map;
			}
			SysRole role=sysRoleService.getBySupplierIdAndRoleCode(sysRole.getSupplierId(), sysRole.getRoleCode());
			if(role!=null){
				map.put("status", false);
				map.put("message", "角色编码(<b>"+sysRole.getRoleCode()+"</b>)存在!");
				return map;
			}
			sysRole.setCreateTime(new Date());
			sysRole.setUpdateTime(new Date());
			sysRole.setIsSystem("0");
			sysRoleService.save(sysRole);
		}else{
			//修改
			sysRole.setUpdateTime(new Date());
			sysRoleService.update(sysRole);
		}
		map.put("status", true);
		map.put("message", "保存成功!");
		return map;
	} catch (Exception e) {
		map.put("message", "出错啦！"+e.getMessage());
		return map;
	}
	  
  }
 /**
  * 
  * @MethodName: doEdit 
  * @Description: 跳转到编辑页面 
  * @param uid
  * @param request
  * @param model
  * @return
  *
  * @Author: liups
  * @Create Date: 2014-12-9 下午5:10:27
  */
  @RequestMapping(value="/v_edit.do")
  public String doEdit(String uid, HttpServletRequest request, ModelMap model){
	  commonData(request, model);
	  if(StringUtils.isEmpty(uid)){
		  throw new RuntimeException("参数错误！");
	  }
	  SysRole param=sysRoleService.getById(uid);
	  String isAppLogin = param.getIsAppLogin();
	  model.addAttribute("model", param);
	  model.addAttribute("isAppLogin" , isAppLogin);
	  return "system/role/edit";
  }
  /**
   * 
   * @MethodName: allotUser 
   * @Description:给角色分配用户
   * @param roleIds
   * @param userId
   * @return
   *
   * @Author: liups
   * @Create Date: 2014-12-26 下午2:23:22
   */
  @RequestMapping("/allotUser.do")
  @ResponseBody
  public Map<String,Object> allotUser(String roleIds,String userId){
	  Map<String, Object> rltMap = new HashMap<String, Object>();
	  if(StringUtils.isEmpty(roleIds)||StringUtils.isEmpty(userId)){
		  rltMap.put("success", "0");
		  rltMap.put("msg", "参数获取失败!");
		  return rltMap;
	  }
	  String[] rs=roleIds.split(",");
		try {
			  for(String rId:rs){
				  Map<String, Object> param=new HashMap<String, Object>();
				  param.put("userId", userId);
				  param.put("roleId", rId);
				  sysRoleService.deleteRoleUser(param);
				  sysRoleService.insertRoleUser(param);  
			  }
			  rltMap.put("success", "1");
			  rltMap.put("msg", "角色分配用户成功!");
		} catch (Exception e) {
			rltMap.put("success", "0");
			rltMap.put("msg", "出错了！" + e.getMessage());
		}
	  return rltMap;
  }
  /**
   * 
   * @MethodName: allotRole 
   * @Description: 给用户分配角色
   * @param roleId
   * @param userIds
   * @return
   *
   * @Author: liups
   * @Create Date: 2014-12-26 下午2:22:19
   */
  @RequestMapping("/allotRole.do")
  @ResponseBody
  public Map<String,Object> allotRole(String roleId,String userIds){
	  Map<String, Object> rltMap = new HashMap<String, Object>();
	  if(StringUtils.isEmpty(userIds)||StringUtils.isEmpty(roleId)){
		  rltMap.put("success", "0");
		  rltMap.put("msg", "参数获取失败!");
		  return rltMap;
	  }
	  String[] us=userIds.split(",");
	  try {
		  for(String uId:us){
			  Map<String, Object> param=new HashMap<String, Object>();
			  param.put("userId", uId);
			  param.put("roleId", roleId);
			  sysRoleService.deleteRoleUser(param);
			  sysRoleService.insertRoleUser(param);  
		  }
		  rltMap.put("success", "1");
		  rltMap.put("msg", "角色分配用户成功!");
	  } catch (Exception e) {
		  rltMap.put("success", "0");
		  rltMap.put("msg", "出错了！" + e.getMessage());
	  }
	  return rltMap;
  }
  
  @RequestMapping("/allotMenu.do")
  @ResponseBody
  public Map<String,Object> allotMenu(String roleId,String menuIds){
	  Map<String, Object> rltMap = new HashMap<String, Object>();
	  if(StringUtils.isEmpty(menuIds)||StringUtils.isEmpty(roleId)){
		  rltMap.put("success", "0");
		  rltMap.put("msg", "参数获取失败!");
		  return rltMap;
	  }
		try {
			  sysRoleService.allotMenus(roleId, menuIds);
			  rltMap.put("success", "1");
			  rltMap.put("msg", "角色分配菜单成功!");
		} catch (Exception e) {
			rltMap.put("success", "0");
			rltMap.put("msg", "出错了！" + e.getMessage());
		}
	  return rltMap;
  }
  
  @RequestMapping(value = "/del.do")
  @ResponseBody
  public Map<String, Object> delete(String[] ids, HttpServletRequest request) {
	  Map<String, Object> rltMap = new HashMap<String, Object>();
	  if(ids==null||ids.length==0){
		  rltMap.put("success", "0");
		  rltMap.put("msg", "请选择要处理的数据！");
		  return rltMap;
	  }
	  if (ids != null && ids.length > 0) {
			for (String roleId : ids) {
				SysRole role=sysRoleService.getById(roleId);
				if(role!=null){
					if("1".equalsIgnoreCase(role.getIsSystem())){
						rltMap.put("success", "0");
						rltMap.put("msg", "系统默认的角色不能删除!");
						return rltMap;
					}	
				}
			}
		}
	  try {
		sysRoleService.deleteByIds(ids);
		rltMap.put("success", "1");
		rltMap.put("msg", "删除成功!");
	} catch (Exception e) {
		rltMap.put("success", "0");
		rltMap.put("msg", "出错了！" + e.getMessage());
	}
	  return rltMap;
  }
}
