package com.voidwhile.core.jdbc.sql;


/**
 * oracle分页实现
 * 
 * @author xiaowei
 * */
public class OracleSQLPageHandleImpl implements SQLPageHandle {

	public String handlerPagingSQL(String oldSQL, int firstResult, int pageSize) {
		// 根据不同的数据库，调用不同的实现
		if (pageSize > 0) {
			int endResult = firstResult + pageSize;

			if (firstResult <= 0) {
				oldSQL = "select * from (" + oldSQL + ") where rownum<="
						+ endResult;
			} else {
				oldSQL = " select row_.*,rownum rownum_ from (" + oldSQL
						+ ") row_ where rownum<=" + endResult;
				oldSQL = "select * from (" + oldSQL + ") where rownum_>"
						+ firstResult;
			}
		}
		return oldSQL;
	}

}
