package com.voidwhile.system.bean;

import com.voidwhile.common.web.Constants;

/**
 * 页面分页属性Bean
 * 
 * @author xiaowei
 *
 */
public class PageBean {
	/**
	 * 当前页码
	 */
	private int page;

	/**
	 * 每页显示数
	 */
	private int rows;
	/**
	 * app每页显示数
	 */
	private int approws;
	/**
	 * 排序字段
	 */
	private String sort;
	
	/**
	 * 排序顺序
	 */
	private String order;

	public int getPage() {
		if (this.page <= 0) {
			this.page = 1;
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		if (rows <= 0) {
			this.rows = Constants.PAGE_SIZE;
		}
		return rows;
	}

	public void setRows(int pagesize) {
		this.rows = pagesize;
	}

	public int getApprows() {
		if (approws <= 0) {
			this.approws = Constants.APP_PAGE_SIZE;
		}
		return approws;
	}

	public void setApprows(int approws) {
		this.approws = approws;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sortField) {
		this.sort = sortField == null ? null : sortField.trim();;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String sortOrder) {
		this.order = sortOrder == null ? null : sortOrder.trim();
	}

	public String getOrderByClause(){
		if(sort!=null && !"".equals(sort)){
			String orderByClause = "";
			if(order!=null && !"".equals(order)){
				orderByClause = sort + " " + order;
			} else {
				orderByClause = sort;
			}
			return orderByClause;
		}else {
			return "";
		}
	}
}
