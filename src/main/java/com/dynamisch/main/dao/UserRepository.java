package com.dynamisch.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dynamisch.main.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
