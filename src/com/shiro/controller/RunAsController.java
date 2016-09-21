package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shiro.pojo.User;
import com.shiro.service.UserRunAsService;
import com.shiro.service.UserService;

@Controller
@RequestMapping("/runas")
public class RunAsController {

    @Autowired
    private UserRunAsService userRunAsService;

    @Autowired
    private UserService userService;

    public UserRunAsService getUserRunAsService() {
		return userRunAsService;
	}


	public void setUserRunAsService(UserRunAsService userRunAsService) {
		this.userRunAsService = userRunAsService;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequiresRoles("admin")
	@RequestMapping
    public String runasList(Model model) {
    	
        model.addAttribute("toUserIds", userRunAsService.findToUserList());

        return "WEB-INF/jsp/runas";
    }


    @RequestMapping("/switchTo/{switchToUserId}")
    public String switchTo(@PathVariable("switchToUserId") Long switchToUserId,RedirectAttributes redirectAttributes) {

        Subject subject = SecurityUtils.getSubject();
        String userLoginUserName = (String)subject.getPrincipal();
        User loginUser = userService.findByUsername(userLoginUserName);
        
        User switchToUser = userService.findOne(switchToUserId);
        if(loginUser.equals(switchToUser)) {
            redirectAttributes.addFlashAttribute("msg", "自己不能切换到自己的身份");
            return "redirect:/runas";
        }

        subject.runAs(new SimplePrincipalCollection(switchToUser.getUsername(), ""));
        redirectAttributes.addFlashAttribute("msg", "操作成功");
        redirectAttributes.addFlashAttribute("needRefresh", "true");
        return "redirect:/index";
    }

    @RequestMapping("/switchBack")
    public String switchBack(RedirectAttributes redirectAttributes) {

        Subject subject = SecurityUtils.getSubject();

        if(subject.isRunAs()) {
           subject.releaseRunAs();
        }
        redirectAttributes.addFlashAttribute("msg", "操作成功");
        redirectAttributes.addFlashAttribute("needRefresh", "true");
        return "redirect:/index";
    }

}