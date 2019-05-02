package com.dynamisch.main.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dynamisch.main.dao.AdminDao;
import com.dynamisch.main.model.Admin;

@Repository
@Transactional
public class AdminLoginDao implements AdminDao{

	@Autowired
	EntityManagerFactory entityManagerFactory;

	
	@Override
	public boolean adminLoginValidate(Admin admin) {
		// TODO Auto-generated method stub
		Session session=entityManagerFactory.unwrap(SessionFactory.class).openSession();
		@SuppressWarnings("deprecation")
		Criteria ct=session.createCriteria(Admin.class);
		ct.add(Restrictions.eq("aemail",admin.getAemail()));
		ct.add(Restrictions.eq("apass", admin.getApass()));
		@SuppressWarnings("unchecked")
		List<Admin>result=ct.list();
		if(result.size()>0) {
			return true;
		}
		return false;
	}

}
