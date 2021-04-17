package com.grabme.dao;

import java.util.List;

import com.grabme.vo.ShopListResVO;

public interface ReservationDAO {

	// 예약 등록
	public void insertReservation(int userIdx, int timeIdx);

	// 예약 삭제
	public void deleteReservation(int reservationIdx);

	// 예약리스트 뿌려줄 객체 리스트 , 날짜순으로 정렬
	public List<ShopListResVO> selectReservationList(int userIdx);
	
	// 선택된 예약의 타임 idx
	public int selectTimeIdx(int reservationIdx);
	
	// 선택된 유저의 예약 번호들
	public List<Integer> selectReservationByUser(int userIdx);
}
