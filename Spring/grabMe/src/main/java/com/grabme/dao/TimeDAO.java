package com.grabme.dao;

import java.util.List;

import com.grabme.vo.TimeVO;

public interface TimeDAO {

	// insert time
	public void insertTime(int shop_idx, String time_date, String time_time, int time_people);

	// update time
	public void updateTime(String time_time, int time_idx);

	// delete time
	public void deleteTime(int time_idx);

	// update People Minus
	public void updateTimePeopleMinus(int time_idx);

	// update People Plus
	public void updateTimePeoplePlus(int time_idx);

	// update time status
	public void updateTimeStatus(int time_idx);

	// select date -> list
	public List<TimeVO> selectDate(int shop_idx, String time_date);

}
