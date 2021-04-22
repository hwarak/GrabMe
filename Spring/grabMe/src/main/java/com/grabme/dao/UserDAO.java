package com.grabme.dao;

public interface UserDAO {

	// 유저 번호와 상태(사장님/개인)를 받아 데이터베이스 존재여부 확인
	public int checkUser(String userPhone, int userStatus);

	// 회원가입
	public void insertUser(String userName, String userPhone, int userStatus);
	
	// 유저 탈퇴
	public void deleteUser(int userIdx);
	
	// 유저가 사장님이면 가게 번호를, 개인고객이면 0을 반환
	public int selectReturnIdx(int userIdx);
	
	// 유저 정보 업데이트
	public void updateUser(String userProfileImg, int userIdx);
	
	// 유저 프로필 주소
	public String selectUserProfile(int userIdx);
}
