package com.grabme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.ShopDAO;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	ShopDAO dao;

	@Override
	public void insertShop(int user_idx, int category_idx, String thumbnail, String title, String address,
			String introduction) {
		dao.insertShop(user_idx, category_idx, thumbnail, title, address, introduction);
	}

	// select shop idx
	@Override
	public int selectShopIdx(int user_idx) {
		return dao.selectShopIdx(user_idx);
	}

	
	// check Shop
	@Override
	public int checkShop(int user_idx) {
		return dao.checkShop(user_idx);
	}
	
	

}
