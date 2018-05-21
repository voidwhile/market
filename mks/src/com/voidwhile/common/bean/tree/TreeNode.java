package com.voidwhile.common.bean.tree;

import java.util.List;

public class TreeNode {
	/**
	 * @Fields id : 节点的ID
	 */
	private String id;
	/**
	 * @Fields text : 节点的 Text
	 */
	private String text;
	/**
	 * @Fields iconCls : 节点的图标
	 */
	private String iconCls;
	/**
	 * @Fields parentId : 父节点的ID
	 */
	private String _parentId;

	/**
	 * @Fields checked : 是否选中
	 */
	private boolean checked;
	/**
	 * @Fields children : 子节点List
	 */
	private List<TreeNode> children;

	public TreeNode() {
	}

	public TreeNode(String id, String text, String iconCls, boolean checked, List<TreeNode> children) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.checked = checked;
		this.children = children;
	}

	public TreeNode(String id, String text, String iconCls, String _parentId, boolean checked) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this._parentId = _parentId;
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public void addChildren(List<TreeNode> children) {
		if (this.children != null && this.children.size() > 0) {
			this.children.addAll(0, children);
		} else {
			this.children = children;
		}
	}

}
