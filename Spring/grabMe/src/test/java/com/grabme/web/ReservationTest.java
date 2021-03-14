package com.grabme.web;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.ReservationDAO;
import com.grabme.vo.ReservationListVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class ReservationTest {

	@Autowired
	private ReservationDAO dao;

	@Test
	public void testInsertReservation() throws Exception {
		// 예약 등록하기
		dao.insertReservation(1, 5);
	}

	@Test
	public void testSelectReservationList() throws Exception {
		// 유저의 예약내역을 보여준다
		List<ReservationListVO> list = dao.selectReservationList(6);
		for (ReservationListVO tmp : list) {
			System.out.println(tmp.getShop_idx());
		}
	}

	@Test
	public void testDeleteReservation() throws Exception {
		// 예약 삭제
		dao.deleteReservation(1);
	}

	@Test
	public void testSelectTimeIdx() throws Exception {
		// 예약한 시간 고유 번호 반환
		System.out.println(dao.selectTimeIdx(3));
	}
}
