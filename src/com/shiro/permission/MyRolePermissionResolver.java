package com.shiro.permission;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * 自定义角色权限解析器,用于根据角色字符串来解析得到权限集合。
 * @author 吴福明
 *
 */
public class MyRolePermissionResolver implements RolePermissionResolver {
	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		//如果有role1的角色默认给menu资源的所有权限
		if ("role1".equals(roleString)) {
			return Arrays.asList((Permission) new WildcardPermission("menu:*"));
		}
		return null;
	}
}
