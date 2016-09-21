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
		//使用classpath中的shiro.ini初始化securityManager工厂
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFilePath);
		//得到SecurityManager实例并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//得到需要被验证的主体subject(可以是一个用户,也可以是其他余应用交互的对象例如网络爬虫)
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		
		//主体进行身份验证
		subject.login(token);
		
	}

}
