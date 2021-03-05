package com.grabme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.CategoryDAO;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO dao;

	// select category idx
	@Override
	public int selectIdx(String category) {
		return dao.selectIdx(category);
	}

}
