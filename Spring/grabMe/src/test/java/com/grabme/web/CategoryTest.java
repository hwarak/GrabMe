package com.grabme.web;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.CategoryDAO;
import com.grabme.vo.ShopListResVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class CategoryTest {

	@Autowired
	private CategoryDAO dao;

	// 위도,경도 값 받아서 거리순으로 정렬후 20 개씩 보여주기
	@Test
	public void testSelectCategoryWithXY() throws Exception {

		List<ShopListResVO> list = dao.selectCategoryWithXY(126.93677023776556, 37.55522238374985, 3, 0);
		if (list.isEmpty()) {
			// 리스트가 비어있을때 처리
			System.out.println("List is empty");
		} else {
			System.out.println(list.get(0).getShopAddress());
		}

	}

	// 위도,경도 값 받아서 거리순으로 정렬후 20 개씩 보여주기 + 원하는 비즈니스 검색
	@Test
	public void testSearchTitle() throws Exception {
		// 가게 검색
		List<ShopListResVO> list = dao.searchTitle(126.93677023776556, 37.55522238374985, 3, 0, "신촌");

		if (list.isEmpty()) {
			// 리스트가 비어있을때 처리
			System.out.println("List is empty");
		} else {
			System.out.println(list.get(0).getShopAddress());
		}

	}
}
