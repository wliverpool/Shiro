package com.shiro.permission;

import java.util.Arrays;
import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * �Զ����ɫȨ�޽�����,���ڸ��ݽ�ɫ�ַ����������õ�Ȩ�޼��ϡ�
 * @author �⸣��
 *
 */
public class MyRolePermissionResolver implements RolePermissionResolver {
	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		//�����role1�Ľ�ɫĬ�ϸ�menu��Դ������Ȩ��
		if ("role1".equals(roleString)) {
			return Arrays.asList((Permission) new WildcardPermission("menu:*"));
		}
		return null;
	}
}
