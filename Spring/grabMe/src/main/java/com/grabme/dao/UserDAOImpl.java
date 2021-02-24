package com.grabme.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOImpl implements UserDAO {

	@Autowired
	SqlSession sqlSession;

	// Check phone in database
	@Override
	public int checkPhone(String phone) {
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.checkPhone", phone);
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

}
