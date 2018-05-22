
package com.voidwhile.common.dto;

/**
 * @desc 该公司下的所有部门
 * @author hekang
 * @date 2017/02/13
 *
 */
public class DeptDTO {

	private String pid;//父id
	private String id;//部门id
	private String supplierId;//企业id
	private String orgName;//部门名称
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
}
