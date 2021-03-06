package com.voidwhile.market.mapper;

import java.util.List;
import java.util.Map;

import com.voidwhile.market.entity.CmdCommodity;

public interface CmdCommodityMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_commodity
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long cmdId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_commodity
	 * @mbg.generated
	 */
	int insert(CmdCommodity record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_commodity
	 * @mbg.generated
	 */
	int insertSelective(CmdCommodity record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_commodity
	 * @mbg.generated
	 */
	CmdCommodity selectByPrimaryKey(Long cmdId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_commodity
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(CmdCommodity record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_commodity
	 * @mbg.generated
	 */
	int updateByPrimaryKeyWithBLOBs(CmdCommodity record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_commodity
	 * @mbg.generated
	 */
	int updateByPrimaryKey(CmdCommodity record);

	void deleteByIds(String[] ids);
	
	List<CmdCommodity> selectByMap(Map<String, Object> param);
	
	int countByMap(Map<String, Object> param);
	
	List<CmdCommodity> findOption();
}