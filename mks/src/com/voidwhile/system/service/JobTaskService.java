package com.voidwhile.system.service;

import java.util.List;

import com.voidwhile.market.service.IBaseService;
import com.voidwhile.system.entity.JobTask;


public interface JobTaskService extends IBaseService<JobTask> {
	
	/**
	 * 获取待执行任务列表
	 * @return 任务列表
	 */
	public List<JobTask> execList();
	
	/**
	 * 创建新任务
	 * @param jobId
	 */
	public void createTask(Long jobId);
}
