package com.grabme.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.ReservationDAO;
import com.grabme.dao.UserDAO;
import com.grabme.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;
	
	@Autowired
	private ReservationDAO rdao;

	// Check user in database
	@Override
	public int checkUser(String phone, int status) {
		return dao.checkUser(phone, status);
	}

	// 인증번호 생성
	@Override
	public String randomNumber() {

		Random rd = new Random();// 랜덤 객체 생성
		String cn = "";

		for (int i = 0; i < 4; i++) { // 숫자 네자리
			cn = cn + rd.nextInt(9); // 0-9 까지의 숫자들 랜덤 번호 String으로 이어붙이기
		}

		return cn;
	}

	// select user idx
	@Override
	public int selectUserIdx(String phone, int status) {
		return dao.selectUserIdx(phone, status);
	}

	// insert user
	@Override
	public void insertUser(String name, String phone, int status) {
		dao.insertUser(name, phone, status);

	}

	// select User By Time
	@Override
	public List<UserVO> selectUserByTime(int time_idx) {
		return dao.selectUserByTime(time_idx);
	}

	// delete user
	@Override
	public void deleteUser(int idx) {
		// 유저 탈퇴시 예약 내역 전부 삭제하기
		
		// 유저의 모든 예약내역 가져오기
		List<Integer> list = rdao.selectReservationByUser(idx);
		
		for (Integer tmp : list) {
			// 예약 내역 삭제
			rdao.deleteReservation(tmp);
		}
		
		// 유저 삭제
		dao.deleteUser(idx);
	}
	
	

}
