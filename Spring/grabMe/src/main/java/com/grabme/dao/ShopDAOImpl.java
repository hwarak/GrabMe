package com.grabme.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.grabme.vo.ShopVO;

public class ShopDAOImpl implements ShopDAO {

	@Autowired
	private SqlSession sqlsession;

	// 비즈니스 등록
	@Override
	public void insertShop(int ownerIdx, int categoryIdx, String shopThumbnail, String shopTitle, String shopAddress,
			String shopPhone, String shopIntroduction, double shopLon, double shopLat, String shopKatalkUrl,
			String shopInstaUrl) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ownerIdx", ownerIdx);
		map.put("categoryIdx", categoryIdx);
		map.put("shopThumbnail", shopThumbnail);
		map.put("shopTitle", shopTitle);
		map.put("shopAddress", shopAddress);
		map.put("shopPhone", shopPhone);
		map.put("shopIntroduction", shopIntroduction);
		map.put("shopLon", shopLon);
		map.put("shopLat", shopLat);
		map.put("shopKatalkUrl", shopKatalkUrl);
		map.put("shopInstaUrl", shopInstaUrl);

		sqlsession.insert("com.grabme.mappers.ShopMapper.insertShop", map);

	}

	// 사장님 idx로 등록된 비즈니스 idx 찾기
	@Override
	public int selectShopIdx(int ownerIdx) {
		return sqlsession.selectOne("com.grabme.mappers.ShopMapper.selectShopIdx", ownerIdx);
	}

	// 사장님이 등록한 비즈니스가 있으면 1을 없으면 0을 반환한다
	@Override
	public int checkShop(int ownerIdx) {
		return sqlsession.selectOne("com.grabme.mappers.ShopMapper.checkShop", ownerIdx);
	}

	// 비즈니스 정보 모두 보기
	@Override
	public ShopVO selectShopAllInfo(int shopIdx) {
		return sqlsession.selectOne("com.grabme.mappers.ShopMapper.selectShopAllInfo", shopIdx);
	}

	// 비즈니스 정보 업데이트
	@Override
	public void updateShopAllInfo(ShopVO svo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopIdx", svo.getShopIdx());
		map.put("categoryIdx", svo.getCategoryIdx());
		map.put("shopThumbnail", svo.getShopThumbnail());
		map.put("shopTitle", svo.getShopTitle());
		map.put("shopAddress", svo.getShopAddress());
		map.put("shopPhone", svo.getShopPhone());
		map.put("shopIntroduction", svo.getShopIntroduction());
		map.put("shopLon", svo.getShopLon());
		map.put("shopLat", svo.getShopLat());
		map.put("shopKatalkUrl", svo.getShopKatalkUrl());
		map.put("shopInstaUrl", svo.getShopInstaUrl());

		sqlsession.insert("com.grabme.mappers.ShopMapper.updateShopAllInfo", map);

	}

	// 비즈니스 삭제
	@Override
	public void deleteShop(int shopIdx) {
		sqlsession.delete("com.grabme.mappers.ShopMapper.deleteShop", shopIdx);
	}

	// 비즈니스에 등록된 예약 시각들, 예약 내역들 모두 삭제
	@Override
	public void deleteTimeReservation(int shopIdx) {
		sqlsession.delete("com.grabme.mappers.ShopMapper.deleteTimeReservation", shopIdx);

	}

}
