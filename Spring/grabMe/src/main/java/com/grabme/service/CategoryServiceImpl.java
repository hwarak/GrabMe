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
	public List<ShopListResVO> selectCategoryWithXY(double x, double y, int category_idx, int startNum) {
		return dao.selectCategoryWithXY(x, y, category_idx, startNum);
	}

	// search Title
	@Override
	public List<ShopListResVO> searchTitle(double x, double y, int category_idx, int startNum, String word) {
		return dao.searchTitle(x, y, category_idx, startNum, word);
	}
	
	

}
