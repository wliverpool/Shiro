package com.shiro.test;

import static org.junit.Assert.assertTrue;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.shiro.util.SecurityUtil;

public class AllSuccessTest {
	
	/**
	 * 测试必须在所有realm中验证通过
	 */
	@Test
	public void testAllSuccess(){
		try {
			SecurityUtil.login("classpath:shiro-realm-allsuccess.ini","zhang","123");
		} catch (AuthenticationException e) {
			// TODO: handle exception
		}
		Subject subject = SecurityUtils.getSubject();
		assertTrue(!subject.isAuthenticated());
	}

}
