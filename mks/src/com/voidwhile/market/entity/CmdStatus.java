package com.voidwhile.market.entity;

public class CmdStatus {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_cmd_status.cmd_status
     *
     * @mbg.generated
     */
    private Integer cmdStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_cmd_status.status_name
     *
     * @mbg.generated
     */
    private String statusName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_cmd_status.cmd_status
     *
     * @return the value of mk_cmd_status.cmd_status
     *
     * @mbg.generated
     */
    public Integer getCmdStatus() {
        return cmdStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_cmd_status.cmd_status
     *
     * @param cmdStatus the value for mk_cmd_status.cmd_status
     *
     * @mbg.generated
     */
    public void setCmdStatus(Integer cmdStatus) {
        this.cmdStatus = cmdStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_cmd_status.status_name
     *
     * @return the value of mk_cmd_status.status_name
     *
     * @mbg.generated
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_cmd_status.status_name
     *
     * @param statusName the value for mk_cmd_status.status_name
     *
     * @mbg.generated
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}