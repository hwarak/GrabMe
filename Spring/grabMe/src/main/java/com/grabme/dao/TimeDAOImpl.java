package com.grabme.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.grabme.vo.TimeVO;
import com.grabme.vo.UserVO;

public class TimeDAOImpl implements TimeDAO {

	@Autowired
	private SqlSession sqlSession;

	// 타임 등록
	@Override
	public void insertTime(int shopIdx, String timeDate, String timeAvailable, int timePeople) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopIdx", shopIdx);
		map.put("timeDate", timeDate);
		map.put("timeAvailable", timeAvailable);
		map.put("timePeople", timePeople);
		map.put("timeMaxPeople", timePeople);
		sqlSession.insert("com.grabme.mappers.TimeMapper.insertTime", map);

	}

	// 타임 삭제
	@Override
	public void deleteTime(int time_idx) {
		sqlSession.delete("com.grabme.mappers.TimeMapper.deleteTime", time_idx);

	}

	// 예약 가능한 인원수 -1
	@Override
	public void updateTimePeopleMinus(int timeIdx) {
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimePeopleMinus", timeIdx);
	}
	
	// 예약 가능한 인원수 +1
	@Override
	public void updateTimePeoplePlus(int timeIdx) {
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimePeoplePlus", timeIdx);
	}
	// time_max_people(예약 가능한 최대 인원)과 time_people(현재 에약 가능한 인원수)
	// time_del_status : 사장님이 선택한 타임을 삭제할 수 있으면 1, 없으면 0
	/// time_status : 선택한 타임에 예약 가능한 인원수가 0이면 0을 , 0보다 크면 1
	public void updateTimeStatus(int timeIdx) {
		sqlSession.update("com.grabme.mappers.TimeMapper.updateTimeStatus", timeIdx);
	}

	// 비즈니즈스의 해당 날짜에 등록된 타임 리스트
	@Override
	public List<TimeVO> selectDate(int shopIdx, String timeDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shopIdx", shopIdx);
		map.put("timeDate", timeDate);
		return sqlSession.selectList("com.grabme.mappers.TimeMapper.selectDate", map);
	}

	// 선택된 타임에 예약된 개인 고객들 리스트를 출력한다
	@Override
	public List<UserVO> selectUserByTime(int timeIdx) {
		return sqlSession.selectList("com.grabme.mappers.TimeMapper.selectUserByTime", timeIdx);
	}
}
