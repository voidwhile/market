package com.voidwhile.market.service;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.RepSupplier;

public interface RepSupplierService extends IBaseService<RepSupplier> {
	
	void deleteByIds(String[] ids);
}
