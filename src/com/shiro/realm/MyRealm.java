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
 * �Զ�������û�����������֤��realm
 * @author �⸣��
 *
 */
public class MyRealm implements Realm {

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)throws AuthenticationException {
		String username = (String)token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		if(!"zhang".equals(username)){
			throw new UnknownAccountException();
		}
		if(!"123".equals(password)){
			throw new IncorrectCredentialsException();
		}
		return new SimpleAuthenticationInfo("zhang","123",getName());
	}

	@Override
	public String getName() {
		return "myRealm1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

}
