package com.grabme.service;

public interface ShopService {
	
	// insert shop
	public void insertShop(int user_idx, int category_idx, String thumbnail, String title, String address,
			String introduction);

}
