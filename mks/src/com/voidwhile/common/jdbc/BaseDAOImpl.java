package com.voidwhile.common.jdbc;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.voidwhile.core.utils.Slf4JLogger;

/**
 * DAO基础类 用于对数据源的注入以及其它一些公用方法的定义
 * 
 * @author xiaowei
 * */
public abstract class BaseDAOImpl extends JdbcDaoSupport {
	private static final Slf4JLogger logger = Slf4JLogger.getLogger("com.voidwhile.common.jdbc");

	/**
	 * 向父类注入数据源
	 * */
	@Resource(name = "dataSource")
	private void setMyJdbcTemplate(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

}
