package com.voidwhile.market.service;

import com.voidwhile.core.IBaseService;
import com.voidwhile.market.entity.CmdSpecification;

public interface CmdSpecificationService extends IBaseService<CmdSpecification> {
	
	void deleteByCmdId(Long cmdId);

}
