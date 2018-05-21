package com.voidwhile.system.bean;

import java.util.List;

import com.voidwhile.common.web.Constants;

public class PageResult<T> {
	/**
	 * 页码
	 */
	private int pageNo;

	/**
	 * 页码总数
	 */
	private int pageCount;
	/**
	 * 总数
	 */
	private int total;
	/**
	 * 偏移
	 */
	private int offset;

	/**
	 * 数量
	 */
	private int pageSize;

	/**
	 * 数据列表
	 */
	private List<T> list;

	public PageResult(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		if (this.pageNo <= 0) {
			this.pageNo = 1;
		}
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageCount() {
		this.pageCount = ((this.getTotal() - 1) / this.getPageSize()) + 1;
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getOffset() {
		this.offset = (this.getPageNo() - 1) * this.getPageSize();
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		if (pageSize <= 0) {
			this.pageSize = Constants.PAGE_SIZE;
		}
		return pageSize;
	}

	public void setPageSize(int rowNum) {
		this.pageSize = rowNum;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
