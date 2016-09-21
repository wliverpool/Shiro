package com.shiro.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiro.exception.CaptchaException;
import com.shiro.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("login")
	public String login(HttpServletRequest request,Model model) {
		
		String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(CaptchaException.class.getName().equals(exceptionClassName)) {
            error = "验证码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        
    	model.addAttribute("error", error);
    	
    	return "WEB-INF/jsp/login";
		
	}
	
	@RequestMapping("index")
	public String index(Model model){
		Subject subject = SecurityUtils.getSubject();
		model.addAttribute("isRunas", subject.isRunAs());
        if(subject.isRunAs()) {
            String previousUsername = (String)subject.getPreviousPrincipals().getPrimaryPrincipal();
            model.addAttribute("previousUsername", previousUsername);
        }
		return "WEB-INF/jsp/loginSuccess";
	}
	
	@RequestMapping("logout")
	public String logout(){
		SecurityUtils.getSubject().logout();
		return "WEB-INF/jsp/logoutSuccess";
	}

}
