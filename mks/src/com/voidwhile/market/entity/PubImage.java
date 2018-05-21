package com.voidwhile.market.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PubImage {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gxs_pub_image.uid
     *
     * @mbg.generated
     */
    private Long imgId;


    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gxs_pub_image.img_type
     *
     * @mbg.generated
     */
    private Integer imgType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gxs_pub_image.imgname
     *
     * @mbg.generated
     */
    private String imgname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gxs_pub_image.path
     *
     * @mbg.generated
     */
    private String path;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gxs_pub_image.biztype
     *
     * @mbg.generated
     */
    private Integer biztype;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column gxs_pub_image.bizid
     *
     * @mbg.generated
     */
    private Long bizid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gxs_pub_image.uid
     *
     * @return the value of gxs_pub_image.uid
     *
     * @mbg.generated
     */
    public Long getImgId() {
        return imgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gxs_pub_image.uid
     *
     * @param uid the value for gxs_pub_image.uid
     *
     * @mbg.generated
     */
    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gxs_pub_image.img_type
     *
     * @return the value of gxs_pub_image.img_type
     *
     * @mbg.generated
     */
    public Integer getImgType() {
        return imgType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gxs_pub_image.img_type
     *
     * @param imgType the value for gxs_pub_image.img_type
     *
     * @mbg.generated
     */
    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gxs_pub_image.imgname
     *
     * @return the value of gxs_pub_image.imgname
     *
     * @mbg.generated
     */
    public String getImgname() {
        return imgname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gxs_pub_image.imgname
     *
     * @param imgname the value for gxs_pub_image.imgname
     *
     * @mbg.generated
     */
    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gxs_pub_image.path
     *
     * @return the value of gxs_pub_image.path
     *
     * @mbg.generated
     */
    public String getPath() {
        return path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gxs_pub_image.path
     *
     * @param path the value for gxs_pub_image.path
     *
     * @mbg.generated
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gxs_pub_image.biztype
     *
     * @return the value of gxs_pub_image.biztype
     *
     * @mbg.generated
     */
    public Integer getBiztype() {
        return biztype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gxs_pub_image.biztype
     *
     * @param biztype the value for gxs_pub_image.biztype
     *
     * @mbg.generated
     */
    public void setBiztype(Integer biztype) {
        this.biztype = biztype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column gxs_pub_image.bizid
     *
     * @return the value of gxs_pub_image.bizid
     *
     * @mbg.generated
     */
    public Long getBizid() {
        return bizid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column gxs_pub_image.bizid
     *
     * @param bizid the value for gxs_pub_image.bizid
     *
     * @mbg.generated
     */
    public void setBizid(Long bizid) {
        this.bizid = bizid;
    }
}