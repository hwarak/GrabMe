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

	// select Category With X,Y
	@Override
	public List<ShopVO> selectCategoryWithXY(double x, double y, int category_idx) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		map.put("category_idx", category_idx);
		return sqlSession.selectList("com.grabme.mappers.CategoryMapper.selectCategoryWithXY", map);
	}

}
