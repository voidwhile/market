package com.voidwhile.core.jdbc.sql;

/**
 * 分页处理接口
 * 
 * @author lxy
 * */
public interface SQLPageHandle {

	/**
	 * 将传入的SQL做分页处理
	 * 
	 * @param String
	 *            oldSql 原SQL
	 * @param int first 开始记录数 这个值由（page-1）*pagesize得到
	 * @param int pagesize 每页数量
	 * */
	public String handlerPagingSQL(String oldSql, int first, int pagesize);


}
