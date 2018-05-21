package com.voidwhile.common.taglib.authority;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 登陆用户菜单操作权限控制 TAG
 * 
 * @author: xiaowei
 * @Create Date: 2014年12月29日 上午9:49:32
 *
 * @Version: v1.0
 */
public class MenuAttributeManagerTag extends TagSupport {

	/**
	 * @Fields menuCode : 菜单code
	 */
	private String menuCode;

	/**
	 * @Fields type : 字段说明
	 */
	private String name;

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int doStartTag() throws JspException {
		HttpSession session = this.pageContext.getSession();

		if ("add".equals(name)) {
			return TagSupport.EVAL_BODY_INCLUDE;
		} else {
			return TagSupport.SKIP_BODY;
		}
	}

	@Override
	public int doEndTag() throws JspException {
		return TagSupport.EVAL_PAGE;
	}

}
