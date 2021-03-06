package com.voidwhile.market.entity;

import java.math.BigDecimal;

public class EveEventCmd {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_eve_event_cmd.event_cmd_id
     *
     * @mbg.generated
     */
    private Long eventCmdId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_eve_event_cmd.event_id
     *
     * @mbg.generated
     */
    private Long eventId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_eve_event_cmd.cmd_id
     *
     * @mbg.generated
     */
    private Long cmdId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_eve_event_cmd.event_cmd_id
     *
     * @return the value of mk_eve_event_cmd.event_cmd_id
     *
     * @mbg.generated
     */
    public Long getEventCmdId() {
        return eventCmdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_eve_event_cmd.event_cmd_id
     *
     * @param eventCmdId the value for mk_eve_event_cmd.event_cmd_id
     *
     * @mbg.generated
     */
    public void setEventCmdId(Long eventCmdId) {
        this.eventCmdId = eventCmdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_eve_event_cmd.event_id
     *
     * @return the value of mk_eve_event_cmd.event_id
     *
     * @mbg.generated
     */
    public Long getEventId() {
        return eventId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_eve_event_cmd.event_id
     *
     * @param eventId the value for mk_eve_event_cmd.event_id
     *
     * @mbg.generated
     */
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_eve_event_cmd.cmd_id
     *
     * @return the value of mk_eve_event_cmd.cmd_id
     *
     * @mbg.generated
     */
    public Long getCmdId() {
        return cmdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_eve_event_cmd.cmd_id
     *
     * @param cmdId the value for mk_eve_event_cmd.cmd_id
     *
     * @mbg.generated
     */
    public void setCmdId(Long cmdId) {
        this.cmdId = cmdId;
    }
    
    private String cmdName;
    
    private BigDecimal price;

	public String getCmdName() {
		return cmdName;
	}

	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
    
    
    
}