package com.voidwhile.market.mapper;

import java.util.List;
import java.util.Map;

import com.voidwhile.market.entity.CmdPrice;

public interface CmdPriceMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_price
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long priceId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_price
	 * @mbg.generated
	 */
	int insert(CmdPrice record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_price
	 * @mbg.generated
	 */
	int insertSelective(CmdPrice record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_price
	 * @mbg.generated
	 */
	CmdPrice selectByPrimaryKey(Long priceId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_price
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(CmdPrice record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_cmd_price
	 * @mbg.generated
	 */
	int updateByPrimaryKey(CmdPrice record);
	
	List<CmdPrice> selectByMap(Map<String, Object> param);
	
	int countByMap(Map<String, Object> param);
	
	void deleteByIds(String[] ids);
	
	int deleteByEventId(Long eventId);
	
	int deleteByCmdId(Long cmdId);
}