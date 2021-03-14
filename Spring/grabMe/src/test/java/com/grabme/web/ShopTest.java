package com.grabme.web;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.ExternalChannelDAO;
import com.grabme.dao.ShopDAO;
import com.grabme.vo.ShopAllVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class ShopTest {

	@Autowired
	private ShopDAO dao;

	@Autowired
	private ExternalChannelDAO edao;

	@Test
	public void testSelectShopIdx() throws Exception {
		// 유저 번호로 가게 번호 찾기
		System.out.println(dao.selectShopIdx(1));
	}

	@Test
	public void testInnsertAndSelect() throws Exception {
		// 가게 등록 후 바로 가게 번호 받아오기
		int user_idx = 7;
		dao.insertShop(user_idx, 3, "썸네일", "가게이름", "주소", "가게 소개");
		edao.insertURL(dao.selectShopIdx(user_idx), "카톡주소입니다", "인스타주소입니다");
	}

	@Test
	public void testCheckShop() throws Exception {
		// 가게 존재하는지 확인
		// 입력받은 유저아이디로 생성된 가게가 없으면 0을 반환
		System.out.println(dao.checkShop(1));
	}

	@Test
	public void testAllInfo() throws Exception {
		// 가게의 모든 정보를 입력한다
		List<ShopAllVO> list = dao.selectShopAllinfo(4);
		for (ShopAllVO tmp : list) {
			System.out.println(tmp.getTitle());
		}
	}

}
