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
	public void insertTime(int shop_idx, String date, String time, int people) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_idx", shop_idx);
		map.put("date", date);
		map.put("time", time);
		map.put("people", people);
		map.put("max_people", people);
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

	// update People Minus
	@Override
	public void updateTimePeopleMinus(int idx) {
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimePeopleMinus", idx);
	}
	
	// update People Plus
	@Override
	public void updateTimePeoplePlus(int idx) {
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimePeoplePlus", idx);
		
	}

	// update time status
	public void updateTimeStatus(int idx) { // TODO Auto-generated method stub
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimeStatus", idx);
	}
	
	
	// update time delete status
	@Override
	public void updateTimeDelStatus(int idx) {
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimeDelStatus", idx);
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
