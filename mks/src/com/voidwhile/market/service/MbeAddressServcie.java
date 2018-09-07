package com.voidwhile.market.service;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.MbeAddress;

public interface MbeAddressServcie extends IBaseService<MbeAddress> {
	
	MbeAddress getDefault(Long memberId);
	
	void setDefault(Long memberId);
}
