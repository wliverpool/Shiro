package com.shiro.test;

import static org.junit.Assert.*;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.shiro.realm.UserCustomJdbcRealm;
import com.shiro.service.UserService;
import com.shiro.service.UserServiceImpl;

public class RealmCacheTest {
	
	protected UserService userService = new UserServiceImpl();
	
	@Before
	public void setUp(){
		//1����ȡSecurityManager�������˴�ʹ��Ini�����ļ���ʼ��SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realmehcache.ini");
        //2���õ�SecurityManagerʵ�� ���󶨸�SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
	}
	
	@After
    public void tearDown() throws Exception {
        userService.changePassword(1L, "1234");
        //userService.uncorrelationRoles(1L, 1L);
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserCustomJdbcRealm userRealm = (UserCustomJdbcRealm) securityManager.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        userRealm.clearCache(subject.getPrincipals());
        //�˳�ʱ������Subject���߳� ������´β������Ӱ��
        ThreadContext.unbindSubject();
    }

    @Test
    public void testClearCachedAuthenticationInfo() {
    	//������֤����
        login("zhang", "1234");
        userService.changePassword(1L, "12341");
        Subject subject = SecurityUtils.getSubject();
        assertTrue(subject.isAuthenticated());
        //�޸�����֮����Ҫ��ջ��������֤��ݻ᲻ͨ��
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserCustomJdbcRealm userRealm = (UserCustomJdbcRealm) securityManager.getRealms().iterator().next();
        subject = SecurityUtils.getSubject();
        userRealm.clearCachedAuthenticationInfo(subject.getPrincipals());

        login("zhang", "12341");
        subject = SecurityUtils.getSubject();
        assertTrue(subject.isAuthenticated());
    }
    
    @Test
    public void testClearCachedAuthorizationInfo() {
    	//������Ȩ����
        login("zhang", "1234");
        Subject subject = SecurityUtils.getSubject();
        assertTrue(!subject.hasRole("admin"));
        //userService.correlationRoles(1L, 1L);
        //�޸���Ȩ֮����Ҫ��ջ������Ȩ
        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserCustomJdbcRealm userRealm = (UserCustomJdbcRealm) securityManager.getRealms().iterator().next();
        userRealm.clearCachedAuthorizationInfo(subject.getPrincipals());

        assertTrue(subject.hasRole("admin"));
    }
    
    @Test
    public void testClearCache() {
        login("zhang", "1234");
        Subject subject = SecurityUtils.getSubject();
        assertTrue(subject.isAuthenticated());
        assertTrue(!subject.hasRole("admin"));

        userService.changePassword(1L, "12341");
        //userService.correlationRoles(1L, 1L);

        RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        UserCustomJdbcRealm userRealm = (UserCustomJdbcRealm) securityManager.getRealms().iterator().next();
        userRealm.clearCache(subject.getPrincipals());

        login("zhang", "12341");
        assertTrue(subject.isAuthenticated());
        assertTrue(subject.hasRole("admin"));
    }
    
    protected void login(String username, String password) {
        //3���õ�Subject�������û���/���������֤Token�����û����/ƾ֤��
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
    }


}
