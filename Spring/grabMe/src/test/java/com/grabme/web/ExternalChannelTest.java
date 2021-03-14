package com.grabme.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.ExternalChannelDAO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class ExternalChannelTest {
	
	@Autowired
	private ExternalChannelDAO dao;
	
	@Test
	public void testInsertURL() throws Exception{
		//외부 채널 등록 (오픈카톡, 인스타 주소)
		dao.insertURL(3, "aa", "bb");
	}

}
