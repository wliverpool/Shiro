package com.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.shiro.realm.UsernamePasswordCaptchaToken;

/**
 * 自定义表单验证过滤器,用于集成验证码验证
 * 
 * @author Administrator
 * 
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
	//请求中验证码的参数名称
	private String captchaParam = DEFAULT_CAPTCHA_PARAM;

	public String getCaptchaParam() {

		return captchaParam;

	}

	protected String getCaptcha(ServletRequest request) {
		//从请求中获取验证码信息
		return WebUtils.getCleanParam(request, getCaptchaParam());

	}

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		//从request请求中获取输入的用户名,密码和验证码信息用于创建待验证的登陆token
		String username = getUsername(request);

		String password = getPassword(request);

		String captcha = getCaptcha(request);

		boolean rememberMe = isRememberMe(request);

		String host = getHost(request);

		return new UsernamePasswordCaptchaToken(username,password.toCharArray(), rememberMe, host, captcha);

	}
}
