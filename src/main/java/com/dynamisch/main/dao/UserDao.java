package com.dynamisch.main.dao;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dynamisch.main.model.User;

public interface UserDao {
	
	public List<User> getAllUser(String filter);
	
	public boolean validate(User user);
	
	public boolean creatPdf(List<User> user, ServletContext context, HttpServletRequest request, HttpServletResponse response);

	public boolean creatCSV(List<User> user, ServletContext context);
	
}
