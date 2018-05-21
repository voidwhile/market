package com.voidwhile.system.entity;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 系统字典值 实体类
 * @author: xiaowei
 * @Create Date: 2014年10月24日 上午10:49:24
 *
 * @Version: v1.0
 */
public class SysParamItem {

	private String uid;

	/**
	 * @Fields paramId : 字典表ID
	 */
	private String paramId;

	/**
	 * @Fields itemCode : 字典内容代码
	 */
	private String itemCode;

	/**
	 * @Fields itemName : 字典内容名称
	 */
	private String itemName;

	/**
	 * @Fields rank : 排序
	 */
	private Integer rank;

	/**
	 * @Fields status : 状态：1可用，0不可用
	 */
	private String status;

	/**
	 * @Fields remark : 描述
	 */
	private String remark;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId == null ? null : paramId.trim();
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode == null ? null : itemCode.trim();
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName == null ? null : itemName.trim();
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}