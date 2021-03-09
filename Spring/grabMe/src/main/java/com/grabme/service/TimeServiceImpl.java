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
	
	// select date
	@Override
	public List<TimeVO> selectDate(int shop_idx, String date) {
		return dao.selectDate(shop_idx, date);
	}
	
	
}
