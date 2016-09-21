package com.shiro.dao;

import com.shiro.pojo.Permission;

/**
 * 权限dao接口
 * @author 吴福�?
 *
 */
public interface PermissionDao {

	/**
	 * 添加权限
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
