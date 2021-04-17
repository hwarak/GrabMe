package com.grabme.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.grabme.vo.ShopListResVO;

public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SqlSession sqlSession;

	// 위도,경도 값 받아서 거리순으로 정렬후 원하는 만큼 보여주기
	@Override
	public List<ShopListResVO> selectCategoryWithXY(double shopLon, double shopLat, int categoryIdx, int startNum) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopLon", shopLon);
		map.put("shopLat", shopLat);
		map.put("categoryIdx", categoryIdx);
		map.put("startNum", startNum);
		
		return sqlSession.selectList("com.grabme.mappers.CategoryMapper.selectCategoryWithXY", map);
	}

	// 원하는 비즈니스 검색
	@Override
	public List<ShopListResVO> searchTitle(double shopLon, double shopLat, int categoryIdx, int startNum, String word) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopLon", shopLon);
		map.put("shopLat", shopLat);
		map.put("categoryIdx", categoryIdx);
		map.put("startNum", startNum);
		map.put("word", "%"+word+"%");
		return sqlSession.selectList("com.grabme.mappers.CategoryMapper.searchTitle", map);
	}
	
	

}
