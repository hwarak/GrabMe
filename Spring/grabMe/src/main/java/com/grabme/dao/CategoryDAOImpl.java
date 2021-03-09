package com.grabme.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SqlSession sqlSession;

	// select category idx
	@Override
	public int selectIdx(String category) {
		return sqlSession.selectOne("com.grabme.mappers.CategoryMapper.selectIdx", category);
	}

	// select category name
	@Override
	public String selectName(int idx) {
		return sqlSession.selectOne("com.grabme.mappers.CategoryMapper.selectName", idx);
	}

}
