package com.shiro.test;

import static org.junit.Assert.assertTrue;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import com.shiro.util.SecurityUtil;

public class JdbcRealmTest {
	
	/**
	 * ≤‚ ‘jdbc realm
	 */
	@Test
	public void testJdbcRealm(){
		SecurityUtil.login("classpath:shiro-jdbcrealm.ini","zhang","123");
		Subject subject = SecurityUtils.getSubject();
		assertTrue(subject.isAuthenticated());
		subject.logout();
	}

}
