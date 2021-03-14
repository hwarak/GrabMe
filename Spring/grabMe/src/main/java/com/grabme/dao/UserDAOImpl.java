package com.grabme.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.grabme.vo.UserVO;

public class UserDAOImpl implements UserDAO {

	@Autowired
	private SqlSession sqlSession;

	// Check user in database
	@Override
	public int checkUser(String phone, int status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("status", status);
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.checkUser", map);
	}

	// insert user
	@Override
	public void insertUser(String name, String phone, int status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("phone", phone);
		map.put("status", status);

		sqlSession.insert("com.grabme.mappers.UserMapper.insertUser", map);
	}

	// select user idx
	@Override
	public int selectUserIdx(String phone, int status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("status", status);
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.selectUserIdx", map);
	}

	// select user
	@Override
	public UserVO selectUser(int idx) {
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.selectUser",idx);
	}

}
