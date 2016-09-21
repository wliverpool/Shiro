package com.shiro.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * �Զ���PermissionResolver�ܹ�����BitPermission��WildcardPermission������Ȩ������
 * @author �⸣��
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
