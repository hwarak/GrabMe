package com.grabme.dao;

public interface CategoryDAO {

	// select category idx
	public int selectIdx(String category);

	// select category name
	public String selectName(int idx);

}
