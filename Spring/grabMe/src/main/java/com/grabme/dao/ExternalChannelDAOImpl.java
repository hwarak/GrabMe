package com.grabme.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class ExternalChannelDAOImpl implements ExternalChannelDAO {

	@Autowired
	private SqlSession sqlSession;

	// insert shop url
	@Override
	public void insertURL(int shop_idx, String openkatalkURL, String instaURL) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_idx", shop_idx);
		map.put("openkatalkURL", openkatalkURL);
		map.put("instaURL", instaURL);
		sqlSession.insert("com.grabme.mappers.ExternalChannelMapper.insertURL", map);

	}

}
