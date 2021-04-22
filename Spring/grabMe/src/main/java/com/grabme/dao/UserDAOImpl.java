package com.grabme.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession sqlSession;

	// 유저 번호와 상태(사장님/개인)를 받아 데이터베이스 존재여부 확인
	@Override
	public int checkUser(String userPhone, int userStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userPhone", userPhone);
		map.put("userStatus", userStatus);
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.checkUser", map);
	}

	// 회원가입
	@Override
	public void insertUser(String userName, String userPhone, int userStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("userPhone", userPhone);
		map.put("userStatus", userStatus);

		sqlSession.insert("com.grabme.mappers.UserMapper.insertUser", map);
	}

	// 유저 탈퇴
	@Override
	public void deleteUser(int userIdx) {
		sqlSession.delete("com.grabme.mappers.UserMapper.deleteUser", userIdx);
	}

	// 유저가 사장님이면 가게 번호를, 개인고객이면 0을 반환
	@Override
	public int selectReturnIdx(int userIdx) {
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.selectReturnIdx", userIdx);
	}

	// 유저 정보 업데이트
	@Override
	public void updateUser(String userProfileImg, int userIdx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userProfileImg", userProfileImg);
		map.put("userIdx", userIdx);
		sqlSession.update("com.grabme.mappers.UserMapper.updateUser",map);
		
	}

	// 유저 프로필 주소
	@Override
	public String selectUserProfile(int userIdx) {
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.selectUserProfile",userIdx);
	}
	
	
	

}
