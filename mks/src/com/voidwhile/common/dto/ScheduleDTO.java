package com.voidwhile.common.dto;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 我的日程 数据对象
 * @author: xiaowei
 * @Create Date: 2014年12月20日 下午4:59:42
 *
 * @Version: v1.0
 */
public class ScheduleDTO {

	/**
	 * @Fields day : 日期
	 */
	private String day;
	/**
	 * @Fields plannum : 工作计划数量
	 */
	private Integer plannum;

	/**
	 * @Fields summarynum : 工作总结数量
	 */
	private Integer summarynum;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Integer getPlannum() {
		return plannum;
	}

	public void setPlannum(Integer plannum) {
		this.plannum = plannum;
	}

	public Integer getSummarynum() {
		return summarynum;
	}

	public void setSummarynum(Integer summarynum) {
		this.summarynum = summarynum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleDTO other = (ScheduleDTO) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		return true;
	}

}
