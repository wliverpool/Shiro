package com.shiro.test;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.shiro.util.SecurityUtil;

public class LoginLogoutTest {
	
	@Test
	public void testHelloWorld(){
		SecurityUtil.login("classpath:shiro.ini","zhang","123");
		Subject subject = SecurityUtils.getSubject();
		//主体身份验证成功
		assertTrue(subject.isAuthenticated());
		//主体退出注销身份
		subject.logout();
	}
	
	
	
	
	
}
