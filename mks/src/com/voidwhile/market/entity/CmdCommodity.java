package com.voidwhile.market.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CmdCommodity {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.cmd_id
	 * @mbg.generated
	 */
	private Long cmdId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.cmd_name
	 * @mbg.generated
	 */
	private String cmdName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.cmd_code
	 * @mbg.generated
	 */
	private String cmdCode;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.cmd_brand
	 * @mbg.generated
	 */
	private Long cmdBrand;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.cmd_status
	 * @mbg.generated
	 */
	private Integer cmdStatus;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.create_time
	 * @mbg.generated
	 */
	private Date createTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.putaway_time
	 * @mbg.generated
	 */
	private Date putawayTime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.shelf_life
	 * @mbg.generated
	 */
	private String shelfLife;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.produce_date
	 * @mbg.generated
	 */
	private Date produceDate;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.expiration_date
	 * @mbg.generated
	 */
	private Date expirationDate;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.supplier_id
	 * @mbg.generated
	 */
	private Long supplierId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.img_path
	 * @mbg.generated
	 */
	private String imgPath;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.cc_cmd_type
	 * @mbg.generated
	 */
	private Integer ccCmdType;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.prod_place
	 * @mbg.generated
	 */
	private String prodPlace;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.storage_condition
	 * @mbg.generated
	 */
	private String storageCondition;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column mk_cmd_commodity.description
	 * @mbg.generated
	 */
	private String description;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.cmd_id
	 * @return  the value of mk_cmd_commodity.cmd_id
	 * @mbg.generated
	 */
	public Long getCmdId() {
		return cmdId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.cmd_id
	 * @param cmdId  the value for mk_cmd_commodity.cmd_id
	 * @mbg.generated
	 */
	public void setCmdId(Long cmdId) {
		this.cmdId = cmdId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.cmd_name
	 * @return  the value of mk_cmd_commodity.cmd_name
	 * @mbg.generated
	 */
	public String getCmdName() {
		return cmdName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.cmd_name
	 * @param cmdName  the value for mk_cmd_commodity.cmd_name
	 * @mbg.generated
	 */
	public void setCmdName(String cmdName) {
		this.cmdName = cmdName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.cmd_code
	 * @return  the value of mk_cmd_commodity.cmd_code
	 * @mbg.generated
	 */
	public String getCmdCode() {
		return cmdCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.cmd_code
	 * @param cmdCode  the value for mk_cmd_commodity.cmd_code
	 * @mbg.generated
	 */
	public void setCmdCode(String cmdCode) {
		this.cmdCode = cmdCode;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.cmd_brand
	 * @return  the value of mk_cmd_commodity.cmd_brand
	 * @mbg.generated
	 */
	public Long getCmdBrand() {
		return cmdBrand;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.cmd_brand
	 * @param cmdBrand  the value for mk_cmd_commodity.cmd_brand
	 * @mbg.generated
	 */
	public void setCmdBrand(Long cmdBrand) {
		this.cmdBrand = cmdBrand;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.cmd_status
	 * @return  the value of mk_cmd_commodity.cmd_status
	 * @mbg.generated
	 */
	public Integer getCmdStatus() {
		return cmdStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.cmd_status
	 * @param cmdStatus  the value for mk_cmd_commodity.cmd_status
	 * @mbg.generated
	 */
	public void setCmdStatus(Integer cmdStatus) {
		this.cmdStatus = cmdStatus;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.create_time
	 * @return  the value of mk_cmd_commodity.create_time
	 * @mbg.generated
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.create_time
	 * @param createTime  the value for mk_cmd_commodity.create_time
	 * @mbg.generated
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.putaway_time
	 * @return  the value of mk_cmd_commodity.putaway_time
	 * @mbg.generated
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getPutawayTime() {
		return putawayTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.putaway_time
	 * @param putawayTime  the value for mk_cmd_commodity.putaway_time
	 * @mbg.generated
	 */
	public void setPutawayTime(Date putawayTime) {
		this.putawayTime = putawayTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.shelf_life
	 * @return  the value of mk_cmd_commodity.shelf_life
	 * @mbg.generated
	 */
	public String getShelfLife() {
		return shelfLife;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.shelf_life
	 * @param shelfLife  the value for mk_cmd_commodity.shelf_life
	 * @mbg.generated
	 */
	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.produce_date
	 * @return  the value of mk_cmd_commodity.produce_date
	 * @mbg.generated
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getProduceDate() {
		return produceDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.produce_date
	 * @param produceDate  the value for mk_cmd_commodity.produce_date
	 * @mbg.generated
	 */
	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.expiration_date
	 * @return  the value of mk_cmd_commodity.expiration_date
	 * @mbg.generated
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.expiration_date
	 * @param expirationDate  the value for mk_cmd_commodity.expiration_date
	 * @mbg.generated
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.supplier_id
	 * @return  the value of mk_cmd_commodity.supplier_id
	 * @mbg.generated
	 */
	public Long getSupplierId() {
		return supplierId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.supplier_id
	 * @param supplierId  the value for mk_cmd_commodity.supplier_id
	 * @mbg.generated
	 */
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.img_path
	 * @return  the value of mk_cmd_commodity.img_path
	 * @mbg.generated
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.img_path
	 * @param imgPath  the value for mk_cmd_commodity.img_path
	 * @mbg.generated
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.cc_cmd_type
	 * @return  the value of mk_cmd_commodity.cc_cmd_type
	 * @mbg.generated
	 */
	public Integer getCcCmdType() {
		return ccCmdType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.cc_cmd_type
	 * @param ccCmdType  the value for mk_cmd_commodity.cc_cmd_type
	 * @mbg.generated
	 */
	public void setCcCmdType(Integer ccCmdType) {
		this.ccCmdType = ccCmdType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.prod_place
	 * @return  the value of mk_cmd_commodity.prod_place
	 * @mbg.generated
	 */
	public String getProdPlace() {
		return prodPlace;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.prod_place
	 * @param prodPlace  the value for mk_cmd_commodity.prod_place
	 * @mbg.generated
	 */
	public void setProdPlace(String prodPlace) {
		this.prodPlace = prodPlace;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.storage_condition
	 * @return  the value of mk_cmd_commodity.storage_condition
	 * @mbg.generated
	 */
	public String getStorageCondition() {
		return storageCondition;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.storage_condition
	 * @param storageCondition  the value for mk_cmd_commodity.storage_condition
	 * @mbg.generated
	 */
	public void setStorageCondition(String storageCondition) {
		this.storageCondition = storageCondition;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column mk_cmd_commodity.description
	 * @return  the value of mk_cmd_commodity.description
	 * @mbg.generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column mk_cmd_commodity.description
	 * @param description  the value for mk_cmd_commodity.description
	 * @mbg.generated
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	private CmdStatus status;//状�?? 
	
	private RepSupplier supplier;//供应�??
	
	private CmdBrand brand;//品牌
	
	private List<CmdLabel> labels;//标签
	
	private List<CmdSpecification> sfts;//规格
	
	private List<PubImage> images;//图片
	
	private BigDecimal price;
	
	private Long ccPriceId;
	
	private BigDecimal eventPrice;
	
	private String typeName;
	

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getCcPriceId() {
		return ccPriceId;
	}

	public void setCcPriceId(Long ccPriceId) {
		this.ccPriceId = ccPriceId;
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

	public List<PubImage> getImages() {
		return images;
	}

	public void setImages(List<PubImage> images) {
		this.images = images;
	}

	
	public List<CmdLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<CmdLabel> labels) {
		this.labels = labels;
	}

	public List<CmdSpecification> getSfts() {
		return sfts;
	}

	public void setSfts(List<CmdSpecification> sfts) {
		this.sfts = sfts;
	}


	public CmdStatus getStatus() {
		return status;
	}

	public void setStatus(CmdStatus status) {
		this.status = status;
	}

	public RepSupplier getSupplier() {
		return supplier;
	}

	public void setSupplier(RepSupplier supplier) {
		this.supplier = supplier;
	}

	public CmdBrand getBrand() {
		return brand;
	}

	public void setBrand(CmdBrand brand) {
		this.brand = brand;
	}
}