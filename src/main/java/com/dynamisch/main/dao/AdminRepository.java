package com.dynamisch.main.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dynamisch.main.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
