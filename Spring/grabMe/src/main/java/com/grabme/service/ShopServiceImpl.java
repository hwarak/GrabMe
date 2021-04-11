package com.grabme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.ShopDAO;
import com.grabme.dao.UserDAO;
import com.grabme.vo.ShopAllVO;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDAO dao;
	
	@Autowired
	private UserDAO udao;

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
	public ShopAllVO selectShopAllinfo(int idx) {
		return dao.selectShopAllinfo(idx);
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

	// delete shop
	@Override
	public void deleteShop(int shop_idx, int user_idx) {
		// 가게 번호로 등록된 모든 예약내역과 타임테이블을 삭제한다
		dao.deleteTimeReservation(shop_idx);
		// 가게 정보 전부 삭제
		dao.deleteShop(shop_idx);
		// 마지막으로 유저 정보까지 삭제
		udao.deleteUser(user_idx);
	}
	
	

}
