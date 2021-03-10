package com.grabme.dao;

import java.util.List;

import com.grabme.vo.TimeVO;

public interface TimeDAO {

	// insert time
	public void insertTime(int shop_idx, String date, String time);

	// update time
	public void updateTime(String time, int idx);

	// delete time
	public void deleteTime(int idx);

	// update time status
	public void updateTimeStatus(int idx);

	// select date -> list
	public List<TimeVO> selectDate(int shop_idx, String date);

}
