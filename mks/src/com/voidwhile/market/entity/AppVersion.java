package com.voidwhile.market.entity;

import java.util.Date;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: APP版本管理实体类
 * @author: xiaowei
 * @Create Date: 2014年12月22日 上午11:16:12
 *
 * @Version: v1.0
 */
public class AppVersion {
	/**
	 * @Fields supplierId : 企业ID
	 */
    private String supplierId;

	/**
	 * @Fields title : 版本信息
	 */
    private String title;

	/**
	 * @Fields version : 版本号
	 */
    private Integer version;

	/**
	 * @Fields remark : 版本描述
	 */
    private String remark;

	/**
	 * @Fields file : APP下载地址
	 */
    private String file;

	/**
	 * @Fields isMust : 是否必须更新
	 */
    private String isMust;

	/**
	 * @Fields versionTime : 版本更新时间
	 */
    private Date versionTime;

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    public String getIsMust() {
        return isMust;
    }

    public void setIsMust(String isMust) {
        this.isMust = isMust == null ? null : isMust.trim();
    }

    public Date getVersionTime() {
        return versionTime;
    }

    public void setVersionTime(Date versionTime) {
        this.versionTime = versionTime;
    }
}