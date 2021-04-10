package com.grabme.dao;

import java.util.List;

import com.grabme.vo.UserVO;

public interface UserDAO {

	// Check user in database
	public int checkUser(String phone, int status);

	// insert user
	public void insertUser(String name, String phone, int status);

	// select user idx
	public int selectUserIdx(String phone, int status);
	
	// select User By Time
	public List<UserVO> selectUserByTime(int time_idx);
}
