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
	 * 测试自定义realm
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
		
		
		//因为Realm里没有进行验证，所以相当于每个Realm都身份验证成功了
		SecurityUtil.login("classpath:shiro-customrealm.ini", "zhang", "123");
		subject = SecurityUtils.getSubject();
        //获取Primary Principal（即第一个）
        Object primaryPrincipal1 = subject.getPrincipal();
        PrincipalCollection princialCollection = subject.getPrincipals();
        Object primaryPrincipal2 = princialCollection.getPrimaryPrincipal();

        //但是因为多个Realm都返回了Principal，所以此处到底是哪个是不确定的
        assertEquals(primaryPrincipal1, primaryPrincipal2);


        //返回myRealm1,myRealm2
        Set<String> realmNames = princialCollection.getRealmNames();
        System.out.println(realmNames);

        //因为MyRealm1和MyRealm2返回的凭据都是zhang，所以排重了
        Set<Object> principals = princialCollection.asSet(); //asList和asSet的结果一样
        System.out.println(principals);

        //根据Realm名字获取
        Collection<User> users = princialCollection.fromRealm("myRealm1");
        System.out.println(users);
	}

}
