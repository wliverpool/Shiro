package com.shiro.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码验证不通过异常
 * @author 吴福明
 *
 */
public class CaptchaException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public CaptchaException() {

		super();

	}

	public CaptchaException(String message, Throwable cause) {

		super(message, cause);

	}

	public CaptchaException(String message) {

		super(message);

	}

	public CaptchaException(Throwable cause) {

		super(cause);

	}

}