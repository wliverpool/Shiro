package com.shiro.service;

import com.shiro.dao.PermissionDao;
import com.shiro.pojo.Permission;

/**
 * 权限服务�?
 * @author 吴福�?
 *
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao;

    public PermissionDao getPermissionDao() {
		return permissionDao;
	}

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}