package com.shiro.pojo;

import java.io.Serializable;

/**
 * �û�����
 * @author �⸣��
 */
public class Organization implements Serializable {
	
	private static final long serialVersionUID = -6764875641987156483L;
	
	private Long id; // ���
	private String name; // ��֯��������
	private Long parentId; // �����
	private String parentIds; // ������б���1/2/
	private Boolean available = Boolean.FALSE;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public boolean isRootNode() {
		return parentId == 0;
	}

	public String makeSelfAsParentIds() {
		return getParentIds() + getId() + "/";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Organization that = (Organization) o;

		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Organization{" + "id=" + id + ", name='" + name + '\''
				+ ", parentId=" + parentId + ", parentIds='" + parentIds + '\''
				+ ", available=" + available + '}';
	}
}