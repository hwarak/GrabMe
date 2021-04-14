package com.grabme.service;

import com.grabme.vo.ShopVO;

public interface ShopService {

	// 비즈니스 등록
	public void insertShop(int ownerIdx, int categoryIdx, String shopThumbnail, String shopTitle, String shopAddress,
			String shopPhone, String shopIntroduction, double shopLon, double shopLat, String shopKatalkUrl,
			String shopInstaUrl);

	// 사장님 idx로 등록된 비즈니스 idx 찾기
	public int selectShopIdx(int ownerIdx);

	// 사장님이 등록한 비즈니스가 있으면 1을 없으면 0을 반환한다
	public int checkShop(int ownerIdx);

	// 비즈니스 정보 모두 보기
	public ShopVO selectShopAllInfo(int shopIdx);

	// 비즈니스 정보 업데이트
	public void updateShopAllInfo(ShopVO svo);

	// 비즈니스 정보 빈칸 체크
	public ShopVO checkEmpty(ShopVO svo);

	// 비즈니스 삭제
	public void deleteShop(int shopIdx, int ownerIdx);
}
