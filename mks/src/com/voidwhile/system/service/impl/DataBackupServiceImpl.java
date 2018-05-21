package com.voidwhile.system.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.Constant;
import com.voidwhile.common.utils.Command;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.constant.SysConstant;
import com.voidwhile.system.entity.DataBackup;
import com.voidwhile.system.mapper.DataBackupMapper;
import com.voidwhile.system.service.DataBackupService;
import com.voidwhile.system.service.JobService;


@Service
public class DataBackupServiceImpl implements DataBackupService {

	@Autowired
	private DataBackupMapper mapper;
	
	@Autowired
	private JobService jobService;
	
	@Override
	public void save(DataBackup entity) throws DataAccessException {
		mapper.insertSelective(entity);
	}

	@Override
	public void update(DataBackup entity) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Integer.parseInt(id));
	}

	@Override
	public DataBackup getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Integer.parseInt(id));
	}

	@Override
	public PageResult<DataBackup> findPageData(Map<String, Object> param, int pageNo, int pageSize,
			String orderByClause) throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<DataBackup> pageResult = new PageResult<DataBackup>(pageNo, pageSize);
		pageResult.setTotal(this.countByMap(param));

		param.put("offset", pageResult.getOffset());
		param.put("pageSize", pageResult.getPageSize());
		if (!"".equals(StringUtils.trimToEmpty(orderByClause))) {
			param.put("orderByClause", orderByClause);
		}
		pageResult.setList(this.findByMap(param));
		return pageResult;
	}

	@Override
	public List<DataBackup> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

	@Override
	public DataBackup getLastFullBackup() {
		return mapper.getLastFullBackup();
	}

	@Override
	public void save(Long userId, Integer baseId,Integer optType, boolean full) throws Exception{
		String dirPath = "";//备份目录
		String newBackup = "";//新备份
		DataBackup entity = new DataBackup();
		entity.setOptType(optType);
		entity.setBackupDate(new Date());
		entity.setUserId(userId);
		if (full) {
			dirPath = Constant.BACKUP_FULLBACKUPPATH;
			entity.setBackupRange(1);
		} else {
			dirPath = Constant.BACKUP_INCBACKUPPATH;
			entity.setBaseId(baseId);
			entity.setBackupRange(2);
		}
		
		File dir = new File(dirPath+"last");
		for(String name : dir.list()) {
			newBackup = name;
		}
		entity.setTitle(newBackup);
		entity.setBackupPath(dirPath+newBackup);
		entity.setDescription("System Automatic Backup");
		save(entity);
		
		//先把最后一次全量备份迁移到全量备份目录
		StringBuffer command = new StringBuffer("mv ").append(dirPath).append("last/").append(newBackup).append(" ").append(dirPath);
		Command.exec(command.toString());
	}

	@Override
	public int getOptType() {
		return mapper.getOptType();
	}

	@Override
	public void updateOptType(Integer optType) {
		mapper.updateOptType(optType);
		if (optType==1) {
			jobService.start(1l);
		} else if (optType==2) {
			jobService.stop(1l);
		}
	}

}
