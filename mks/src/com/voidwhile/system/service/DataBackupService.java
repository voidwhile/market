package com.voidwhile.system.service;

import java.io.IOException;

import com.voidwhile.market.service.IBaseService;
import com.voidwhile.system.entity.DataBackup;


public interface DataBackupService extends IBaseService<DataBackup> {

	/**
	 * 获取最后一次全量备份
	 * 
	 * @return
	 */
	public DataBackup getLastFullBackup();

	/**
	 * 保存备份
	 * 
	 * @param baseId
	 *            基础备份主键
	 * @param full
	 *            是否全量备份
	 * @throws IOException
	 */
	public void save(Long userId, Integer baseId,Integer optType, boolean full) throws Exception;

	/**
	 * 获取数据备份操作类型
	 * 
	 * @return
	 */
	int getOptType();

	/*
	 * 修改数据备份操作类型
	 * 
	 * @param optType
	 */
	void updateOptType(Integer optType);
}
