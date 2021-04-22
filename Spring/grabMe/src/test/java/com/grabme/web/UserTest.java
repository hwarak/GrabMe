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

	private String userPhone = "01011223366";
	private int userStatus = 1;
	private String userName = "김철수";
	private String userProfileImg = "";
	private int userIdx = 100;

	// 데이터베이스에 존재하는지 체크
	@Test
	public void testCheckUser() throws Exception {

		if (userPhone.isEmpty()) {
			System.out.println("폰번호 없음");
		} else {
			System.out.println(dao.checkUser(userPhone, userStatus));
		}
	}

	// 랜덤번호 생성
	@Test
	public void testRandomNumber() throws Exception {

		Random rd = new Random();// 랜덤 객체 생성
		String cn = "";

		for (int i = 0; i < 4; i++) { // 숫자 네자리
			cn = cn + rd.nextInt(9); // 0-9 까지의 숫자들 랜덤 번호 String으로 이어붙이기
		}

		System.out.println(cn);
	}

	// 회원가입
	@Test
	public void testInsertUser() throws Exception {
		dao.insertUser(userName, userPhone, userStatus); // 데이터베이스 등록
	}

	// 유저 탈퇴
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

	// 사장님이면 가게 번호, 개인고객이면 0을 반환
	@Test
	public void testSelectReturnIdx() throws Exception {
		System.out.println(dao.selectReturnIdx(8));
	}

	// 유저 정보 업데이트
	@Test
	public void testUpdateUser() throws Exception {
		dao.updateUser(userProfileImg, userIdx);
	}

	// 유저 프로필 주소
	@Test
	public void testSelctUserProfile() throws Exception {
		System.out.println(dao.selectUserProfile(userIdx));
	}

}
