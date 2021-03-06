package com.voidwhile.market.mapper;

import java.util.List;
import java.util.Map;

import com.voidwhile.market.entity.EveEvent;

public interface EveEventMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_eve_event
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long eventId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_eve_event
	 * @mbg.generated
	 */
	int insert(EveEvent record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_eve_event
	 * @mbg.generated
	 */
	int insertSelective(EveEvent record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_eve_event
	 * @mbg.generated
	 */
	EveEvent selectByPrimaryKey(Long eventId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_eve_event
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(EveEvent record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_eve_event
	 * @mbg.generated
	 */
	int updateByPrimaryKeyWithBLOBs(EveEvent record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_eve_event
	 * @mbg.generated
	 */
	int updateByPrimaryKey(EveEvent record);
	
	List<EveEvent> findByMap(Map<String, Object> param);
	
	List<EveEvent> findEvent();
	
	int countByMap(Map<String, Object> param);
	
	void deleteByIds(String[] ids);
}