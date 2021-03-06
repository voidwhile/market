package com.voidwhile.market.mapper;

import java.util.List;
import java.util.Map;

import com.voidwhile.market.entity.RepTransact;

public interface RepTransactMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_rep_transact
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long transId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_rep_transact
	 * @mbg.generated
	 */
	int insert(RepTransact record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_rep_transact
	 * @mbg.generated
	 */
	int insertSelective(RepTransact record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_rep_transact
	 * @mbg.generated
	 */
	RepTransact selectByPrimaryKey(Long transId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_rep_transact
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(RepTransact record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_rep_transact
	 * @mbg.generated
	 */
	int updateByPrimaryKey(RepTransact record);
	
	List<RepTransact> selectByMap(Map<String, Object> param);
	
	int countByMap(Map<String, Object> param);
}