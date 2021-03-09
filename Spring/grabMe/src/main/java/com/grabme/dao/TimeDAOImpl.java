package com.grabme.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.grabme.vo.TimeVO;

public class TimeDAOImpl implements TimeDAO {

	@Autowired
	private SqlSession sqlSession;

	// insert time
	@Override
	public void insertTime(int shop_idx, String date, String time) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_idx", shop_idx);
		map.put("date", date);
		map.put("time", time);
		sqlSession.insert("com.grabme.mappers.TimeMapper.insertTime", map);

	}

	// update time status
	@Override
	public void updateTimeStatus(int idx) {
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimeStatus", idx);
	}

	// select date
	@Override
	public List<TimeVO> selectDate(int shop_idx, String date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_idx", shop_idx);
		map.put("date", date);
		return sqlSession.selectList("com.grabme.mappers.TimeMapper.selectDate", map);
	}

}
