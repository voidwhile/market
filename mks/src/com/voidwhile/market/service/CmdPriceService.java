package com.voidwhile.market.service;

import com.voidwhile.market.entity.CmdPrice;

public interface CmdPriceService extends IBaseService<CmdPrice> {
	
	public void deleteByIds(String[] ids);
	
}
