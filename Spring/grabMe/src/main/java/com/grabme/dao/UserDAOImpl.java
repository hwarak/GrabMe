package com.grabme.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.grabme.vo.UserVO;

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

	// select user idx
	@Override
	public int selectUserIdx(String user_phone, int user_status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_phone", user_phone);
		map.put("user_status", user_status);
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.selectUserIdx", map);
	}

	// select User By Time
	@Override
	public List<UserVO> selectUserByTime(int time_idx) {
		return sqlSession.selectList("com.grabme.mappers.UserMapper.selectUserByTime",time_idx);
	}

	// 유저 탈퇴
	@Override
	public void deleteUser(int userIdx) {
		sqlSession.delete("com.grabme.mappers.UserMapper.deleteUser",userIdx);
	}

	// 유저가 사장님이면 가게 번호를, 개인고객이면 0을 반환
	@Override
	public int selectReturnIdx(int userIdx) {
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.selectReturnIdx",userIdx);
	}
	
	
	
	

}
