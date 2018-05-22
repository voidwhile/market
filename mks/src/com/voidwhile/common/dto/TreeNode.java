package com.voidwhile.common.dto;

import java.util.HashSet;
import java.util.Set;

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
	 * 节点类型(如人员列表树：dept,person)
	 */
	private String type;

	/**
	 * @Fields children : 子节点List
	 */
	private Set<TreeNode> children = new HashSet<TreeNode>();

	public TreeNode() {
	}

	public TreeNode(String id, String text, String type) {
		this.id = id;
		this.text = text;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(Set<TreeNode> children) {
		this.children = children;
	}

	public void addChildren(TreeNode children) {
		this.children.add(children);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeNode other = (TreeNode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
