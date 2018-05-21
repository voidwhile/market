/** 
 * Project Name:mks 
 * File Name:CmdTypeService.java 
 * Package Name:com.voidwhile.market.service 
 * Date:2017年9月11日上午2:10:41 
 * Copyright (c) 2017, chenzhou1025@126.com All Rights Reserved. 
 * 
*/  
  
package com.voidwhile.market.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.voidwhile.common.bean.tree.TreeNode;
import com.voidwhile.market.entity.CmdType;

/** 
 * ClassName:CmdTypeService <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2017年9月11日 上午2:10:41 <br/> 
 * @author   zhangzheng 
 * @version   
 * @since    JDK 1.8
 * @see       
 */
public interface CmdTypeService extends IBaseService<CmdType> {
	/**
	 * 
	 * getTree:(获取分类树型结构). <br/> 
	 * 
	 * @author zhangzheng 
	 * @param parentId 父级
	 * @return
	 * @throws DataAccessException 
	 * @since JDK 1.8
	 */
	List<TreeNode> getTree(Integer parentId) throws DataAccessException;
	
	/**
	 * 
	 * getAllChildIds:(获取所有子分类). <br/> 
	 * 
	 * @author zhangzheng 
	 * @param parentId
	 * @param typeIdList
	 * @throws DataAccessException 
	 * @since JDK 1.8
	 */
	public void  getAllChildIds( String parentId,List<String> typeIdList) throws DataAccessException  ;
	
	public void deleteByIds(String[] ids);
}
  