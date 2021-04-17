package com.grabme.dao;

import java.util.List;

import com.grabme.vo.ShopListResVO;

public interface CategoryDAO {

	// 위도,경도 값 받아서 거리순으로 정렬후 원하는 만큼 보여주기
	public List<ShopListResVO> selectCategoryWithXY(double shopLon, double shopLat, int categoryIdx, int startNum);
	
	// 원하는 비즈니스 검색
	public List<ShopListResVO> searchTitle(double shopLon, double shopLat, int categoryIdx, int startNum, String word);
}
