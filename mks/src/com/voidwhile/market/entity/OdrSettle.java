package com.voidwhile.market.entity;

public class OdrSettle {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_odr_settle.settle_id
     *
     * @mbg.generated
     */
    private Long settleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_odr_settle.member_id
     *
     * @mbg.generated
     */
    private Long memberId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mk_odr_settle.cart_id
     *
     * @mbg.generated
     */
    private Long cartId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_odr_settle.settle_id
     *
     * @return the value of mk_odr_settle.settle_id
     *
     * @mbg.generated
     */
    public Long getSettleId() {
        return settleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_odr_settle.settle_id
     *
     * @param settleId the value for mk_odr_settle.settle_id
     *
     * @mbg.generated
     */
    public void setSettleId(Long settleId) {
        this.settleId = settleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_odr_settle.member_id
     *
     * @return the value of mk_odr_settle.member_id
     *
     * @mbg.generated
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_odr_settle.member_id
     *
     * @param memberId the value for mk_odr_settle.member_id
     *
     * @mbg.generated
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mk_odr_settle.cart_id
     *
     * @return the value of mk_odr_settle.cart_id
     *
     * @mbg.generated
     */
    public Long getCartId() {
        return cartId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mk_odr_settle.cart_id
     *
     * @param cartId the value for mk_odr_settle.cart_id
     *
     * @mbg.generated
     */
    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}