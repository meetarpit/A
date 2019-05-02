package com.dynamisch.main.controller;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.dynamisch.main.dao.AdminRepository;
import com.dynamisch.main.model.Admin;
import com.dynamisch.main.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	AdminRepository adminRepository;
	
	
	@Autowired
	AdminService adminService;
	
	
	@GetMapping("/adminLoginPage")
	public String adminLogin(Model model,HttpServletRequest request) {
		
		 Cookie ck[]=request.getCookies();  
		  if(ck!=null) {
			  for (Cookie cookie : ck) {
				  if(cookie.getName().equals("cookuser")) {
					  String name=cookie.getValue(); 
					  request.setAttribute("name",name); 
			           }
				  else if(cookie.getName().equals("cookpass")) {
					  String apass=cookie.getValue(); 
					  request.setAttribute("pass",apass); 
				  }
				  else if(cookie.getName().equals("cookrem")){
					  String rem=cookie.getValue(); 
					  request.setAttribute("rem",rem);
					  System.out.println("rem123"+rem);
				  }
			  }
			
		  }
	
	        
		model.addAttribute("admin",new Admin());
		return "adminLoginPage";
	}
	
	@PostMapping("/adminLogin")
public String adminLoginProcess(Admin admin,Model model,HttpServletRequest request,HttpServletResponse response) {
		boolean result=adminService.adminLoginValidate(admin);
		String rem=request.getParameter("RememberMe");
		System.out.println("rem"+rem);
		boolean isRem = false;
		if(rem!=null && rem.length()>2) {
            isRem = true;
        }
		if(result==true) {
			HttpSession session=request.getSession();
		  session.setAttribute("aemail",admin.getAemail());
			session.setAttribute("email","");
			   if(isRem == true) {
				   request.setAttribute("isRem",isRem);
			Cookie cUserName = new Cookie("cookuser", admin.getAemail());
	          Cookie cPassword = new Cookie("cookpass",admin.getApass());
			 Cookie cremember=new Cookie("cookrem",rem); 
	          cUserName.setMaxAge(60 * 60 * 24 * 15);//15 days
	          cPassword.setMaxAge(60 * 60 * 24 * 15);
			 cremember.setMaxAge(60 * 60 * 24 * 15); 
	          response.addCookie(cUserName);
	          response.addCookie(cPassword);
			 response.addCookie(cremember);
		}
			   else {
				   Cookie cUserName = new Cookie("cookuser", "");
			          Cookie cPassword = new Cookie("cookpass","");
					 Cookie cremember=new Cookie("cookrem",""); 
					 response.addCookie(cUserName);
			          response.addCookie(cPassword);
					 response.addCookie(cremember);
			   }
			   return "redirect:/userList";
		}
		model.addAttribute("admin",new Admin());
	return "adminLoginPage";
		
	}
	
	@GetMapping("/adminPage")
	public String login(Model model) {
	model.addAttribute("admin",new Admin());
		return "admin";	
	}
	@PostMapping("adminRegister")
	public String adminRegister(Admin admin,HttpServletRequest request) {
		System.out.println("value"+admin.getRoles().getRolename());
		HttpSession session=request.getSession();
		session.setAttribute("isRole",admin.getRoles().getRolename());
		if(admin.getRoles().getRolename().equals("MANAGER")) {
			admin.setIsRoleId(0);
		}
		else if(admin.getRoles().getRolename().equals("CEO")) {
			admin.setIsRoleId(1);
		}
		else if(admin.getRoles().getRolename().equals("CTO")) {
			admin.setIsRoleId(2);
		}
		adminRepository.save(admin);
		return "redirect:/adminList";		
	}
	@GetMapping("adminList")
	public String getAllAdmin(Model model) {
		List<Admin>admin=adminRepository.findAll();
		model.addAttribute("admin",admin);
		return  "adminList";		
	}
	@GetMapping("/editAdmin/{aid}")
	public String editAdmin(@PathVariable("aid") int id,Model model) {
		Admin admin=adminRepository.findById(id).orElse(new Admin());
		model.addAttribute("admin",admin);
		return "editAdmin";	
	}
	
	@PostMapping("adminUpdate")
	public String adminUpdate(Admin admin) {
	if(admin.getRoles().getRolename().equals("MANAGER")) {
		admin.setIsRoleId(0);
	}
	else if(admin.getRoles().getRolename().equals("CEO")) {
		admin.setIsRoleId(1);
	}
	else if(admin.getRoles().getRolename().equals("CTO")) {
		admin.setIsRoleId(2);
	}
	adminRepository.save(admin);
		return "redirect:/adminList";
	}
	
	
	@GetMapping("delete/{aid}")
	public String delete(@PathVariable("aid")int id, Admin admin) {
	adminRepository.delete(admin);
	return "redirect:/adminList";
	}
	@ModelAttribute("roles")
	public Map<String, String> getRoles() {
		Map<String, String>roles = new HashMap<String, String>();
		roles.put("MANAGER","MANAGER");
		roles.put("CEO","CEO");
		roles.put("CTO","CTO");
		return roles;
	}
	@GetMapping("logoutForAdmin")
	public String logoutDo(HttpServletRequest request,HttpServletResponse response,Model model){
		
		    HttpSession session= request.getSession(false);
		        if(session != null) {
		           
		        	
		            Cookie ck[]=request.getCookies(); 
		         
		            session.invalidate();
			/*
			 * String5 name=ck[0].getValue(); System.out.println("name"+name);
			 * request.setAttribute("name",name); String apass=ck[1].getValue();
			 * request.setAttribute("pass",apass);
			 */
		            for (Cookie cookie : ck) {
						  if(cookie.getName().equals("cookuser")) {
							  String name=cookie.getValue(); 
							  request.setAttribute("name",name); 
					           }
						  else if(cookie.getName().equals("cookpass")) {
							  String apass=cookie.getValue(); 
							  request.setAttribute("pass",apass); 
						  }
						  else if(cookie.getName().equals("cookrem")){
							  String rem=cookie.getValue(); 
							  request.setAttribute("rem",rem);
							  System.out.println("rem123"+rem);
						  }
		            }
		        }
		        model.addAttribute("admin",new Admin());
		    return "redirect:/adminLoginPage";
		}

	
}
