package com.shiro.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * 自定义PermissionResolver能够解析BitPermission和WildcardPermission这两种权限类型
 * @author 吴福明
 *
 */
public class BitAndWildPermissionResolver implements PermissionResolver {

	@Override
	public Permission resolvePermission(String permissionString) {
		if(permissionString.startsWith("+")){
			return new BitPermission(permissionString);
		}
		return new WildcardPermission(permissionString);
	}

}
