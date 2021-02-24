package com.grabme.dao;

public interface UserDAO {
	
	// Check phone in database
	public int checkPhone(String phone);

	// insert user
	public void insertUser(String name, String phone, int status);

}
