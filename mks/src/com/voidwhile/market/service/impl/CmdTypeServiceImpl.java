/** 
 * Project Name:mks 
 * File Name:CmdTypeServiceImpl.java 
 * Package Name:com.voidwhile.market.service.impl 
 * Date:2017年9月11日下午12:45:33 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.voidwhile.market.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.bean.tree.TreeNode;
import com.voidwhile.common.utils.Tools;
import com.voidwhile.market.constant.MarketConstant;
import com.voidwhile.market.entity.CmdLabel;
import com.voidwhile.market.entity.CmdType;
import com.voidwhile.market.mapper.CmdLabelMapper;
import com.voidwhile.market.mapper.CmdTypeMapper;
import com.voidwhile.market.service.CmdTypeService;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.constant.SysConstant;

/** 
 * ClassName:CmdTypeServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年9月11日 下午12:45:33 <br/> 
 * @author   zhangzheng 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
@Service
public class CmdTypeServiceImpl implements CmdTypeService {
	
	private Logger logger = Logger.getLogger(CmdTypeServiceImpl.class);
	
	@Autowired
	private CmdTypeMapper mapper;
	
	@Autowired
	private CmdLabelMapper labelMapper;

	@Override
	public void save(CmdType entity) throws DataAccessException {
		if(entity.getParentId()==0){
			entity.setLevel(1);
		}else{
			CmdType parent = this.getById(Tools.toString(entity.getParentId()));
			entity.setLevel(parent.getLevel()+1);
			if (SysConstant.NO.equals(parent.getHasChild())) {
				parent.setHasChild("1");
				this.update(parent);
			}
		}
		entity.setHasChild("0");
		mapper.insert(entity);
	}

	@Override
	public void update(CmdType entity) throws DataAccessException {
		mapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		mapper.deleteByPrimaryKey(Integer.parseInt(id));
		labelMapper.deleteByCmdType(Integer.parseInt(id));
	}

	@Override
	public CmdType getById(String id) throws DataAccessException {
		return mapper.selectByPrimaryKey(Integer.valueOf(id));
	}

	@Override
	public PageResult<CmdType> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause)
			throws DataAccessException {
		if (param == null) {
			param = new HashMap<String, Object>();
		}
		PageResult<CmdType> pageResult = new PageResult<CmdType>(pageNo, pageSize);
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
	public List<CmdType> findByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.selectByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return mapper.countByMap(param);
	}

	@Override
	public List<TreeNode> getTree(Integer parentId) throws DataAccessException {
		List<TreeNode> treeNodes = new ArrayList<>();
		Map<String, Object> param = new HashMap<>();
		param.put("parentId", parentId);
		List<CmdType> list = mapper.selectByMap(param);
		for(CmdType t:list){
			List<TreeNode> childList = null;
			TreeNode n = new TreeNode();
			n.setText(t.getTypeName());
			n.setId(Tools.toString(t.getCmdType()));
			n.set_parentId(Tools.toString(t.getParentId()));
			if (SysConstant.YES.equals(t.getHasChild())) {
				childList = this.getTree(t.getCmdType());
				n.setChildren(childList);
			}
			treeNodes.add(n);
		}
		return treeNodes;
	}

	@Override
	public void getAllChildIds(String parentId, List<String> typeIdList) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByIds(String[] ids) {
		mapper.deleteByIds(ids);
	}

}
  