package com.voidwhile.market.mapper;

import java.util.List;
import java.util.Map;

import com.voidwhile.market.entity.EveEventCmd;

public interface EveEventCmdMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mk_eve_event_cmd
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long eventCmdId);
    int deleteByEventId(Long eventId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mk_eve_event_cmd
     *
     * @mbg.generated
     */
    int insert(EveEventCmd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mk_eve_event_cmd
     *
     * @mbg.generated
     */
    int insertSelective(EveEventCmd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mk_eve_event_cmd
     *
     * @mbg.generated
     */
    EveEventCmd selectByPrimaryKey(Long eventCmdId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mk_eve_event_cmd
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EveEventCmd record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mk_eve_event_cmd
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EveEventCmd record);

	List<EveEventCmd> findByMap(Map<String, Object> param);

	int countByMap(Map<String, Object> param);
}