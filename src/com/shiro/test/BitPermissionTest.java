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
		//����Ȩ��
		BitPermission add = new BitPermission("+user+1");
		//������Ȩ��
		assertTrue(!b.implies(add));
		//�޸�Ȩ��
		BitPermission edit = new BitPermission("+user+2");
		//���޸�Ȩ��
		assertTrue(b.implies(edit));
		//ɾ��Ȩ��
		BitPermission delete = new BitPermission("+user+4");
		//��ɾ��Ȩ��
		assertTrue(!b.implies(delete));
		//�鿴Ȩ��
		BitPermission view = new BitPermission("+user+8");
		//�в鿴Ȩ��
		assertTrue(b.implies(view));
	}
	
	@Test
	public void testIsPermitted(){
		SecurityUtil.login("classpath:shiro-jdbc-authorizer.ini", "zhang", "123");
		Subject subject = SecurityUtils.getSubject();
		//������user1��user2��Դ��updateȨ��
		assertTrue(subject.isPermitted("user1:update"));
		assertTrue(subject.isPermitted("user2:update"));
		
		//ͨ��������λ�ķ�ʽ��ʾȨ��
		assertTrue(subject.isPermitted("+user1+2"));//����Ȩ��
		assertTrue(subject.isPermitted("+user1+8"));//�鿴Ȩ��
		assertTrue(subject.isPermitted("+user2+10"));//�������鿴
		assertFalse(subject.isPermitted("+user1+4"));//û��ɾ��Ȩ��
		//ͨ��MyRolePermissionResolver �����õ���Ȩ��
		assertTrue(subject.isPermitted("menu:view"));
	}

}
