package com.grabme.dao;

import java.util.List;

import com.grabme.vo.TimeVO;
import com.grabme.vo.UserVO;

public interface TimeDAO {

	// 타임 등록
	public void insertTime(int shopIdx, String timeDate, String timeAvailable, int timePeople);

	// 타임 삭제
	public void deleteTime(int timeIdx);

	// 예약 가능한 인원수 -1
	public void updateTimePeopleMinus(int timeIdx);

	// 예약 가능한 인원수 +1
	public void updateTimePeoplePlus(int timeIdx);

	// time_max_people(예약 가능한 최대 인원)과 time_people(현재 에약 가능한 인원수)
	// time_del_status : 사장님이 선택한 타임을 삭제할 수 있으면 1, 없으면 0
	/// time_status : 선택한 타임에 예약 가능한 인원수가 0이면 0을 , 0보다 크면 1
	public void updateTimeStatus(int timeIdx);

	// 비즈니즈스의 해당 날짜에 등록된 타임 리스트
	public List<TimeVO> selectDate(int shopIdx, String timeDate);
	
	// 선택된 타임에 예약된 개인 고객들 리스트를 출력한다
	public List<UserVO> selectUserByTime(int timeIdx);

}
