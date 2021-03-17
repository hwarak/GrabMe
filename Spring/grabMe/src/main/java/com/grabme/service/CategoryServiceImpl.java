package com.grabme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.CategoryDAO;
import com.grabme.vo.ShopVO;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO dao;

	// select Category With X,Y
	@Override
	public List<ShopVO> selectCategoryWithXY(double x, double y, int category_idx) {
		return dao.selectCategoryWithXY(x, y, category_idx);
	}

}
