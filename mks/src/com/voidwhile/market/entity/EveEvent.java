package com.voidwhile.market.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EveEvent {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_eve_event.event_id
	 * @mbg.generated
	 */
	private Long eventId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_eve_event.title
	 * @mbg.generated
	 */
	private String title;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_eve_event.start_time
	 * @mbg.generated
	 */
	private Date startTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_eve_event.end_time
	 * @mbg.generated
	 */
	private Date endTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_eve_event.eve_status
	 * @mbg.generated
	 */
	private Integer eveStatus;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_eve_event.fcd
	 * @mbg.generated
	 */
	private Date fcd;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_eve_event.description
	 * @mbg.generated
	 */
	private String description;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_eve_event.event_id
	 * @return  the value of mk_eve_event.event_id
	 * @mbg.generated
	 */
	public Long getEventId() {
		return eventId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_eve_event.event_id
	 * @param eventId  the value for mk_eve_event.event_id
	 * @mbg.generated
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_eve_event.title
	 * @return  the value of mk_eve_event.title
	 * @mbg.generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_eve_event.title
	 * @param title  the value for mk_eve_event.title
	 * @mbg.generated
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_eve_event.start_time
	 * @return  the value of mk_eve_event.start_time
	 * @mbg.generated
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_eve_event.start_time
	 * @param startTime  the value for mk_eve_event.start_time
	 * @mbg.generated
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_eve_event.end_time
	 * @return  the value of mk_eve_event.end_time
	 * @mbg.generated
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_eve_event.end_time
	 * @param endTime  the value for mk_eve_event.end_time
	 * @mbg.generated
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_eve_event.eve_status
	 * @return  the value of mk_eve_event.eve_status
	 * @mbg.generated
	 */
	public Integer getEveStatus() {
		return eveStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_eve_event.eve_status
	 * @param eveStatus  the value for mk_eve_event.eve_status
	 * @mbg.generated
	 */
	public void setEveStatus(Integer eveStatus) {
		this.eveStatus = eveStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_eve_event.fcd
	 * @return  the value of mk_eve_event.fcd
	 * @mbg.generated
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getFcd() {
		return fcd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_eve_event.fcd
	 * @param fcd  the value for mk_eve_event.fcd
	 * @mbg.generated
	 */
	public void setFcd(Date fcd) {
		this.fcd = fcd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_eve_event.description
	 * @return  the value of mk_eve_event.description
	 * @mbg.generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_eve_event.description
	 * @param description  the value for mk_eve_event.description
	 * @mbg.generated
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	private String statusName;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}