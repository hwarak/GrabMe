package com.grabme.web;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.grabme.dao.TimeDAO;
import com.grabme.vo.TimeVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class TimeTest {

	@Autowired
	private TimeDAO dao;

	@Test
	public void testIsertTime() throws Exception {
		// 예약 날짜/시각 등록하기
		dao.insertTime(28, "2021-05-19", "10:30",5);
	}

	@Test
	public void testUpdateTime() throws Exception {
		// 시간 업데이트하기
		dao.updateTime("9:00", 6);
	}

	@Test
	public void testDeleteTime() throws Exception {
		// 시간 삭제하기
		dao.deleteTime(7);
	}

	@Test
	public void testSelectDate() throws Exception {
		// 해당 날짜, 해당 가게의 예약 시간들을 보여준다
		List<TimeVO> list = dao.selectDate(4, "2021-05-11");
		for (TimeVO tmp : list) {
			// boolean 사용할때는 getStatus() X isStatus O
			System.out.println(tmp.isStatus());
		}
	}

}
