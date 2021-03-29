package com.grabme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.ShopDAO;
import com.grabme.vo.ShopAllVO;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDAO dao;

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

	// select Shop All Info
	@Override
	public List<ShopAllVO> selectShopAllinfo(int user_idx) {
		return dao.selectShopAllinfo(user_idx);
	}

	// update Shop All Info
	@Override
	public void updateShopAllinfo(ShopAllVO savo) {
		dao.updateShopAllinfo(savo);
	}

	// check empty
	@Override
	public ShopAllVO checkEmpty(ShopAllVO savo) {

		if (savo.getOpenkatalkURL().isEmpty()) {
			savo.setOpenkatalkURL("default");
		}

		if (savo.getInstaURL().isEmpty()) {
			savo.setInstaURL("default");
		}

		if (savo.getIntroduction().isEmpty()) {
			savo.setIntroduction("등록된 소개가 없습니다");
		}

		return savo;
	}

}
