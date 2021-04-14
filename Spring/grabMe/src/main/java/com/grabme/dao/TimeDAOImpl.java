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
	public void insertTime(int shop_idx, String time_date, String time_time, int time_people) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_idx", shop_idx);
		map.put("time_date", time_date);
		map.put("time_time", time_time);
		map.put("time_people", time_people);
		map.put("time_max_people", time_people);
		sqlSession.insert("com.grabme.mappers.TimeMapper.insertTime", map);

	}

	// update time
	@Override
	public void updateTime(String time_time, int time_idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("time_time", time_time);
		map.put("time_idx", time_idx);
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTime", map);
	}

	// delete time
	@Override
	public void deleteTime(int time_idx) {
		sqlSession.delete("com.grabme.mappers.TimeMapper.deleteTime", time_idx);

	}

	// update People Minus
	@Override
	public void updateTimePeopleMinus(int time_idx) {
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimePeopleMinus", time_idx);
	}
	
	// update People Plus
	@Override
	public void updateTimePeoplePlus(int time_idx) {
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimePeoplePlus", time_idx);
		
	}

	// update time status
	public void updateTimeStatus(int time_idx) { // TODO Auto-generated method stub
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimeStatus", time_idx);
	}

	// select date -> list
	@Override
	public List<TimeVO> selectDate(int shop_idx, String time_date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_idx", shop_idx);
		map.put("time_date", time_date);
		return sqlSession.selectList("com.grabme.mappers.TimeMapper.selectDate", map);
	}

}
