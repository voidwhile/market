package com.voidwhile.common.jdbc;

import java.util.List;
import java.util.Map;

import com.voidwhile.common.dto.ScheduleDTO;

/**
 * CopyRright (c) 2017:
 * 
 * @Description: 我的日程数据库操作接口
 * @author: zhanzheng
 * @Create Date: 2014年12月20日 下午4:59:09
 *
 * @Version: v1.0
 */
public interface ScheduleDao {


	/**
	 * @MethodName: countUserSchedule
	 * @Description: 统计用户一个月的工作日程
	 * @param userId 用户ID
	 * @param month (格式yyyy-MM)
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月20日 下午5:02:29
	 */
	List<ScheduleDTO> countUserSchedule(String userId, Integer year, Integer month);

	/**
	 * @MethodName: countUserSummary
	 * @Description: 统计用户一个月的工作总结数量
	 * @param userId
	 * @param year
	 * @param month
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月30日 上午11:12:10
	 */
	public Map<String, Integer> countUserSummary(String userId, Integer year, Integer month);

	/**
	 * @MethodName: countUserPlan
	 * @Description: 统计用户一个月的工作计划数量
	 * @param userId
	 * @param year
	 * @param month
	 * @return
	 *
	 * @author: zhanzheng
	 * @Create Date: 2014年12月30日 上午11:12:42
	 */
	public Map<String, Integer> countUserPlan(String userId, Integer year, Integer month);
}
