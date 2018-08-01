package com.voidwhile.market.service;

import java.util.List;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.OdrCart;

public interface OdrCartService extends IBaseService<OdrCart> {
	
	List<OdrCart> findByMemberId(Long memberId);
	
	List<OdrCart> findForSettle(Long memberId);

}
