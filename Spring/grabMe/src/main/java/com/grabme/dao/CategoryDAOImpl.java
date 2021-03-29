package com.grabme.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.grabme.vo.ShopVO;

public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SqlSession sqlSession;

	// 위도,경도 값 받아서 거리순으로 정렬후 원하는 만큼 보여주기
	@Override
	public List<ShopVO> selectCategoryWithXY(double x, double y, int category_idx, int startNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		map.put("category_idx", category_idx);
		map.put("startNum", startNum);
		
		return sqlSession.selectList("com.grabme.mappers.CategoryMapper.selectCategoryWithXY", map);
	}

	// search Title
	@Override
	public List<ShopVO> searchTitle(double x, double y, int category_idx, int startNum, String word) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		map.put("category_idx", category_idx);
		map.put("startNum", startNum);
		map.put("word", "%"+word+"%");
		return sqlSession.selectList("com.grabme.mappers.CategoryMapper.searchTitle", map);
	}
	
	

}
