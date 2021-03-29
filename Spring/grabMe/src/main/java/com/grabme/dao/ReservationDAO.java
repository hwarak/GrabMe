package com.grabme.dao;

import java.util.List;

import com.grabme.vo.ReservationListVO;

public interface ReservationDAO {

	// insert reservation
	public void insertReservation(int user_idx, int time_idx);

	// select reservation list
	public List<ReservationListVO> selectReservationList(int user_idx);

	// delete reservation
	public void deleteReservation(int idx);
	
	// select time idx
	public int selectTimeIdx(int idx);
}
