package com.voidwhile.market.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OdrCart {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_odr_cart.cart_id
	 * @mbg.generated
	 */
	private Long cartId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_odr_cart.oc_member_id
	 * @mbg.generated
	 */
	private Long ocMemberId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_odr_cart.oc_cmd_id
	 * @mbg.generated
	 */
	private Long ocCmdId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_odr_cart.add_time
	 * @mbg.generated
	 */
	private Date addTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_odr_cart.num
	 * @mbg.generated
	 */
	private Integer num;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_odr_cart.cart_id
	 * @return  the value of mk_odr_cart.cart_id
	 * @mbg.generated
	 */
	public Long getCartId() {
		return cartId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_odr_cart.cart_id
	 * @param cartId  the value for mk_odr_cart.cart_id
	 * @mbg.generated
	 */
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_odr_cart.oc_member_id
	 * @return  the value of mk_odr_cart.oc_member_id
	 * @mbg.generated
	 */
	public Long getOcMemberId() {
		return ocMemberId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_odr_cart.oc_member_id
	 * @param ocMemberId  the value for mk_odr_cart.oc_member_id
	 * @mbg.generated
	 */
	public void setOcMemberId(Long ocMemberId) {
		this.ocMemberId = ocMemberId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_odr_cart.oc_cmd_id
	 * @return  the value of mk_odr_cart.oc_cmd_id
	 * @mbg.generated
	 */
	public Long getOcCmdId() {
		return ocCmdId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_odr_cart.oc_cmd_id
	 * @param ocCmdId  the value for mk_odr_cart.oc_cmd_id
	 * @mbg.generated
	 */
	public void setOcCmdId(Long ocCmdId) {
		this.ocCmdId = ocCmdId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_odr_cart.add_time
	 * @return  the value of mk_odr_cart.add_time
	 * @mbg.generated
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_odr_cart.add_time
	 * @param addTime  the value for mk_odr_cart.add_time
	 * @mbg.generated
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_odr_cart.num
	 * @return  the value of mk_odr_cart.num
	 * @mbg.generated
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_odr_cart.num
	 * @param num  the value for mk_odr_cart.num
	 * @mbg.generated
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	
	private String cmdName;

	private String cmdCode;
	
	private BigDecimal price;
	
	private BigDecimal eventPrice;
	
	private String imgPath;


	public String getCmdName() {
		return cmdName;
	}

	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}

	public String getCmdCode() {
		return cmdCode;
	}

	public void setCmdCode(String cmdCode) {
		this.cmdCode = cmdCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getEventPrice() {
		return eventPrice;
	}

	public void setEventPrice(BigDecimal eventPrice) {
		this.eventPrice = eventPrice;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}