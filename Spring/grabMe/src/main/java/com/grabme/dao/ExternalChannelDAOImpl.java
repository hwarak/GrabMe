package com.grabme.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class ExternalChannelDAOImpl implements ExternalChannelDAO {

	@Autowired
	private SqlSession sqlSession;

	// 가게 채널 등록
	@Override
	public void insertURL(int shopIdx, String katalkURL, String instaURL) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopIdx", shopIdx);
		map.put("katalkURL", katalkURL);
		map.put("instaURL", instaURL);
		sqlSession.insert("com.grabme.mappers.ExternalChannelMapper.insertURL", map);

	}

}
