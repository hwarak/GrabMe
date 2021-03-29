package com.grabme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.ReservationDAO;
import com.grabme.dao.TimeDAO;
import com.grabme.vo.ReservationListVO;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDAO dao;

	@Autowired
	private TimeDAO tdao;

	// insert reservation
	@Override
	public void insertReservation(int user_idx, int time_idx) {
		dao.insertReservation(user_idx, time_idx);
		tdao.updateTimeStatus(time_idx);
	}

	// select reservation list
	@Override
	public List<ReservationListVO> selectReservationList(int user_idx) {
		return dao.selectReservationList(user_idx);
	}

	// delete reservation
	@Override
	public void deleteReservation(int idx) {
		tdao.updateTimeStatus(dao.selectTimeIdx(idx));
		dao.deleteReservation(idx);
		
	}

}
