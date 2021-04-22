package com.grabme.web;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.ReservationDAO;
import com.grabme.vo.ShopListResVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class ReservationTest {

	@Autowired
	private ReservationDAO dao;

	// 예약 등록하기
	@Test
	public void testInsertReservation() throws Exception {
		dao.insertReservation(15, 4);
	}

	// 유저의 예약내역을 보여준다
	@Test
	public void testSelectReservationList() throws Exception {
		List<ShopListResVO> list = dao.selectReservationList(6);
		for (ShopListResVO tmp : list) {
			System.out.println(tmp.getReservationIdx());
		}
	}

	// 예약 삭제
	@Test
	public void testDeleteReservation() throws Exception {
		dao.deleteReservation(1);
	}

	// 선택된 예약의 타임 idx
	@Test
	public void testSelectTimeIdx() throws Exception {
		System.out.println(dao.selectTimeIdx(3));
	}
	
	// 선택된 유저의 예약 번호들
	@Test
	public void testSelectReservationByUser() throws Exception {
		List<Integer> list = dao.selectReservationByUser(6);
		for(int tmp : list) {
			System.out.println(tmp);
		}
		
	}
}
