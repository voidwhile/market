package com.voidwhile.core.jdbc.sql;



/**
 * mysql数据库的分页实现
 * 
 * @author xiaowei
 * */
public class MysqlSQLPageHandleImpl implements SQLPageHandle {

	public String handlerPagingSQL(String oldSQL, int firstResult, int pageSize) {
		StringBuffer sql = new StringBuffer(oldSQL);
		if (pageSize > 0) {
			if (firstResult <= 0) {
				sql.append(" limit ").append(pageSize);
			} else {
				sql.append(" limit ").append(firstResult).append(",")
						.append(pageSize);
			}
		}
		return sql.toString();
	}

}
