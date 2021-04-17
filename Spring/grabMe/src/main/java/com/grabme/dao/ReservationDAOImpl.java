package com.grabme.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.grabme.vo.ShopListResVO;

public class ReservationDAOImpl implements ReservationDAO {

	@Autowired
	private SqlSession sqlSession;

	// 예약 등록
	@Override
	public void insertReservation(int userIdx, int timeIdx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIdx", userIdx);
		map.put("timeIdx", timeIdx);
		sqlSession.insert("com.grabme.mappers.ReservationMapper.insertReservation", map);
	}
	
	// 예약 삭제
	@Override
	public void deleteReservation(int reservationIdx) {
		sqlSession.delete("com.grabme.mappers.ReservationMapper.deleteReservation", reservationIdx);
	}

	// 예약리스트 뿌려줄 객체 리스트 , 날짜순으로 정렬
	@Override
	public List<ShopListResVO> selectReservationList(int userIdx) {
		return sqlSession.selectList("com.grabme.mappers.ReservationMapper.selectReservationList", userIdx);
	}

	// 선택된 예약의 타임 idx
	@Override
	public int selectTimeIdx(int reservationIdx) {
		return sqlSession.selectOne("com.grabme.mappers.ReservationMapper.selectTimeIdx", reservationIdx);
		
	}

	// 선택된 유저의 예약 번호들
	@Override
	public List<Integer> selectReservationByUser(int userIdx) {
		return sqlSession.selectList("com.grabme.mappers.ReservationMapper.selectReservationByUser",userIdx);
	}

	

}
