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
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realmehcache.ini");
        //2、得到SecurityManager实例 并绑定给SecurityUtils
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
        //退出时请解除绑定Subject到线程 否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }

    @Test
    public void testClearCachedAuthenticationInfo() {
    	//测试验证缓存
        login("zhang", "1234");
        userService.changePassword(1L, "12341");
        Subject subject = SecurityUtils.getSubject();
        assertTrue(subject.isAuthenticated());
        //修改密码之后需要清空缓存否则验证身份会不通过
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
    	//测试授权缓存
        login("zhang", "1234");
        Subject subject = SecurityUtils.getSubject();
        assertTrue(!subject.hasRole("admin"));
        //userService.correlationRoles(1L, 1L);
        //修改授权之后需要清空缓存的授权
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
        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
    }


}
