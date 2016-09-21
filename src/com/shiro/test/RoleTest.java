package com.shiro.test;


import static org.junit.Assert.*;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;

import org.junit.Test;

import com.shiro.util.SecurityUtil;

public class RoleTest {
	
	@Test
	public void testHasRole(){
		SecurityUtil.login("classpath:shiro-role.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		//�ж�ӵ�н�ɫrole1
		assertTrue(subject.hasAllRoles(Arrays.asList("role1")));
		//ӵ�н�ɫrole1��role2
		assertTrue(subject.hasAllRoles(Arrays.asList("role1","role2")));
		//ӵ�н�ɫrole1��role2��role3
		assertTrue(!subject.hasAllRoles(Arrays.asList("role1","role2","role3")));
		boolean[] result = subject.hasRoles(Arrays.asList("role1","role2","role3"));
		assertTrue(result[0]);
		assertTrue(result[1]);
		assertTrue(!result[2]);
	} 
	
	@Test(expected=UnauthorizedException.class)
	public void testCheckRole(){
		SecurityUtil.login("classpath:shiro-role.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		//ӵ�н�ɫrole1
		subject.checkRole("role1");
		//ӵ�н�ɫrole1,role3  ��û�н�ɫʱ���׳��쳣UnauthorizedException
		subject.checkRoles("role1","role3");
	}
	
	@Test
	public void testIsPermitted(){
		SecurityUtil.login("classpath:shiro-role.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		//�ж���Ȩ��
		assertTrue(subject.isPermitted("user:create"));
		assertTrue(subject.isPermittedAll("user:create","user:delete"));
		//��Ȩ��
		assertTrue(!subject.isPermittedAll("user:view"));
		
	}
	
	@Test(expected=UnauthorizedException.class)
	public void testCheckPermitted(){
		SecurityUtil.login("classpath:shiro-role.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		//�ж���Ȩ��
		subject.checkPermission("user:create");
		subject.checkPermissions("user:create","user:delete");
		//��Ȩ��
		subject.checkPermissions("user:view");
	}
	
	

}
