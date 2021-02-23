package com.grabme.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class ShopDAOImpl implements ShopDAO {

	@Autowired
	SqlSession sqlsession;

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

}
