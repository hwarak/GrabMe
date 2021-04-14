package com.grabme.dao;

import java.util.List;

import com.grabme.vo.ShopListResVO;

public interface ReservationDAO {

	// insert reservation
	public void insertReservation(int user_idx, int time_idx);

	// select reservation list
	public List<ShopListResVO> selectReservationList(int user_idx);

	// delete reservation
	public void deleteReservation(int reservation_idx);
	
	// select time idx by reservation_idx
	public int selectTimeIdx(int reservation_idx);
	
	// select reservation by user
	public List<Integer> selectReservationByUser(int user_idx);
}
