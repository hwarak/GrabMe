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

	// select User By Time
	@Override
	public List<UserVO> selectUserByTime(int time_idx) {
		return sqlSession.selectList("com.grabme.mappers.UserMapper.selectUserByTime",time_idx);
	}

	// delete user
	@Override
	public void deleteUser(int idx) {
		sqlSession.delete("com.grabme.mappers.UserMapper.deleteUser",idx);
	}

	// select return idx (사장님 : 가게 번호 / 개인 : 0)
	@Override
	public int selectReturnIdx(int idx) {
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.selectReturnIdx",idx);
	}
	
	
	
	

}
