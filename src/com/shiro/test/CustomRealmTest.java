package com.shiro.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.shiro.pojo.User;
import com.shiro.util.SecurityUtil;

public class CustomRealmTest {
	
	/**
	 * �����Զ���realm
	 */
	@Test
	public void testCustomRealm(){
		try {
			SecurityUtil.login("classpath:shiro-customrealm.ini","zhang","321");
		} catch (AuthenticationException e) {
			// TODO: handle exception
		}
		Subject subject = SecurityUtils.getSubject();
		assertTrue(!subject.isAuthenticated());
		
		
		//��ΪRealm��û�н�����֤�������൱��ÿ��Realm�������֤�ɹ���
		SecurityUtil.login("classpath:shiro-customrealm.ini", "zhang", "123");
		subject = SecurityUtils.getSubject();
        //��ȡPrimary Principal������һ����
        Object primaryPrincipal1 = subject.getPrincipal();
        PrincipalCollection princialCollection = subject.getPrincipals();
        Object primaryPrincipal2 = princialCollection.getPrimaryPrincipal();

        //������Ϊ���Realm��������Principal�����Դ˴��������ĸ��ǲ�ȷ����
        assertEquals(primaryPrincipal1, primaryPrincipal2);


        //����myRealm1,myRealm2
        Set<String> realmNames = princialCollection.getRealmNames();
        System.out.println(realmNames);

        //��ΪMyRealm1��MyRealm2���ص�ƾ�ݶ���zhang������������
        Set<Object> principals = princialCollection.asSet(); //asList��asSet�Ľ��һ��
        System.out.println(principals);

        //����Realm���ֻ�ȡ
        Collection<User> users = princialCollection.fromRealm("myRealm1");
        System.out.println(users);
	}

}
