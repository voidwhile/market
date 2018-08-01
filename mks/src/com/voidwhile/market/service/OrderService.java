package com.voidwhile.market.service;

import java.util.Map;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.OdrOrder;
import com.voidwhile.market.entity.OdrOrderDetail;
import com.voidwhile.system.bean.PageResult;

public interface OrderService extends IBaseService<OdrOrder> {

	/**
	 * 获取订单明细
	 * @param orderId
	 * @return
	 */
	PageResult<OdrOrderDetail> getDetail(Map<String, Object> param, int pageNo, int pageSize);
	
	/**
	 * 发货
	 * @param orderId
	 */
	void deliver(Long orderId);
	
	/**
	 * 确认订单
	 * @param memberId
	 */
	void book(Long memberId);
	
	/**
	 * 提交订单
	 * @param memberId
	 */
	void order(OdrOrder order);
	
	/**
	 * 支付下单
	 * @param order
	 */
	void pay(OdrOrder order);
}
