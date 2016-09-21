package com.shiro.service;

import java.util.List;
import java.util.Set;

import com.shiro.pojo.Resource;

/**
 * ��Դservice
 * @author  �⸣��
 */
public interface ResourceService {

	public Resource createResource(Resource resource);

	public Resource updateResource(Resource resource);

	public void deleteResource(Long resourceId);

	Resource findOne(Long resourceId);

	List<Resource> findAll();

	/**
	 * �õ���Դ��Ӧ��Ȩ���ַ���
	 * 
	 * @param resourceIds
	 * @return
	 */
	Set<String> findPermissions(Set<Long> resourceIds);

	/**
	 * �����û�Ȩ�޵õ��˵�
	 * 
	 * @param permissions
	 * @return
	 */
	List<Resource> findMenus(Set<String> permissions);
}