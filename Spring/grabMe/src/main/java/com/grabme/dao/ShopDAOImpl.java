package com.grabme.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.grabme.vo.ShopAllVO;

public class ShopDAOImpl implements ShopDAO {

	@Autowired
	private SqlSession sqlsession;

	// insert shop
	@Override
	public void insertShop(int user_idx, int category_idx, String thumbnail, String title, String address,
			String introduction) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_idx", user_idx);
		map.put("category_idx", category_idx);
		map.put("thumbnail", thumbnail);
		map.put("title", title);
		map.put("address", address);
		map.put("introduction", introduction);

		sqlsession.insert("com.grabme.mappers.ShopMapper.insertShop", map);

	}

	// select shop idx
	@Override
	public int selectShopIdx(int user_idx) {
		return sqlsession.selectOne("com.grabme.mappers.ShopMapper.selectShopIdx", user_idx);
	}

	// check Shop
	@Override
	public int checkShop(int user_idx) {
		// 가게 존재하는지 확인
		// 입력받은 유저아이디로 생성된 가게가 없으면 0을 반환
		return sqlsession.selectOne("com.grabme.mappers.ShopMapper.checkShop", user_idx);
	}

	// select Shop All Info
	@Override
	public List<ShopAllVO> selectShopAllinfo(int user_idx) {
		return sqlsession.selectList("com.grabme.mappers.ShopMapper.selectShopAllinfo", user_idx);

	}

	// update Shop All Info
	@Override
	public void updateShopAllinfo(ShopAllVO savo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idx", savo.getShop_idx());
		map.put("category_idx", savo.getCategory_idx());
		map.put("thumbnail", savo.getThumbnail());
		map.put("title", savo.getTitle());
		map.put("address", savo.getAddress());
		map.put("introduction", savo.getIntroduction());
		map.put("openkatalkURL", savo.getOpenkatalkURL());
		map.put("instaURL", savo.getInstaURL());

		sqlsession.insert("com.grabme.mappers.ShopMapper.updateShopAllInfo", map);

	}

}
