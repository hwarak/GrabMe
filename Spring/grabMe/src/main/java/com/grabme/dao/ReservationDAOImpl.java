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

	// insert reservation
	@Override
	public void insertReservation(int user_idx, int time_idx) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_idx", user_idx);
		map.put("time_idx", time_idx);
		sqlSession.insert("com.grabme.mappers.ReservationMapper.insertReservation", map);
	}

	// select reservation list
	@Override
	public List<ShopListResVO> selectReservationList(int user_idx) {
		return sqlSession.selectList("com.grabme.mappers.ReservationMapper.selectReservationList", user_idx);
	}

	// delete reservation
	@Override
	public void deleteReservation(int reservation_idx) {
		sqlSession.delete("com.grabme.mappers.ReservationMapper.deleteReservation", reservation_idx);
	}

	// select time idx by reservation_idx
	@Override
	public int selectTimeIdx(int reservation_idx) {
		return sqlSession.selectOne("com.grabme.mappers.ReservationMapper.selectTimeIdx", reservation_idx);
		
	}

	// select reservation by user
	@Override
	public List<Integer> selectReservationByUser(int user_idx) {
		return sqlSession.selectList("com.grabme.mappers.ReservationMapper.selectReservationByUser",user_idx);
	}

	

}
