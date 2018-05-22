package com.voidwhile.common.dto;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 企业定位配置类
 * @author: xiaowei
 * @Create Date: 2014年12月15日 下午1:18:19
 *
 * @Version: v1.0
 */
public class PositionConfigDTO {

	/**
	 * @Fields positionType : 定位类型，1：手动，2：实时定位
	 */
	private String positionType;

	/**
	 * @Fields positionInterval : 实时定位 的定位时间间隔
	 */
	private String positionInterval;
	/**
	 * @Fields positionBegintime : 实时定位 的 定位开始时间
	 */
	private String positionBegintime;
	/**
	 * @Fields positionEndtime : 实时定位 的定位结束时间
	 */
	private String positionEndtime;

	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType == null ? null : positionType.trim();
	}

	public String getPositionInterval() {
		return positionInterval;
	}

	public void setPositionInterval(String positionInterval) {
		this.positionInterval = positionInterval == null ? null : positionInterval.trim();
	}

	public String getPositionBegintime() {
		return positionBegintime;
	}

	public void setPositionBegintime(String positionBegintime) {
		this.positionBegintime = positionBegintime == null ? null : positionBegintime.trim();
	}

	public String getPositionEndtime() {
		return positionEndtime;
	}

	public void setPositionEndtime(String positionEndtime) {
		this.positionEndtime = positionEndtime == null ? null : positionEndtime.trim();
		
	}

}
