package com.grabme.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabme.dao.UserDAO;
import com.grabme.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;

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
	
	// select user
	@Override
	public UserVO selectUser(int idx) {
		return dao.selectUser(idx);
	}
	
	

}
