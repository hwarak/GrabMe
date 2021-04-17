package com.grabme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.ReservationDAO;
import com.grabme.dao.TimeDAO;
import com.grabme.vo.ShopListResVO;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDAO dao;

	@Autowired
	private TimeDAO tdao;

	// 예약 등록
	@Override
	public void insertReservation(int userIdx, int timeIdx) {

		// 예약 가능 인원 -1
		tdao.updateTimePeopleMinus(timeIdx);

		// 상태 체크 후 예약 가능 인원수가 1 이상이면 T로 전환
		// 상태 체크 후 최대 인원수와 예약가능한 인원수가 같지 않으면 0 같으면 1
		tdao.updateTimeStatus(timeIdx);

		// time 테이블 업데이트 후에 reservation 테이블 추가 !
		dao.insertReservation(userIdx, timeIdx);
	}

	// 예약 삭제
	@Override
	public void deleteReservation(int reservationIdx) {

		// 업데이트 할 시간 idx 가져오기
		int timeIdx = dao.selectTimeIdx(reservationIdx);

		// 예약 가능 인원 +1
		tdao.updateTimePeoplePlus(timeIdx);

		// 상태 체크 후 예약 가능 인원수가 1 이상이면 T로 전환
		// 상태 체크 후 최대 인원수와 예약가능한 인원수가 같지 않으면 0 같으면 1
		tdao.updateTimeStatus(timeIdx);

		// time 테이블 업데이트 후에 reservation 테이블 삭제 !
		dao.deleteReservation(reservationIdx);

	}

	// 예약리스트 뿌려줄 객체 리스트 , 날짜순으로 정렬
	@Override
	public List<ShopListResVO> selectReservationList(int userIdx) {
		return dao.selectReservationList(userIdx);
	}

}
