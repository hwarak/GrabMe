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
	public void insertTime(int shop_idx, String date, String time) {
		dao.insertTime(shop_idx, date, time);
	}

	// update time
	@Override
	public void updateTime(String time, int idx) {
		dao.updateTime(time, idx);
	}

	// delete time
	@Override
	public void deleteTime(int idx) {
		dao.deleteTime(idx);
	}

	// select date -> list
	@Override
	public List<TimeVO> selectDate(int shop_idx, String date) {
		return dao.selectDate(shop_idx, date);
	}

}
