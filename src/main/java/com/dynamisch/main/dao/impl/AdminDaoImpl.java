package com.dynamisch.main.dao.impl;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dynamisch.main.dao.SuperAdminLogin;
import com.dynamisch.main.model.SuperAdmin;

@Repository
@Transactional
public class AdminDaoImpl implements SuperAdminLogin{
	
	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	public boolean validate(SuperAdmin superAdmin) {
		// TODO Auto-generated method stub
		Session session=entityManagerFactory.unwrap(SessionFactory.class).openSession();
		@SuppressWarnings("deprecation")
		Criteria ct=session.createCriteria(SuperAdmin.class);
		ct.add(Restrictions.eq("name", superAdmin.getName()));
		ct.add(Restrictions.eq("password",superAdmin.getPassword()));
		@SuppressWarnings("unchecked")
		List<SuperAdmin>result=ct.list();
		if(result.size()>0) {
			return true;
		}
		return false;
	}

}
