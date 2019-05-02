package com.dynamisch.main.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dynamisch.main.dao.LanguageRepository;
import com.dynamisch.main.dao.UserRepository;
import com.dynamisch.main.model.Language;
import com.dynamisch.main.model.User;
import com.dynamisch.main.service.UserService;

@Controller
public class UserController {

	@Autowired
	ServletContext context;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	LanguageRepository languageRepository;

	@GetMapping("/")
	public String userLogin(Model model) {
		model.addAttribute("user",new User());
		return "userLogin";				
	}
	
	@PostMapping("/userLogin")
	public String userLogin(User user,Model model,HttpServletRequest request) {
		boolean result=userService.validate(user);
		if(result==true) {
			HttpSession session=request.getSession();
			session.setAttribute("email",user.getEmail());
			return "redirect:/userList";
		}
		model.addAttribute("user",new User());
		return "userLogin";
		
	}
	
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user",new User());
		return "register";				
	}
	@PostMapping("create")
	public String create( User user) {
		Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
	    user.setDateCreated(strDate);
		userRepository.save(user);
		return "redirect:/";		
	}
	
	@GetMapping("userList")
	public String userList(Model model) {
		List<User>user=userRepository.findAll();
		model.addAttribute("user",user);
		return "userList";		
	}
	
	
	@GetMapping("/editUser/{userId}")
	public String editUser(@PathVariable("userId")int id,Model model) {
		User user=userRepository.findById(id).orElse(new User());
		model.addAttribute("user",user);
		return "editUser";
	}
	
	@PostMapping("updateUser")
	public String update(User user) {
		Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
	    user.setDateUpdated(strDate);
		userRepository.save(user);
		return "redirect:/userList";		
	}
	@GetMapping("deleteUser/{userId}")
	public String deleteUser(@PathVariable("userId") int id,User user) {
		userRepository.delete(user);
		return "redirect:/userList";	
	}
	@PostMapping("/multiSearch")
    public String getAllUser(Model model,@RequestParam(required = false) String filter){
		System.out.println("filter"+filter);
        if(filter == null || filter=="" || filter.length()==0) {
            System.out.println("null");
            model.addAttribute("user", new User());
            model.addAttribute("user", userRepository.findAll());
           
           }else {
               System.out.println("not null");
               model.addAttribute("user",new User());
               model.addAttribute("user", userService.getAllUser(filter));
           }
        System.out.println("not null"+ userService.getAllUser(filter));
        return "userList";
        
    }
	
	
	@GetMapping("/createPdf")
	public void userListPdf(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<User>user=userRepository.findAll();
		boolean isFlag = userService.creatPdf(user, context, request, response);
		System.out.println("isFlag.."+isFlag);
		if(isFlag) {
			System.out.println();
			String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"user"+".pdf");
			filedownload(fullPath, response, "user.pdf");
			
		}		
	}
	
	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int  BUFFER_SIZE = 4096;
		if(file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename="+fileName);
				ServletOutputStream outputStream = response.getOutputStream();
				byte [] buffer = new byte[BUFFER_SIZE];
				int byteRead =-1;
				
				while ((byteRead = inputStream.read(buffer))!=-1) {
					outputStream.write(buffer,0,byteRead);
				}
				
				inputStream.close();
				outputStream.close();
				//file.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@GetMapping("/createCsv")
    public void userListCSV(Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<User>user=userRepository.findAll();
        boolean isFlag = userService.creatCSV(user, context);
        System.out.println("isFlag.."+isFlag);
        if(isFlag) {
            System.out.println();
            String fullPath = request.getServletContext().getRealPath("/resources/reports/"+"user.csv");
            filedownload(fullPath, response, "user.csv");
            
        }        
    }
	
	@GetMapping("/international")
    public String getInternationalPage() {
        return "international";
    }
	@GetMapping("languageList")
public String LanguageList(Model model,HttpServletRequest request) {
List<Language>lang=languageRepository.findAll();
HttpSession session=request.getSession();
session.setAttribute("lang",lang);
System.out.println("languageList"+" "+lang);
model.addAttribute("lang",lang);
		return "redirect:/userList";	
	}
}
