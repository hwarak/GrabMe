package com.grabme.dao;

import com.grabme.vo.UserVO;

public interface UserDAO {

	// Check user in database
	public int checkUser(String phone, int status);

	// insert user
	public void insertUser(String name, String phone, int status);

	// select user idx
	public int selectUserIdx(String phone, int status);
	
	// select user
	public UserVO selectUser(int idx);
	
}
