package com.voidwhile.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.voidwhile.common.bean.tree.TreeNode;
import com.voidwhile.market.service.IBaseService;
import com.voidwhile.system.entity.SysOrganization;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 企业组织机构管理 interface
 * @author: xiaowei
 * @Create Date: 2014年12月5日 下午1:42:18
 *
 * @Version: v1.0
 */
public interface SysOrganizationService extends IBaseService<SysOrganization> {

	/**
	 * @MethodName: getChilds
	 * @Description: 获取子部门列表
	 * @param supplierId
	 * @param parentId
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月5日 下午4:13:24
	 */
	List<SysOrganization> getChilds(String supplierId, String parentId) throws DataAccessException;

	/**
	 * @MethodName: getTreeGridData
	 * @Description: easyui TreeGrid数据处理
	 * @param param
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月5日 下午2:21:08
	 */
	List<Map<String, Object>> getTreeGridData(Map<String, Object> param) throws DataAccessException;

	/**
	 * @MethodName: deleteByIds
	 * @Description: 批量删除
	 * @param ids
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年10月24日 下午8:06:42
	 */
	void deleteByIds(String[] ids) throws DataAccessException;

	/**
	 * @MethodName: getTree
	 * @Description: 按上级ID获取组织机构树
	 * @param supplierId 企业ID
	 * @param parentId 父ID
	 * @param exclude 不需要显示的ID
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月5日 下午4:10:33
	 */
	List<TreeNode> getTree(String supplierId, String parentId, String exclude) throws DataAccessException;

	/**
	 * @MethodName: getUserTree
	 * @Description: 根据机构ID获取组织机构和用户树
	 * @param supplierId
	 * @param parentId
	 * @param selectedUser
	 * @return
	 * @throws DataAccessException
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月12日 下午4:07:41
	 */
	public List<TreeNode> getUserTree(String supplierId, String parentId, List<String> selectedUser) throws DataAccessException;
	/**
	 * 根据父id查询所有子id
	 * @param parentId
	 * @param orgIdList
	 * @throws DataAccessException
	 */
	public void  getAllChildIds( String parentId,List<String> orgIdList) throws DataAccessException  ;
}
