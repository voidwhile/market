package com.voidwhile.market.service;

import java.util.List;

import com.voidwhile.market.entity.EveEvent;

public interface EveEventService extends IBaseService<EveEvent> {

	/**
	 * 获取进行中的活动
	 * @return
	 */
	public List<EveEvent> findEvent();
}
