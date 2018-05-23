package com.voidwhile.system.service;

import java.util.List;

import com.voidwhile.core.IBaseService;
import com.voidwhile.system.entity.Job;

public interface JobService extends IBaseService<Job> {

	/**
	 * 获取待执行工作
	 * 
	 * @return
	 */
	List<Job> execList();

	/**
	 * 更新下次执行时间
	 * 
	 * @param jobId
	 */
	void updateExecTime(Long jobId);

	/**
	 * 启用
	 * 
	 * @param jobId
	 */
	void start(Long jobId);

	/**
	 * 停止
	 * 
	 * @param jobId
	 */
	void stop(Long jobId);
}
