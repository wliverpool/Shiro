package com.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.shiro.realm.UsernamePasswordCaptchaToken;

/**
 * �Զ������֤������,���ڼ�����֤����֤
 * 
 * @author Administrator
 * 
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	//��������֤��Ĳ�������
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {

		return captchaParam;

	}

	protected String getCaptcha(ServletRequest request) {
		//�������л�ȡ��֤����Ϣ
		return WebUtils.getCleanParam(request, getCaptchaParam());

	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		//��request�����л�ȡ������û���,�������֤����Ϣ���ڴ�������֤�ĵ�½token
		String username = getUsername(request);

		String password = getPassword(request);

		String captcha = getCaptcha(request);

		boolean rememberMe = isRememberMe(request);

		String host = getHost(request);

		return new UsernamePasswordCaptchaToken(username,password.toCharArray(), rememberMe, host, captcha);

	}
}
