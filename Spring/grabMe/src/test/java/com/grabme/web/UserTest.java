package com.grabme.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.UserDAO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class UserTest {

	@Autowired
	private UserDAO dao;

	@Test
	public void testCheckPhone() throws Exception {
		// Check phone in database
		System.out.println(dao.checkPhone("01023652365"));

	}

}
