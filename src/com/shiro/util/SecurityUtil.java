package com.shiro.util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;


public class SecurityUtil {
	
	public static void login(String configFilePath,String username,String password)throws AuthenticationException{
		//ʹ��classpath�е�shiro.ini��ʼ��securityManager����
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFilePath);
		//�õ�SecurityManagerʵ�����󶨸�SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//�õ���Ҫ����֤������subject(������һ���û�,Ҳ������������Ӧ�ý����Ķ���������������)
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		
		//������������֤
		subject.login(token);
		
	}

}
