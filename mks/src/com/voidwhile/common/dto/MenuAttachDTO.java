package com.voidwhile.common.dto;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 菜单附加操作权限 DTO
 * @Create Date: 2014年12月29日 下午2:47:11
 *
 * @Version: v1.0
 */
public class MenuAttachDTO {

	/**
	 * @Fields methodName : 操作权限代码
	 */
	private String methodCode;

	/**
	 * @Fields methodName : 操作权限中文名称
	 */
	private String methodName;

	public String getMethodCode() {
		return methodCode;
	}

	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode == null ? null : methodCode.trim();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName == null ? null : methodName.trim();
	}

}
