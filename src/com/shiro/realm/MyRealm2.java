package com.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * 自定义基于用户名和密码验证的realm
 * @author 吴福明
 *
 */
public class MyRealm2 implements Realm {

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)throws AuthenticationException {
		String username = (String)token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		if(!"liu".equals(username)){
			throw new UnknownAccountException();
		}
		if(!"432".equals(password)){
			throw new IncorrectCredentialsException();
		}
		return new SimpleAuthenticationInfo("liu", "432",getName());
	}

	@Override
	public String getName() {
		return "myRealm2";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

}
