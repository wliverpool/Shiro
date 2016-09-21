package com.shiro.service;

import com.shiro.pojo.Permission;

/**
 * 权限的服务接�?
 * @author 吴福�?
 *
 */
public interface PermissionService {
	
	/**
	 * 创建权限
	 * @param permission  权限
	 * @return   权限
	 */
	public Permission createPermission(Permission permission);
	
	/**
	 * 删除权限
	 * @param permissionId  权限id
	 */
	public void deletePermission(Long permissionId);

}
