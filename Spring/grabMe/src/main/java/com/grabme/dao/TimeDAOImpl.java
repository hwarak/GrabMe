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

	// update time
	@Override
	public void updateTime(String time, int idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time", time);
		map.put("idx", idx);
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTime", map);
	}

	// delete time
	@Override
	public void deleteTime(int idx) {
		sqlSession.delete("com.grabme.mappers.TimeMapper.deleteTime", idx);

	}

	// update time status
	@Override
	public void updateTimeStatus(int idx) {
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimeStatus", idx);
	}

	// select date -> list
	@Override
	public List<TimeVO> selectDate(int shop_idx, String date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_idx", shop_idx);
		map.put("date", date);
		return sqlSession.selectList("com.grabme.mappers.TimeMapper.selectDate", map);
	}

}
