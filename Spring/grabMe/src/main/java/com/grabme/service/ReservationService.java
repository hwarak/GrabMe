package com.grabme.service;

import java.util.List;

import com.grabme.vo.ShopResVO;

public interface ReservationService {

	// insert reservation
	public void insertReservation(int user_idx, int time_idx);

	// select reservation list
	public List<ShopResVO> selectReservationList(int user_idx);

	// delete reservation
	public void deleteReservation(int idx);
}
