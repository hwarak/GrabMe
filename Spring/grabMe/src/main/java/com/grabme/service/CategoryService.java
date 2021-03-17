package com.grabme.service;

import java.util.List;

import com.grabme.vo.ShopVO;

public interface CategoryService {

	// select Category With X,Y
	public List<ShopVO> selectCategoryWithXY(double x, double y, int category_idx);
	
}
