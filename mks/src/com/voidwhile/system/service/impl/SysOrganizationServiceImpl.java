package com.voidwhile.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.voidwhile.common.bean.tree.TreeNode;
import com.voidwhile.system.bean.PageResult;
import com.voidwhile.system.constant.SysConstant;
import com.voidwhile.system.entity.SysOrganization;
import com.voidwhile.system.entity.SysUser;
import com.voidwhile.system.mapper.SysOrganizationMapper;
import com.voidwhile.system.mapper.SysUserMapper;
import com.voidwhile.system.service.SysOrganizationService;

/**
 * CopyRright (c) 2017: 
 * 
 * @Description: 企业内部组织机构管理业务类
 * @author: xiaowei
 * @Create Date: 2014年12月5日 下午1:43:26
 *
 * @Version: v1.0
 */
@Service
public class SysOrganizationServiceImpl implements SysOrganizationService {

	@Autowired
	private SysOrganizationMapper sysOrganizationMapper;

	@Autowired
	private SysUserMapper userMapper;

	@Override
	public void save(SysOrganization entity) throws DataAccessException {
		entity.setUid(idGenarater.getNextValue());
		if (StringUtils.isEmpty(entity.getpId())) {
			entity.setpId("0");
		}
		entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());
		sysOrganizationMapper.insert(entity);
	}

	@Override
	public void update(SysOrganization entity) throws DataAccessException {
		entity.setUpdateTime(new Date());
		if (SysConstant.NO.equals(entity.getStatus()) && !orgCancelCheck(entity.getUid())) {
			throw new RuntimeException("存在关联数据，请处理后再设置为不可用！");
		}
		sysOrganizationMapper.updateById(entity);
	}

	@Override
	public void delete(String id) throws DataAccessException {
		if (orgCancelCheck(id)) {
			sysOrganizationMapper.deleteById(id);
		} else {
			throw new RuntimeException("存在关联数据，请处理后再删除！");
		}
	}

	@Override
	public void deleteByIds(String[] ids) throws DataAccessException {
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				delete(id);
			}
		}
	}

	@Override
	public SysOrganization getById(String id) throws DataAccessException {
		return sysOrganizationMapper.getById(id);
	}

	@Override
	@Deprecated
	public PageResult<SysOrganization> findPageData(Map<String, Object> param, int pageNo, int pageSize, String orderByClause) throws DataAccessException {
		return null;
	}

	@Override
	public List<SysOrganization> findByMap(Map<String, Object> param) throws DataAccessException {
		return sysOrganizationMapper.findByMap(param);
	}

	@Override
	public int countByMap(Map<String, Object> param) throws DataAccessException {
		return sysOrganizationMapper.countByMap(param);
	}
	@Override
	public List<Map<String, Object>> getTreeGridData(Map<String, Object> param) throws DataAccessException {
		param.put("orderByClause", "create_time desc");
		List<SysOrganization> orgList = findByMap(param);
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		if (orgList != null && orgList.size() > 0) {
			Map<String, Object> map = null;
			for (SysOrganization entity : orgList) {
				map = new HashMap<String, Object>();
				map.put("uid", entity.getUid());
				if (orgList.contains(new SysOrganization(entity.getpId()))) {
					map.put("_parentId", entity.getpId());
				}
				map.put("orgName", entity.getOrgName());
				map.put("orgCode", entity.getOrgCode());
				map.put("orgHead", entity.getOrgHead());
				map.put("orgPhone", entity.getOrgPhone());
				map.put("status", entity.getStatus());
				map.put("ownerUsername", entity.getOwnerUsername());
				treeList.add(map);
			}
		}
		return treeList;
	}

	@Override
	public List<SysOrganization> getChilds(String supplierId, String parentId) throws DataAccessException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplierId", supplierId);
		param.put("pId", parentId);
		param.put("status", "1");
		param.put("orderByClause", "create_time desc");
		List<SysOrganization> childs = findByMap(param);
		return childs;
	}

	@Override
	public List<TreeNode> getTree(String supplierId, String parentId, String exclude) throws DataAccessException {
		List<TreeNode> treeNodes = null;
		List<SysOrganization> childOrgs = getChilds(supplierId, parentId);
		if (childOrgs != null && childOrgs.size() > 0) {
			treeNodes = new ArrayList<TreeNode>();
			TreeNode node = null;
			for (SysOrganization entity : childOrgs) {
				if (entity.getUid().equals(exclude)) {
					continue;
				}
				node = new TreeNode();
				node.setId(entity.getUid());
				node.setText(entity.getOrgName());

				List<TreeNode> childNodes = getTree(supplierId, entity.getUid(), exclude);
				if (childNodes != null && childNodes.size() > 0) {
					node.setChildren(childNodes);
				}
				treeNodes.add(node);
			}
		}
		return treeNodes;
	}

	public List<TreeNode> getUserTree(String supplierId, String parentId, List<String> selectedUser) throws DataAccessException {
		List<TreeNode> treeNodes = null;
		Map<String, Object> queryParam = new HashMap<String, Object>();
		queryParam.put("supplierId", supplierId);
		queryParam.put("status", SysConstant.YES);
		queryParam.put("isSystem", SysConstant.NO);
		List<SysOrganization> childOrgs = getChilds(supplierId, parentId);
		if (childOrgs != null && childOrgs.size() > 0) {
			treeNodes = new ArrayList<TreeNode>();
			TreeNode node = null;
			for (SysOrganization entity : childOrgs) {
				node = new TreeNode();
				node.setId("dept|" + entity.getUid());
				node.setText(entity.getOrgName());
				// 加入用户
				queryParam.put("deptId", entity.getUid());
				List<SysUser> userList = userMapper.findByMap(queryParam);
				List<TreeNode> childNodes = new ArrayList<TreeNode>();
				if (userList != null && userList.size() > 0) {
					for (SysUser user : userList) {
						boolean checked=false;
						//(selectedUser != null) && selectedUser.contains(user.getUid());
						if(selectedUser!=null && selectedUser.size()>0){
						for(int i=0;i<selectedUser.size();i++){
							if(selectedUser.get(i).toString().contains(user.getUid()+"")){
								checked=true;
								break;
							}
						}
							
						}
						childNodes.add(new TreeNode("user|" + user.getUid(), user.getRealName(), "icon-user", checked, null));
					}
				}
				// 加入子部门
				List<TreeNode> childDeptNodes = getUserTree(supplierId, entity.getUid(), selectedUser);
				if (childDeptNodes != null && childDeptNodes.size() > 0) {
					childNodes.addAll(childDeptNodes);
				}
				if (childNodes.size() > 0)
					node.setChildren(childNodes);

				treeNodes.add(node);
			}
		}
		return treeNodes;
	}

	/**
	 * @MethodName: orgCancelCheck
	 * @Description: 检查组织机构是否可以删除、注销
	 * @param id
	 * @return
	 *
	 * @Author: xiaowei
	 * @Create Date: 2014年12月26日 上午11:11:49
	 */
	private boolean orgCancelCheck(String id) {
		Map<String, Object> param = new HashMap<String, Object>();
		// 是否包含子机构
		param.put("pId", id);
		List<SysOrganization> list = findByMap(param);
		if (list != null && list.size() > 0) {
			return false;
		}
		param = new HashMap<String, Object>();
		param.put("deptId", id);
		List<SysUser> userList = userMapper.findByMap(param);
		if (userList != null && userList.size() > 0) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @param parentId
	 * @param orgIdList
	 * @throws DataAccessException
	 */
	public void  getAllChildIds( String parentId,List<String> orgIdList) throws DataAccessException {
 		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pId", parentId);
		param.put("status", "1");
		param.put("orderByClause", "create_time desc");
		List<SysOrganization> childs = findByMap(param);
		for (SysOrganization sysOrganization : childs) {
			orgIdList.add(sysOrganization.getUid());
			//计数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pId", sysOrganization.getUid());
			map.put("status", "1");
            int count = sysOrganizationMapper.countByMap(map);
            if (count > 0) {
            	getAllChildIds(sysOrganization.getUid(), orgIdList);
            }
        }
		System.out.println(childs.size());
 	}


}
