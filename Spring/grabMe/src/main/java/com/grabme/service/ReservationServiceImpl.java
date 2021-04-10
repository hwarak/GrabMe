package com.grabme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.ReservationDAO;
import com.grabme.dao.TimeDAO;
import com.grabme.vo.ShopResVO;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDAO dao;

	@Autowired
	private TimeDAO tdao;
	
	@Autowired
	private ReservationDAO rdao;

	// insert reservation
	@Override
	public void insertReservation(int user_idx, int time_idx) {
		
		// 예약 가능 인원 -1 , 상태 체크 후 1명 이상이면 T로 전환
		tdao.updateTimePeopleMinus(time_idx);
		tdao.updateTimeStatus(time_idx);
		
		// time 테이블 업데이트 후에 reservation 테이블 추가 !
		dao.insertReservation(user_idx, time_idx);
	}

	// select reservation list
	@Override
	public List<ShopResVO> selectReservationList(int user_idx) {
		return dao.selectReservationList(user_idx);
	}

	// delete reservation
	@Override
	public void deleteReservation(int reservation_idx) {
		
		// 업데이트 할 시간 idx 가져오기
		int time_idx = rdao.selectTimeIdx(reservation_idx);
		
		// 예약 가능 인원 +1 , 상태 체크 후 1명 이상이면 T로 전환
		tdao.updateTimePeoplePlus(time_idx);
		tdao.updateTimeStatus(time_idx);
		
		// time 테이블 업데이트 후에 reservation 테이블 삭제 !
		dao.deleteReservation(reservation_idx);
		
		
	}

}
