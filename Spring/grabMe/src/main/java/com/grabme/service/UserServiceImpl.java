package com.grabme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.UserDAO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO dao;

	// Check phone in database
	@Override
	public int checkPhone(String phone) {
		return dao.checkPhone(phone);
	}

	// insert user
	@Override
	public void insertUser(String name, String phone, int status) {
		dao.insertUser(name, phone, status);

	}

}
