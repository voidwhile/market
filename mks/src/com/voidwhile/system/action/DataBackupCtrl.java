package com.voidwhile.system.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.voidwhile.common.jdbc.Xtrabackup;
import com.voidwhile.common.web.MarketUtils;
import com.voidwhile.core.utils.Logger;
import com.voidwhile.core.utils.Slf4JLogger;
import com.voidwhile.system.bean.Admin;
import com.voidwhile.system.bean.PageBean;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.entity.DataBackup;
import com.voidwhile.system.service.DataBackupService;



@Controller
@RequestMapping("/admin/data")
public class DataBackupCtrl {

	private Logger log = Slf4JLogger.getLogger(DataBackupCtrl.class);

	@Autowired
	private DataBackupService service;

	/**
	 * 进入备份列表页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/list.do")
	public String list(Model model) {
		int optType = service.getOptType();
		model.addAttribute("optType", optType);
		return "system/data/list";
	}

	/**
	 * 获取备份列表
	 * @param page
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list_data.do")
	public Map<String, Object> listData(PageBean page) {
		log.info("/admin/data/list_data.do");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		try {
			PageResult<DataBackup> result = service.findPageData(param, page.getPage(), page.getRows(), "");
			map.put("rows", result.getList());
			map.put("total", result.getTotal());
		} catch (Exception e) {
			throw e;
		}
		return map;
	}

	/**
	 * 保存数据备份操作类型
	 * @param optType
	 * @return
	 */
	@RequestMapping("/saveOptType.do")
	@ResponseBody
	public Map<String, Object> saveOptType(Integer optType) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			service.updateOptType(optType);
			map.put("errCode", "0000");
		} catch (Exception e) {
			map.put("errCode", "1111");
			map.put("errMsg", e.getMessage());
		}
		
		return map;
	}
	
	/**
	 * 执行手动备份 
	 * @return
	 */
	@RequestMapping("/backup.do")
	@ResponseBody
	public Map<String, Object> backup(HttpServletRequest request){
		Admin admin = MarketUtils.getAdmin(request);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Xtrabackup.fullBackup();
			service.save(Long.valueOf(admin.getUser().getUid()),null,2, true);
			map.put("errCode", "0000");
		} catch (Exception e) {
			map.put("errCode", "1111");
			map.put("errMsg", e.getMessage());
		}
		return map;
	}
	
	
	/**
	 * 还原备份
	 * @param request
	 * @param backupId
	 * @return
	 */
	@RequestMapping("/copyBack.do")
	@ResponseBody
	public Map<String, Object> copyBack(HttpServletRequest request,Long backupId){
		log.info("/admin/data/copyBack/"+backupId);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			DataBackup backup = service.getById(String.valueOf(backupId));
			if (backup.getBackupRange() == 1) {
				Xtrabackup.copyBack(backup.getTitle(), null, true);
			} else if(backup.getBackupRange()==2) {
				Xtrabackup.copyBack(backup.getBaseDir().getTitle(),backup.getTitle(), false);
			}
			map.put("errCode", "0000");
		} catch (Exception e) {
			map.put("errCode", "1111");
			map.put("errMsg", "还原失败："+e.getMessage());
		}
		return map;
	}
	
	
}
