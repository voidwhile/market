package com.voidwhile.market.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.core.utils.Logger;
import com.voidwhile.core.utils.Slf4JLogger;
import com.voidwhile.market.entity.CmdPrice;
import com.voidwhile.market.entity.EveEvent;
import com.voidwhile.market.entity.EveEventCmd;
import com.voidwhile.market.mapper.CmdPriceMapper;
import com.voidwhile.market.mapper.EveEventCmdMapper;
import com.voidwhile.market.mapper.EveEventMapper;
import com.voidwhile.market.service.EveEventService;
import com.voidwhile.system.bean.PageResult;

@Service
public class EveEventServiceImpl implements EveEventService {
	
	private Logger log = Slf4JLogger.getLogger(EveEventServiceImpl.class);
	
	@Autowired
	private EveEventMapper mapper;
	
	@Autowired
	private EveEventCmdMapper evtCmdMapper;
	
	@Autowired
	private CmdPriceMapper priceMapper;

	@Override
	public void save(EveEvent entity) throws DataAccessException {
		mapper.insertSelective(entity);
	}

	@Override
	public void update(EveEvent entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public EveEvent getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public PageResult<EveEvent> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause)
			throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<EveEvent> pageResult = new PageResult<EveEvent>(pageNo,pageSize);
		pageResult.setTotal(this.countByMap(param));
		param.put("offset", pageResult.getOffset());
		param.put("pageSize", pageResult.getPageSize());
		if (!"".equals(StringUtils.trimToEmpty(orderByClause))) {
			param.put("orderByClause", orderByClause);
		}
		pageResult.setList(this.findByMap(param));
		return pageResult;
	}
	
	@Override
	public PageResult<EveEventCmd> getCmdPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<EveEventCmd> pageResult = new PageResult<EveEventCmd>(pageNo,pageSize);
		pageResult.setTotal(evtCmdMapper.countByMap(param));
		param.put("offset", pageResult.getOffset());
		param.put("pageSize", pageResult.getPageSize());
		if (!"".equals(StringUtils.trimToEmpty(orderByClause))) {
			param.put("orderByClause", orderByClause);
		}
		pageResult.setList(evtCmdMapper.findByMap(param));
		return pageResult;
	}

	@Override
	public List<EveEvent> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

	@Override
	public List<EveEvent> findEvent() {
		return mapper.findEvent();
	}

	@Override
	public void deleteByIds(String[] ids) {
		for(String eventId:ids){
			evtCmdMapper.deleteByEventId(Long.valueOf(eventId));
			priceMapper.deleteByEventId(Long.valueOf(eventId));
		}
		mapper.deleteByIds(ids);
	}

	@Override
	public void save(Long eventId, Long[] cmdIds, BigDecimal[] prices) {
		for(int i=0;i<cmdIds.length;i++){
			EveEventCmd cmd = new EveEventCmd();
			cmd.setEventId(eventId);
			cmd.setCmdId(cmdIds[i]);
			evtCmdMapper.insert(cmd);
			CmdPrice p = new CmdPrice();
			p.setCmdId(cmdIds[i]);
			p.setEventId(eventId);
			p.setPrice(prices[i]);
			p.setPriceType(2);
			p.setStatus(1);
			priceMapper.insert(p);
		}
	}

}
