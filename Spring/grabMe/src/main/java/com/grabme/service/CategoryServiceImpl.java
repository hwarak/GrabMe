package com.grabme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.CategoryDAO;
import com.grabme.vo.ShopListResVO;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO dao;

	// 위도,경도 값 받아서 거리순으로 정렬후 원하는 만큼 보여주기
	@Override
	public List<ShopListResVO> selectCategoryWithXY(double shopLon, double shopLat, int categoryIdx, int startNum) {
		return dao.selectCategoryWithXY(shopLon, shopLat, categoryIdx, startNum);
	}

	// 원하는 비즈니스 검색
	@Override
	public List<ShopListResVO> searchTitle(double shopLon, double shopLat, int categoryIdx, int startNum, String word){
		return dao.searchTitle(shopLon, shopLat, categoryIdx, startNum, word);
	}
	
	

}
