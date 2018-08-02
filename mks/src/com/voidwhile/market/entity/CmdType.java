package com.voidwhile.market.entity;

import java.util.List;
import java.util.Date;

public class CmdType {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column mk_cmd_type.cmd_type
	 * 
	 * @mbg.generated
	 */
	private Integer cmdType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column mk_cmd_type.parent_id
	 * 
	 * @mbg.generated
	 */
	private Integer parentId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column mk_cmd_type.type_name
	 * 
	 * @mbg.generated
	 */
	private String typeName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column mk_cmd_type.level
	 * 
	 * @mbg.generated
	 */
	private Integer level;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column mk_cmd_type.has_child
	 * 
	 * @mbg.generated
	 */
	private String hasChild;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column mk_cmd_type.create_time
	 * 
	 * @mbg.generated
	 */
	private Date createTime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column mk_cmd_type.img_path
	 * 
	 * @mbg.generated
	 */
	private String imgPath;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column mk_cmd_type.cmd_type
	 * 
	 * @return the value of mk_cmd_type.cmd_type
	 * @mbg.generated
	 */
	public Integer getCmdType() {
		return cmdType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column mk_cmd_type.cmd_type
	 * 
	 * @param cmdType
	 *            the value for mk_cmd_type.cmd_type
	 * @mbg.generated
	 */
	public void setCmdType(Integer cmdType) {
		this.cmdType = cmdType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column mk_cmd_type.parent_id
	 * 
	 * @return the value of mk_cmd_type.parent_id
	 * @mbg.generated
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column mk_cmd_type.parent_id
	 * 
	 * @param parentId
	 *            the value for mk_cmd_type.parent_id
	 * @mbg.generated
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column mk_cmd_type.type_name
	 * 
	 * @return the value of mk_cmd_type.type_name
	 * @mbg.generated
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column mk_cmd_type.type_name
	 * 
	 * @param typeName
	 *            the value for mk_cmd_type.type_name
	 * @mbg.generated
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column mk_cmd_type.level
	 * 
	 * @return the value of mk_cmd_type.level
	 * @mbg.generated
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column mk_cmd_type.level
	 * 
	 * @param level
	 *            the value for mk_cmd_type.level
	 * @mbg.generated
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column mk_cmd_type.has_child
	 * 
	 * @return the value of mk_cmd_type.has_child
	 * @mbg.generated
	 */
	public String getHasChild() {
		return hasChild;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column mk_cmd_type.has_child
	 * 
	 * @param hasChild
	 *            the value for mk_cmd_type.has_child
	 * @mbg.generated
	 */
	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column mk_cmd_type.create_time
	 * 
	 * @return the value of mk_cmd_type.create_time
	 * @mbg.generated
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column mk_cmd_type.create_time
	 * 
	 * @param createTime
	 *            the value for mk_cmd_type.create_time
	 * @mbg.generated
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column mk_cmd_type.img_path
	 * 
	 * @return the value of mk_cmd_type.img_path
	 * @mbg.generated
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column mk_cmd_type.img_path
	 * 
	 * @param imgPath
	 *            the value for mk_cmd_type.img_path
	 * @mbg.generated
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	private List<CmdType> children;

	public List<CmdType> getChildren() {
		return children;
	}

	public void setChildren(List<CmdType> children) {
		this.children = children;
	}
	
	private CmdType parentType;

	public CmdType getParentType() {
		return parentType;
	}

	public void setParentType(CmdType parentType) {
		this.parentType = parentType;
	}
	
}