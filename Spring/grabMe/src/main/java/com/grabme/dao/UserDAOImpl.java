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
	public int checkUser(String user_phone, int user_status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_phone", user_phone);
		map.put("user_status", user_status);
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.checkUser", map);
	}

	// insert user
	@Override
	public void insertUser(String user_name, String user_phone, int user_status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name", user_name);
		map.put("user_phone", user_phone);
		map.put("user_status", user_status);

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

	// delete user
	@Override
	public void deleteUser(int user_idx) {
		sqlSession.delete("com.grabme.mappers.UserMapper.deleteUser",user_idx);
	}

	// select return idx (사장님 : 가게 번호 / 개인 : 0)
	@Override
	public int selectReturnIdx(int user_idx) {
		return sqlSession.selectOne("com.grabme.mappers.UserMapper.selectReturnIdx",user_idx);
	}
	
	
	
	

}
