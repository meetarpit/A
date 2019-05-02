package com.dynamisch.main.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dynamisch.main.dao.AdminDao;
import com.dynamisch.main.model.Admin;
import com.dynamisch.main.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	AdminDao adminDao;

	@Override
	public boolean adminLoginValidate(Admin admin) {	 
		// TODO Auto-generated method stub
		return adminDao.adminLoginValidate(admin);
	}

}
