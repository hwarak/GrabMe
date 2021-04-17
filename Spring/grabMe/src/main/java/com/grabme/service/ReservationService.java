package com.grabme.service;

import java.util.List;

import com.grabme.vo.ShopListResVO;

public interface ReservationService {

	// 예약 등록
	public void insertReservation(int userIdx, int timeIdx);

	// 예약 삭제
	public void deleteReservation(int reservationIdx);
	
	// 예약리스트 뿌려줄 객체 리스트 , 날짜순으로 정렬
	public List<ShopListResVO> selectReservationList(int userIdx);
}
