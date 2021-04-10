package com.grabme.dao;

import java.util.List;

import com.grabme.vo.ShopResVO;

public interface CategoryDAO {

	// 위도,경도 값 받아서 거리순으로 정렬후 원하는 만큼 보여주기
	public List<ShopResVO> selectCategoryWithXY(double x, double y, int category_idx, int startNum);
	
	// search Title
	public List<ShopResVO> searchTitle(double x, double y, int category_idx, int startNum, String word);
}
