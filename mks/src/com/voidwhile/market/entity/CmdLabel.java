package com.voidwhile.market.entity;

public class CmdLabel {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_cmd_label.label_id
     *
     * @mbg.generated
     */
    private Long labelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_cmd_label.cmd_id
     *
     * @mbg.generated
     */
    private Long cmdId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_cmd_label.cmd_type
     *
     * @mbg.generated
     */
    private Integer cmdType;
    
    private CmdType type;
    

    public CmdType getType() {
		return type;
	}

	public void setType(CmdType type) {
		this.type = type;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_cmd_label.label_id
     *
     * @return the value of mk_cmd_label.label_id
     *
     * @mbg.generated
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_cmd_label.label_id
     *
     * @param labelId the value for mk_cmd_label.label_id
     *
     * @mbg.generated
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_cmd_label.cmd_id
     *
     * @return the value of mk_cmd_label.cmd_id
     *
     * @mbg.generated
     */
    public Long getCmdId() {
        return cmdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_cmd_label.cmd_id
     *
     * @param cmdId the value for mk_cmd_label.cmd_id
     *
     * @mbg.generated
     */
    public void setCmdId(Long cmdId) {
        this.cmdId = cmdId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_cmd_label.cmd_type
     *
     * @return the value of mk_cmd_label.cmd_type
     *
     * @mbg.generated
     */
    public Integer getCmdType() {
        return cmdType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_cmd_label.cmd_type
     *
     * @param cmdType the value for mk_cmd_label.cmd_type
     *
     * @mbg.generated
     */
    public void setCmdType(Integer cmdType) {
        this.cmdType = cmdType;
    }
}