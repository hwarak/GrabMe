package com.grabme.service;

public interface UserService {
	
	// Check phone in database
	public int checkPhone(String phone);

	// insert user
	public void insertUser(String name, String phone, int status);

}
