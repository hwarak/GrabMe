package com.grabme.service;

public interface CategoryService {

	// select category idx
	public int selectIdx(String category);

	// select category name
	public String selectName(int idx);

}
