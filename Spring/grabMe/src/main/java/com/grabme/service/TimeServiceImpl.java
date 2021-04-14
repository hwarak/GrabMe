package com.grabme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.TimeDAO;
import com.grabme.vo.TimeVO;

@Service
public class TimeServiceImpl implements TimeService {

	@Autowired
	private TimeDAO dao;

	// insert time
	@Override
	public void insertTime(int shop_idx, String time_date, String time_time, int time_people) {
		dao.insertTime(shop_idx, time_date, time_time, time_people);
	}

	// update time
	@Override
	public void updateTime(String time_time, int time_idx) {
		dao.updateTime(time_time, time_idx);
	}

	// delete time
	@Override
	public void deleteTime(int time_idx) {
		dao.deleteTime(time_idx);
	}

	// select date -> list
	@Override
	public List<TimeVO> selectDate(int shop_idx, String time_date) {
		return dao.selectDate(shop_idx, time_date);
	}

}
