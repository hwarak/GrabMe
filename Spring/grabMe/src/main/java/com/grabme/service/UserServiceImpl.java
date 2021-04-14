package com.grabme.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.ReservationDAO;
import com.grabme.dao.TimeDAO;
import com.grabme.dao.UserDAO;
import com.grabme.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;
	
	@Autowired
	private ReservationDAO rdao;
	
	@Autowired
	private TimeDAO tdao;

	// Check user in database
	@Override
	public int checkUser(String user_phone, int user_status) {
		return dao.checkUser(user_phone, user_status);
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
	public int selectUserIdx(String user_phone, int user_status) {
		return dao.selectUserIdx(user_phone, user_status);
	}

	// insert user
	@Override
	public void insertUser(String user_name, String user_phone, int user_status) {
		dao.insertUser(user_name, user_phone, user_status);

	}

	// select User By Time
	@Override
	public List<UserVO> selectUserByTime(int time_idx) {
		return dao.selectUserByTime(time_idx);
	}

	// delete user
	@Override
	public void deleteUser(int user_idx) {
		// 유저 탈퇴시 예약 내역 전부 삭제하기
		
		// 유저의 모든 예약내역 가져오기
		List<Integer> list = rdao.selectReservationByUser(user_idx);
		int time_idx = 0;
		
		for (Integer tmp : list) {
			// 예약 내역 삭제
			time_idx = rdao.selectTimeIdx(tmp);
			
			// 업데이트 할 시간 idx 가져와서 인원수 +1 
			tdao.updateTimePeoplePlus(time_idx);
			
			// 상태 체크 후 예약 가능 인원수가 1 이상이면 T로 전환
			// 상태 체크 후 최대 인원수와 예약가능한 인원수가 같지 않으면 0 같으면 1
			tdao.updateTimeStatus(time_idx);
			
			// time 테이블 업데이트 후에 reservation 테이블 삭제 !
			rdao.deleteReservation(tmp);
		}
		
		// 유저 삭제
		dao.deleteUser(user_idx);
	}

	// select return idx (사장님 : 가게 번호 / 개인 : 0)
	@Override
	public int selectReturnIdx(int user_idx) {
		return dao.selectReturnIdx(user_idx);
	}
	
	
	

}
