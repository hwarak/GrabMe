package com.grabme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.ShopDAO;
import com.grabme.dao.UserDAO;
import com.grabme.vo.ShopVO;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDAO dao;

	@Autowired
	private UserDAO udao;

	// 비즈니스 등록
	@Override
	public void insertShop(int ownerIdx, int categoryIdx, String shopThumbnail, String shopTitle, String shopAddress,
			String shopPhone, String shopIntroduction, double shopLon, double shopLat, String shopKatalkUrl,
			String shopInstaUrl) {
		dao.insertShop(ownerIdx, categoryIdx, shopThumbnail, shopTitle, shopAddress, shopPhone, shopIntroduction,
				shopLon, shopLat, shopKatalkUrl, shopInstaUrl);
	}

	// 사장님 idx로 등록된 비즈니스 idx 찾기
	@Override
	public int selectShopIdx(int ownerIdx) {
		return dao.selectShopIdx(ownerIdx);
	}

	// 사장님이 등록한 비즈니스가 있으면 1을 없으면 0을 반환한다
	@Override
	public int checkShop(int ownerIdx) {
		return dao.checkShop(ownerIdx);
	}

	// 비즈니스 정보 모두 보기
	@Override
	public ShopVO selectShopAllInfo(int shopIdx) {
		return dao.selectShopAllInfo(shopIdx);
	}

	// 비즈니스 정보 업데이트
	@Override
	public void updateShopAllInfo(ShopVO svo) {
		dao.updateShopAllInfo(svo);
	}

	// 비즈니스 정보 빈칸 체크
	@Override
	public ShopVO checkEmpty(ShopVO svo) {

		if (svo.getShopPhone().isEmpty()) {
			svo.setShopPhone("default");
		}

		if (svo.getShopIntroduction().isEmpty()) {
			svo.setShopIntroduction("등록된 소개가 없습니다");
		}

		if (svo.getShopKatalkUrl().isEmpty()) {
			svo.setShopKatalkUrl("default");
		}

		if (svo.getShopInstaUrl().isEmpty()) {
			svo.setShopInstaUrl("default");
		}

		return svo;
	}

	// 비즈니스 삭제
	@Override
	public void deleteShop(int shopIdx, int ownerIdx) {
		// 가게 번호로 등록된 모든 예약내역과 타임테이블을 삭제한다
		dao.deleteTimeReservation(shopIdx);
		// 가게 정보 전부 삭제
		dao.deleteShop(shopIdx);
		// 마지막으로 유저 정보까지 삭제
		udao.deleteUser(ownerIdx);
	}

}
