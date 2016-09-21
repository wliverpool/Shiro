package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AuthorityController {
	
	@RequestMapping("notneedauthorized")
	public String toLogin(){
		return "WEB-INF/jsp/unneedauthorized";
	}
	
	@RequestMapping("role")
	public String role(){
		Subject subject = SecurityUtils.getSubject();
        try {
        	 subject.checkRole("admin");
		} catch (AuthorizationException e) {
			return "WEB-INF/jsp/visitDenied";
		}
        return "WEB-INF/jsp/hasRole";
	}
	
	@RequestMapping("permission")
	public String permission(){
		Subject subject = SecurityUtils.getSubject();
        try {
        	 subject.checkPermission("user:create");
		} catch (AuthorizationException e) {
			return "WEB-INF/jsp/visitDenied";
		}
        return "WEB-INF/jsp/hasPermission";
	}
	
	@RequestMapping("authenticated")
	public String authenticated(){
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()) {
			return "WEB-INF/jsp/authenticated";
        } else {
        	return "WEB-INF/jsp/tologin";
        }
	}
	
	@RequestMapping("visitDenied")
	public String visitDenied(){
		return "WEB-INF/jsp/visitDenied";
	}
	
	@RequiresRoles("test")
	@RequestMapping("authorityRoleByAnnotation")
	public String authorityRoleByAnnotation(){
		return "WEB-INF/jsp/hasRole";
	}
	
	@RequiresUser()
	@RequestMapping("authorityUserByAnnotation")
	public String authorityUserByAnnotation(){
		return "WEB-INF/jsp/authenticated";
	}
	
	@RequiresPermissions("menu:create")
	@RequestMapping("authorityPermissionsByAnnotation")
	public String authorityPermissionsByAnnotation(){
		return "WEB-INF/jsp/hasPermission";
	}

}
