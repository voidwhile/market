package com.voidwhile.system.entity;

import java.util.Date;


public class DataBackup {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_backup.backup_id
     *
     * @mbg.generated
     */
    private Integer backupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_backup.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_backup.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_backup.backup_date
     *
     * @mbg.generated
     */
    private Date backupDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_backup.backup_path
     *
     * @mbg.generated
     */
    private String backupPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_backup.backup_range
     *
     * @mbg.generated
     */
    private Integer backupRange;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_backup.user_id
     *
     * @mbg.generated
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_backup.opt_type
     *
     * @mbg.generated
     */
    private Integer optType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_backup.base_id
     *
     * @mbg.generated
     */
    private Integer baseId;
    
    private SysUser user;
    
    private DataBackup baseDir;
    
    public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public DataBackup getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(DataBackup baseDir) {
		this.baseDir = baseDir;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_backup.backup_id
     *
     * @return the value of sys_data_backup.backup_id
     *
     * @mbg.generated
     */
    public Integer getBackupId() {
        return backupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_backup.backup_id
     *
     * @param backupId the value for sys_data_backup.backup_id
     *
     * @mbg.generated
     */
    public void setBackupId(Integer backupId) {
        this.backupId = backupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_backup.title
     *
     * @return the value of sys_data_backup.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_backup.title
     *
     * @param title the value for sys_data_backup.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_backup.description
     *
     * @return the value of sys_data_backup.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_backup.description
     *
     * @param description the value for sys_data_backup.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_backup.backup_date
     *
     * @return the value of sys_data_backup.backup_date
     *
     * @mbg.generated
     */
    public Date getBackupDate() {
        return backupDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_backup.backup_date
     *
     * @param backupDate the value for sys_data_backup.backup_date
     *
     * @mbg.generated
     */
    public void setBackupDate(Date backupDate) {
        this.backupDate = backupDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_backup.backup_path
     *
     * @return the value of sys_data_backup.backup_path
     *
     * @mbg.generated
     */
    public String getBackupPath() {
        return backupPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_backup.backup_path
     *
     * @param backupPath the value for sys_data_backup.backup_path
     *
     * @mbg.generated
     */
    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_backup.backup_range
     *
     * @return the value of sys_data_backup.backup_range
     *
     * @mbg.generated
     */
    public Integer getBackupRange() {
        return backupRange;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_backup.backup_range
     *
     * @param backupRange the value for sys_data_backup.backup_range
     *
     * @mbg.generated
     */
    public void setBackupRange(Integer backupRange) {
        this.backupRange = backupRange;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_backup.user_id
     *
     * @return the value of sys_data_backup.user_id
     *
     * @mbg.generated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_backup.user_id
     *
     * @param userId the value for sys_data_backup.user_id
     *
     * @mbg.generated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_backup.opt_type
     *
     * @return the value of sys_data_backup.opt_type
     *
     * @mbg.generated
     */
    public Integer getOptType() {
        return optType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_backup.opt_type
     *
     * @param optType the value for sys_data_backup.opt_type
     *
     * @mbg.generated
     */
    public void setOptType(Integer optType) {
        this.optType = optType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_backup.base_id
     *
     * @return the value of sys_data_backup.base_id
     *
     * @mbg.generated
     */
    public Integer getBaseId() {
        return baseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_backup.base_id
     *
     * @param baseId the value for sys_data_backup.base_id
     *
     * @mbg.generated
     */
    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }
}