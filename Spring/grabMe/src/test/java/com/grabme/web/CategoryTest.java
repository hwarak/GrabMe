package com.grabme.web;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.CategoryDAO;
import com.grabme.vo.ShopResVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class CategoryTest {

	@Autowired
	private CategoryDAO dao;

	@Test
	public void testSelectCategoryWithXY() throws Exception {
		// 위도,경도 값 받아서 거리순으로 정렬후 원하는 만큼 보여주기
		
		//List<ReservationListVO> list = dao.selectCategoryWithXY(126.93677023776556, 37.55522238374985, 3,0);
		List<ShopResVO> list = dao.selectCategoryWithXY(126.93677023776556, 37.55522238374985, 5,0);
		if(list.isEmpty()) {
			// 리스트가 비어있을때 처리
			System.out.println("List is empty");
		}else {
			System.out.println(list.get(0).getTitle());	
		}
		
	}
	
	@Test
	public void testSearchTitle() throws Exception{
		// 가게 검색
		List<ShopResVO> list = dao.searchTitle(126.93677023776556, 37.55522238374985, 3,0,"민희");
		
		if(list.isEmpty()) {
			// 리스트가 비어있을때 처리
			System.out.println("List is empty");
		}else {
			System.out.println(list.get(0).getTitle());	
		}
		
	}
}
