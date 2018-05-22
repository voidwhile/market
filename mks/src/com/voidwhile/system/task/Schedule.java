package com.voidwhile.system.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.voidwhile.common.utils.CommonUtils;
import com.voidwhile.core.utils.Logger;
import com.voidwhile.core.utils.Slf4JLogger;
import com.voidwhile.system.entity.Job;
import com.voidwhile.system.entity.JobTask;
import com.voidwhile.system.service.JobService;
import com.voidwhile.system.service.JobTaskService;


@Component
public class Schedule {
	private Logger log = Slf4JLogger.getLogger(Schedule.class);
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private JobTaskService jobTaskService;
	
	public synchronized void exec(){
		log.info("执行调度器");
		runJob();
		runTask();
	}
	
	private void runJob(){
		try {
			List<Job> jobs = jobService.execList();
			for(Job job : jobs) {
				jobTaskService.createTask(job.getJobId());
				jobService.updateExecTime(job.getJobId());
			}
		} catch (Exception e) {
			log.info("创建新任务异常！", e);
		}
	}
	
	private void runTask(){
		List<JobTask> tasks = jobTaskService.execList();
		for(JobTask task : tasks){
			try {
				Class<?> clazz = Class.forName(task.getInstance());
				Task t = (Task)clazz.newInstance();
				t.run();
				task.setTaskStatus(1);
				task.setResultMsg("执行成功");
			} catch (Exception e) {
				e.printStackTrace();
				log.info("任务执行异常", e);
				String err = CommonUtils.getStackTrace(e);
				int length = 3000;
				if (err.length()<length) {
					length = err.length();
				}
				task.setResultMsg("任务执行异常："+err.substring(0, length));
				task.setTaskStatus(2);
			}
			task.setDoneTime(new Date());
			jobTaskService.update(task);
		}
	}
	
	
	
}
