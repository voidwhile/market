package com.voidwhile.market.mapper;

import java.util.List;
import java.util.Map;

import com.voidwhile.market.entity.MbeAddress;

public interface MbeAddressMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_mbe_address
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long addrId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_mbe_address
	 * @mbg.generated
	 */
	int insert(MbeAddress record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_mbe_address
	 * @mbg.generated
	 */
	int insertSelective(MbeAddress record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_mbe_address
	 * @mbg.generated
	 */
	MbeAddress selectByPrimaryKey(Long addrId);
	
	MbeAddress selectDefault(Long memberId);
	
	void setDefault(Long memberId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_mbe_address
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(MbeAddress record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table mk_mbe_address
	 * @mbg.generated
	 */
	int updateByPrimaryKey(MbeAddress record);

	List<MbeAddress> selectByMap(Map<String, Object> param);
	
	
}