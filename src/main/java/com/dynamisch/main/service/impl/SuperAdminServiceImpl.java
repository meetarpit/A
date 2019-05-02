package com.dynamisch.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dynamisch.main.dao.SuperAdminLogin;
import com.dynamisch.main.model.SuperAdmin;
import com.dynamisch.main.service.SuperAdminService;

@Service
@Transactional
public class SuperAdminServiceImpl implements SuperAdminService{

	@Autowired
	SuperAdminLogin superAdminLogin;
	
	@Override
	public boolean validate(SuperAdmin superAdmin) {
		// TODO Auto-generated method stub
		return superAdminLogin.validate(superAdmin);
	}

}
