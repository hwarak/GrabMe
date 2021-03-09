package com.grabme.dao;

import java.util.List;

import com.grabme.vo.TimeVO;

public interface TimeDAO {

	// insert time
	public void insertTime(int shop_idx, String date, String time);

	// update time status
	public void updateTimeStatus(int idx);

	// select date
	public List<TimeVO> selectDate(int shop_idx, String date);
}
