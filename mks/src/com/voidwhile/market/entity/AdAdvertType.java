package com.voidwhile.market.entity;

public class AdAdvertType {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_ad_advert_type.advert_type
     *
     * @mbg.generated
     */
    private Long advertType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_ad_advert_type.type_name
     *
     * @mbg.generated
     */
    private String typeName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_ad_advert_type.advert_type
     *
     * @return the value of mk_ad_advert_type.advert_type
     *
     * @mbg.generated
     */
    public Long getAdvertType() {
        return advertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_ad_advert_type.advert_type
     *
     * @param advertType the value for mk_ad_advert_type.advert_type
     *
     * @mbg.generated
     */
    public void setAdvertType(Long advertType) {
        this.advertType = advertType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_ad_advert_type.type_name
     *
     * @return the value of mk_ad_advert_type.type_name
     *
     * @mbg.generated
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_ad_advert_type.type_name
     *
     * @param typeName the value for mk_ad_advert_type.type_name
     *
     * @mbg.generated
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}