package com.shiro.test;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.shiro.util.SecurityUtil;

public class AtLeastOneSuccessTest {
	
	/**
	 * 测试只须在所有realm中有一个验证通过即可
	 */
	@Test
	public void testAllSuccess(){
		try {
			SecurityUtil.login("classpath:shiro-realm-atleastonsuccess.ini","zhang","123");
		} catch (AuthenticationException e) {
			// TODO: handle exception
		}
		Subject subject = SecurityUtils.getSubject();
		assertTrue(subject.isAuthenticated());
		PrincipalCollection collections = subject.getPrincipals();
		assertEquals(1, collections.asList().size());
		for(Object p : collections.asList()){
			System.out.println((String)p);
		}
	}

}
