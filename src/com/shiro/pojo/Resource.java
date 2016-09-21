package com.shiro.pojo;

import java.io.Serializable;

/**
 * ��Դ
 * @author �⸣��
 */
public class Resource implements Serializable {

	private static final long serialVersionUID = 2663672947024879534L;
	
	private Long id; // ���
	private String name; // ��Դ����
	private ResourceType type = ResourceType.menu; // ��Դ����
	private String url; // ��Դ·��
	private String permission; // Ȩ���ַ���
	private Long parentId; // �����
	private String parentIds; // ������б�
	private Boolean available = Boolean.FALSE;

	public static enum ResourceType {
		menu("�˵�"), button("��ť");

		private final String info;

		private ResourceType(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

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

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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

		Resource resource = (Resource) o;

		if (id != null ? !id.equals(resource.id) : resource.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Resource{" + "id=" + id + ", name='" + name + '\'' + ", type="
				+ type + ", permission='" + permission + '\'' + ", parentId="
				+ parentId + ", parentIds='" + parentIds + '\''
				+ ", available=" + available + '}';
	}
}