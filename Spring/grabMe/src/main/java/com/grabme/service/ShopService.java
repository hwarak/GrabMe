package com.grabme.service;

import java.util.List;

import com.grabme.vo.ShopAllVO;

public interface ShopService {

	// insert shop
	public void insertShop(int user_idx, int category_idx, String thumbnail, String title, String address,
			String introduction);

	// select shop idx
	public int selectShopIdx(int user_idx);

	// check Shop
	public int checkShop(int user_idx);

	// select Shop All Info
	public List<ShopAllVO> selectShopAllinfo(int user_idx);

	// update Shop All Info
	public void updateShopAllinfo(ShopAllVO savo);

	// check empty
	public ShopAllVO checkEmpty(ShopAllVO savo);

}
