package com.grabme.web;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.ReservationDAO;
import com.grabme.dao.UserDAO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class UserTest {

	@Autowired
	private UserDAO dao;

	@Autowired
	private ReservationDAO rdao;

	@Test
	public void testCheckUser() throws Exception {

		System.out.println(dao.checkUser("01023652365", 1));
	}

	@Test
	public void testRandomNumber() throws Exception { // 인증번호 만들기

		Random rd = new Random();// 랜덤 객체 생성
		String cn = "";

		for (int i = 0; i < 4; i++) { // 숫자 네자리
			cn = cn + rd.nextInt(9); // 0-9 까지의 숫자들 랜덤 번호 String으로 이어붙이기
		}

		System.out.println(cn);
	}

	@Test
	public void testInsertUser() throws Exception {

		String name = "김병수";
		String phone = "01045692565";
		int status = 1;

		dao.insertUser(name, phone, status); // 데이터베이스 등록
		System.out.println(dao.selectUserIdx(phone, status));// 등록한 유저 idx 반환
	}

	@Test
	public void testSelectReservationByUser() throws Exception {
		// 유저 탈퇴시 예약 내역 전부 삭제하기
		List<Integer> list = rdao.selectReservationByUser(32);
		for (Integer tmp : list) {
			// 예약 내역 삭제
			rdao.deleteReservation(tmp);
		}
		// 유저 삭제
		dao.deleteUser(32);
	}
	
	@Test
	public void testSelectReturnIdx() throws Exception{
		System.out.println(dao.selectReturnIdx(8));
	}

}
