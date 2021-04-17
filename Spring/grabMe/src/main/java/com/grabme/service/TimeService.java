package com.grabme.service;

import java.util.List;

import com.grabme.vo.TimeVO;
import com.grabme.vo.UserVO;

public interface TimeService {

	// 타임 등록
	public void insertTime(int shopIdx, String timeDate, String timeAvailable, int timePeople);

	// 타임 삭제
	public void deleteTime(int timeIdx);

	// 비즈니즈스의 해당 날짜에 등록된 타임 리스트
	public List<TimeVO> selectDate(int shopIdx, String timeDate);
	
	// 선택된 타임에 예약된 개인 고객들 리스트를 출력한다
	public List<UserVO> selectUserByTime(int timeIdx);
}
