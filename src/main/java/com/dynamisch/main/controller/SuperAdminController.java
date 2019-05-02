package com.dynamisch.main.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dynamisch.main.model.SuperAdmin;
import com.dynamisch.main.service.SuperAdminService;

@Controller
public class SuperAdminController {
	
	@Autowired
	SuperAdminService superAdminService;

	@GetMapping("/SuperAdmin")
	public String login(Model model) {
		model.addAttribute("super",new SuperAdmin());
		return "superAdminlogin";	
	}
	@PostMapping("/superAdminloginProcess")
	public String superAdminDoLogin(Model model,SuperAdmin superAdmin,HttpServletRequest request) {
		boolean result=superAdminService.validate(superAdmin);
		if(result==true) {
			HttpSession session=request.getSession();
			  session.setAttribute("email",superAdmin.getName());
			  session.setAttribute("aemail","");
			return "redirect:/userList";
		}
		model.addAttribute("super",new SuperAdmin());
		return "superAdminlogin";	
	}
	@GetMapping("logoutForSuperAdmin")	
	public String logoutDo(HttpServletRequest request,HttpServletResponse response,Model model){
		HttpSession session= request.getSession(false);
		    SecurityContextHolder.clearContext();
		         session= request.getSession(false);
		        if(session != null) {
		            session.invalidate();
		        }
		        for(Cookie cookie : request.getCookies()) {
		            cookie.setMaxAge(0);
		        }
		        model.addAttribute("super",new SuperAdmin());
		    return "superAdminlogin";
		}

}
