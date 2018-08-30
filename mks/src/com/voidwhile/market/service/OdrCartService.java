package com.voidwhile.market.service;

import java.util.List;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.OdrCart;

public interface OdrCartService extends IBaseService<OdrCart> {
	
	List<OdrCart> findByMemberId(Long memberId);
	
	/**
	 * 结算单
	 * @param memberId
	 * @return
	 */
	List<OdrCart> findForSettle(Long memberId);
	
	void add(OdrCart entity);
	
	Double sum(Long memberId);
	
	/**
	 * 结算商品
	 * @param memberId
	 */
	void settle(Long memberId);
	
}
