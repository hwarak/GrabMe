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
	
	

}
