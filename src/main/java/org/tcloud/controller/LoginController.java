package org.tcloud.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tcloud.service.LoginService;

@Controller("loginController")  
public class LoginController {
	
	private LoginService loginService;
	
	public LoginService getLoginService() {
		return loginService;
	}

	@Resource
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login(HttpServletRequest request, Model modelMap) {
		return "admin/login";
	}
	
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String loginIn(@RequestParam(value="name") String userName, 
			@RequestParam(value="password") String password,
			HttpServletRequest request){
		boolean result = false;
		try {
			result=loginService.loginIn(userName, password, request);
		} catch (Exception e) {
			request.setAttribute("msg", "服务器忙...");
		}
		if(result){
			return "redirect:/admin";
		}
			return "redirect:/login";
	}
		
	@RequestMapping(value="/logout" ,method=RequestMethod.GET)
	public String loginOut(){
		if(loginService.loginOut())
			return "redirect:/login";
		else
			return "redirect:/admin";
	}
}
