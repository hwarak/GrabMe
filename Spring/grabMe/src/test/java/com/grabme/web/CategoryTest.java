package com.grabme.web;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.CategoryDAO;
import com.grabme.vo.ShopVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class CategoryTest {

	@Autowired
	private CategoryDAO dao;

	@Test
	public void testSelectCategoryWithXY() throws Exception {
		// 현재 위도,경도 값으로 주변 가게 리스트 가져오기
		List<ShopVO> list = dao.selectCategoryWithXY(126.93677023776556, 37.55522238374985, 3);
		System.out.println(list.get(0).getTitle());
	}
}
