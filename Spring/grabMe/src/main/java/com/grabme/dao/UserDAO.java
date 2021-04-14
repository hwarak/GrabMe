package com.grabme.dao;

import java.util.List;

import com.grabme.vo.UserVO;

public interface UserDAO {

	// Check user in database
	public int checkUser(String user_phone, int user_status);

	// insert user
	public void insertUser(String user_name, String user_phone, int user_status);

	// select user idx
	public int selectUserIdx(String user_phone, int user_status);
	
	// select User By Time
	public List<UserVO> selectUserByTime(int time_idx);
	
	// delete user
	public void deleteUser(int user_idx);
	
	// select return idx (사장님 : 가게 번호 / 개인 : 0)
	public int selectReturnIdx(int user_idx);
}
