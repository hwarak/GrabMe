package com.grabme.dao;

public interface ShopDAO {

	// insert shop
	public void insertShop(int user_idx, int category_idx, String thumbnail, String title, String address,
			String introduction);
	
	// select shop idx
	public int selectShopIdx(int user_idx);
	
	// check Shop
	public int checkShop(int user_idx);

}
