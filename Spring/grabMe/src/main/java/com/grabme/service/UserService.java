package com.grabme.service;

import com.grabme.vo.UserVO;

public interface UserService {

	// Check user in database
	public int checkUser(String phone, int status);

	// 인증번호 생성
	public String randomNumber();

	// select user idx
	public int selectUserIdx(String phone, int status);

	// insert user
	public void insertUser(String name, String phone, int status);

	// select user
	public UserVO selectUser(int idx);

}
