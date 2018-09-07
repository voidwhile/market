package com.voidwhile.market.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.tools.Tool;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.voidwhile.common.utils.Tools;
import com.voidwhile.market.entity.OdrCart;
import com.voidwhile.market.entity.OdrOrder;
import com.voidwhile.market.entity.OdrOrderDetail;
import com.voidwhile.market.entity.OdrSettle;
import com.voidwhile.market.mapper.OdrOrderDetailMapper;
import com.voidwhile.market.mapper.OdrOrderMapper;
import com.voidwhile.market.mapper.OdrSettleMapper;
import com.voidwhile.market.service.OdrCartService;
import com.voidwhile.market.service.OrderService;
import com.voidwhile.system.bean.PageResult;

@Service
@Transactional(rollbackFor=Exception.class)
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OdrOrderMapper mapper;
	@Autowired
	private OdrOrderDetailMapper detailMapper;
	@Autowired
	private OdrCartService cartService;
	@Autowired
	private OdrSettleMapper settleMapper;

	@Override
	public void save(OdrOrder entity) throws DataAccessException {
		mapper.insertSelective(entity);
	}

	@Override
	public void update(OdrOrder entity) throws DataAccessException {
		
	}

	@Override
	public void delete(String id) throws DataAccessException {
		
	}

	@Override
	public OdrOrder getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Long.valueOf(id));
	}

	@Override
	public PageResult<OdrOrder> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause)
			throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<OdrOrder> pageResult = new PageResult<OdrOrder>(pageNo,pageSize);
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
	public List<OdrOrder> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

	@Override
	public PageResult<OdrOrderDetail> getDetail(Map<String, Object> param, int pageNo, int pageSize) {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<OdrOrderDetail> pageResult = new PageResult<OdrOrderDetail>(pageNo,pageSize);
		pageResult.setTotal(detailMapper.countByOrderId((Long)param.get("orderId")));
		param.put("offset", pageResult.getOffset());
		param.put("pageSize", pageResult.getPageSize());
		pageResult.setList(detailMapper.findByOrderId((Long)param.get("orderId")));
		return pageResult;
	}

	@Override
	public void deliver(Long orderId) {
		mapper.deliver(orderId);
	}

	@Override
	public void book(Long memberId) {
		List<OdrCart> cmdList = cartService.findByMemberId(memberId);
		for (OdrCart odrCart : cmdList) {
			int num = settleMapper.countByCartId(odrCart.getCartId());
			if (num==0) {
				OdrSettle settle = new OdrSettle();
				settle.setMemberId(memberId);
				settle.setCartId(odrCart.getCartId());
				settleMapper.insert(settle);
			}
		}
	}

	@Override
	public void order(OdrOrder order) {
		List<OdrCart> cmdList = cartService.findForSettle(order.getMemberId());
		order.setOrderCode(this.createOrderCode());
		order.setCreateTime(new Date());
		order.setPayStatus(0);
		order.setStatus(1);
		this.save(order);
		for (OdrCart cmd : cmdList) {
			OdrOrderDetail detail = new OdrOrderDetail();
			detail.setOrderId(order.getOrderId());
			detail.setCmdId(cmd.getOcCmdId());
			detail.setQuantity(cmd.getNum());
			detailMapper.insert(detail);
		}
		cartService.settle(order.getMemberId());
		settleMapper.deleteByMemberId(order.getMemberId());
		
	}
	
	private String createOrderCode() {
		int code = (int)(Math.random()*10000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss"); 
	    String ctime = sdf.format(new Date());
	    return ctime+code;
	}

	@Override
	public void pay(OdrOrder order) {
		
	}

	@Override
	public void giveUp(Long memberId) {
		mapper.giveUp(memberId);
	}

	@Override
	public void cancel(Long memberId) {
		settleMapper.deleteByMemberId(memberId);
	}

}
