package com.shiro.test;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.shiro.permission.BitPermission;
import com.shiro.util.SecurityUtil;

public class BitPermissionTest {
	
	@Test
	public void testBitPermission(){
		BitPermission b = new BitPermission("+user+10");
		//新增权限
		BitPermission add = new BitPermission("+user+1");
		//无新增权限
		assertTrue(!b.implies(add));
		//修改权限
		BitPermission edit = new BitPermission("+user+2");
		//有修改权限
		assertTrue(b.implies(edit));
		//删除权限
		BitPermission delete = new BitPermission("+user+4");
		//无删除权限
		assertTrue(!b.implies(delete));
		//查看权限
		BitPermission view = new BitPermission("+user+8");
		//有查看权限
		assertTrue(b.implies(view));
	}
	
	@Test
	public void testIsPermitted(){
		SecurityUtil.login("classpath:shiro-jdbc-authorizer.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		//断言有user1和user2资源的update权限
		assertTrue(subject.isPermitted("user1:update"));
		assertTrue(subject.isPermitted("user2:update"));
		
		//通过二进制位的方式表示权限
		assertTrue(subject.isPermitted("+user1+2"));//新增权限
		assertTrue(subject.isPermitted("+user1+8"));//查看权限
		assertTrue(subject.isPermitted("+user2+10"));//新增及查看
		assertFalse(subject.isPermitted("+user1+4"));//没有删除权限
		//通过MyRolePermissionResolver 解析得到的权限
		assertTrue(subject.isPermitted("menu:view"));
	}

}
