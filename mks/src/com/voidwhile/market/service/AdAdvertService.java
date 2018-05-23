package com.voidwhile.market.service;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.AdAdvert;

public interface AdAdvertService extends IBaseService<AdAdvert> {
	void deleteByIds(String[] ids);
}
