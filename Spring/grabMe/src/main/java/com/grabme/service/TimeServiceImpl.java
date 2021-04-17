package com.grabme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.TimeDAO;
import com.grabme.vo.TimeVO;
import com.grabme.vo.UserVO;

@Service
public class TimeServiceImpl implements TimeService {

	@Autowired
	private TimeDAO dao;

	// 타임 등록
	@Override
	public void insertTime(int shopIdx, String timeDate, String timeAvailable, int timePeople) {
		dao.insertTime(shopIdx, timeDate, timeAvailable, timePeople);
	}

	// 타임 삭제
	@Override
	public void deleteTime(int timeIdx) {
		dao.deleteTime(timeIdx);
	}

	// 비즈니즈스의 해당 날짜에 등록된 타임 리스트
	@Override
	public List<TimeVO> selectDate(int shopIdx, String timeDate) {
		return dao.selectDate(shopIdx, timeDate);
	}
	
	// 선택된 타임에 예약된 개인 고객들 리스트를 출력한다
	@Override
	public List<UserVO> selectUserByTime(int timeIdx) {
		return dao.selectUserByTime(timeIdx);
	}

}
