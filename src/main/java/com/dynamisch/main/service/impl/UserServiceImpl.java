package com.dynamisch.main.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynamisch.main.dao.UserDao;
import com.dynamisch.main.model.User;
import com.dynamisch.main.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> getAllUser(String filter) {
		// TODO Auto-generated method stub
		return userDao.getAllUser(filter);
	}

	@Override
	public boolean validate(User user) {
		// TODO Auto-generated method stub
		return userDao.validate(user);
	}

	@Override
	public boolean creatPdf(List<User> user, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		return userDao.creatPdf(user, context, request, response);
	}

	@Override
	public boolean creatCSV(List<User> user, ServletContext context) {
		// TODO Auto-generated method stub
		return userDao.creatCSV(user, context);
	}

}
