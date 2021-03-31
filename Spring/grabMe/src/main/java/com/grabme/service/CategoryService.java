package com.grabme.service;

import java.util.List;

import com.grabme.vo.ShopVO;

public interface CategoryService {

	// 위도,경도 값 받아서 거리순으로 정렬후 원하는 만큼 보여주기
	public List<ShopVO> selectCategoryWithXY(double x, double y, int category_idx, int startNum);

	// search Title
	public List<ShopVO> searchTitle(double x, double y, int category_idx, int startNum, String word);
}
