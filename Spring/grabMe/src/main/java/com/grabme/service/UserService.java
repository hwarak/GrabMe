package com.grabme.service;

import java.util.List;

import com.grabme.vo.UserVO;

public interface UserService {

	// 유저 번호와 상태(사장님/개인)를 받아 데이터베이스 존재여부 확인
	public int checkUser(String userPhone, int userStatus);

	// 인증번호 생성
	public String randomNumber();

	// select user idx
	public int selectUserIdx(String user_phone, int user_status);

	// 회원가입
	public void insertUser(String userName, String userPhone, int userStatus);

	// select User By Time
	public List<UserVO> selectUserByTime(int time_idx);

	// 유저 탈퇴
	public void deleteUser(int userIdx);

	// 유저가 사장님이면 가게 번호를, 개인고객이면 0을 반환
	public int selectReturnIdx(int userIdx);

}
