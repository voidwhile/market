package com.voidwhile.market.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.voidwhile.market.entity.EveEvent;
import com.voidwhile.market.entity.EveEventCmd;
import com.voidwhile.system.bean.PageResult;

public interface EveEventService extends IBaseService<EveEvent> {

	/**
	 * 获取进行中的活动
	 * @return
	 */
	public List<EveEvent> findEvent();
	
	public void deleteByIds(String[] ids);
	
	public PageResult<EveEventCmd> getCmdPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause);
	
	public void save(Long eventId,Long[] cmdIds,BigDecimal[] prices);
}
