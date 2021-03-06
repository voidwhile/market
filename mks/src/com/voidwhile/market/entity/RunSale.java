package com.voidwhile.market.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RunSale {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_run_sale.sale_id
     *
     * @mbg.generated
     */
    private Long saleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_run_sale.rs_cmd_id
     *
     * @mbg.generated
     */
    private Long rsCmdId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_run_sale.sale_price
     *
     * @mbg.generated
     */
    private BigDecimal salePrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_run_sale.sale_day
     *
     * @mbg.generated
     */
    private Date saleDay;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_run_sale.intro
     *
     * @mbg.generated
     */
    private String intro;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_run_sale.sale_type
     *
     * @mbg.generated
     */
    private Integer saleType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_run_sale.sale_id
     *
     * @return the value of mk_run_sale.sale_id
     *
     * @mbg.generated
     */
    public Long getSaleId() {
        return saleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_run_sale.sale_id
     *
     * @param saleId the value for mk_run_sale.sale_id
     *
     * @mbg.generated
     */
    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_run_sale.rs_cmd_id
     *
     * @return the value of mk_run_sale.rs_cmd_id
     *
     * @mbg.generated
     */
    public Long getRsCmdId() {
        return rsCmdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_run_sale.rs_cmd_id
     *
     * @param rsCmdId the value for mk_run_sale.rs_cmd_id
     *
     * @mbg.generated
     */
    public void setRsCmdId(Long rsCmdId) {
        this.rsCmdId = rsCmdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_run_sale.sale_price
     *
     * @return the value of mk_run_sale.sale_price
     *
     * @mbg.generated
     */
    public BigDecimal getSalePrice() {
        return salePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_run_sale.sale_price
     *
     * @param salePrice the value for mk_run_sale.sale_price
     *
     * @mbg.generated
     */
    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_run_sale.sale_day
     *
     * @return the value of mk_run_sale.sale_day
     *
     * @mbg.generated
     */
    public Date getSaleDay() {
        return saleDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_run_sale.sale_day
     *
     * @param saleDay the value for mk_run_sale.sale_day
     *
     * @mbg.generated
     */
    public void setSaleDay(Date saleDay) {
        this.saleDay = saleDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_run_sale.intro
     *
     * @return the value of mk_run_sale.intro
     *
     * @mbg.generated
     */
    public String getIntro() {
        return intro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_run_sale.intro
     *
     * @param intro the value for mk_run_sale.intro
     *
     * @mbg.generated
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_run_sale.sale_type
     *
     * @return the value of mk_run_sale.sale_type
     *
     * @mbg.generated
     */
    public Integer getSaleType() {
        return saleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_run_sale.sale_type
     *
     * @param saleType the value for mk_run_sale.sale_type
     *
     * @mbg.generated
     */
    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }
    
    private BigDecimal price;
    
    private String cmdImg;
    private String cmdName;
    

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

	public String getCmdImg() {
		return cmdImg;
	}

	public void setCmdImg(String cmdImg) {
		this.cmdImg = cmdImg;
	}
    
    
}