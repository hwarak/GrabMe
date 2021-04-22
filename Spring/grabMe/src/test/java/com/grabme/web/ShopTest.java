package com.grabme.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.ShopDAO;
import com.grabme.vo.ShopVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class ShopTest {

	@Autowired
	private ShopDAO dao;
	
	// 비즈니스 등록
	@Test
	public void testInsertShop() throws Exception {
		dao.insertShop(7, 3, "썸네일", "가게이름", "주소", "가게 소개", "가게번호", 127.1646848, 37.164685, "가게 카톡 주소", "가게 인스타 주소");
	}

	// 사장님 번호로 등록된 비즈니스 찾기
	@Test
	public void testSelectShopIdx() throws Exception {
		System.out.println(dao.selectShopIdx(1));
	}

	// 사장님이 등록한 비즈니스가 있으면 1을 없으면 0을 반환한다
	// 입력받은 유저아이디로 생성된 가게가 없으면 0을 반환
	@Test
	public void testCheckShop() throws Exception {
		System.out.println(dao.checkShop(1));
	}

	// 비즈니스 정보 모두 보기
	@Test
	public void testSelectShopAllInfo() throws Exception {
		ShopVO svo = dao.selectShopAllInfo(35);
		System.out.println(svo.getShopAddress());
	}
	
	// 비즈니스 정보 업데이트
	@Test
	public void testUpdateShop() throws Exception {
		ShopVO svo = new ShopVO();
		dao.updateShopAllInfo(svo);
	}
	
	// 비즈니스 삭제
	// 비즈니스에 등록된 예약 시각들, 예약 내역들 모두 삭제
	@Test
	public void testDeleteShop() throws Exception {
		dao.deleteTimeReservation(7);
		dao.deleteShop(7);
	}
	
	

}
