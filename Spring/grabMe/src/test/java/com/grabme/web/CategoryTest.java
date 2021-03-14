package com.grabme.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.CategoryDAO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class CategoryTest {

	@Autowired
	private CategoryDAO dao;

	@Test
	public void testSelectIdx() throws Exception {
		// 카테고리 이름으로 카테고리 번호 반환
		System.out.println(dao.selectIdx("노래방"));

	}

	@Test
	public void testSelectName() throws Exception {
		// 카테고리 번호로 이름 반환
		System.out.println(dao.selectName(1));
	}
}
