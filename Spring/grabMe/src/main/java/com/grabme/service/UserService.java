package com.grabme.service;

import java.util.List;

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

	// select User By Time
	public List<UserVO> selectUserByTime(int time_idx);
	
	// delete user
	public void deleteUser(int idx);

}
