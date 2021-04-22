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
import com.grabme.vo.UserVO;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class TimeTest {

	@Autowired
	private TimeDAO dao;

	// 타임 등록
	@Test
	public void testIsertTime() throws Exception {
		dao.insertTime(28, "2021-05-19", "10:30",5);
	}

	// 타임 삭제
	@Test
	public void testDeleteTime() throws Exception {
		dao.deleteTime(7);
	}

	// 해당 날짜, 해당 가게의 예약 가능한 타임 리스트를 보여준다
	@Test
	public void testSelectDate() throws Exception {
		List<TimeVO> list = dao.selectDate(4, "2021-05-11");
		for (TimeVO tmp : list) {
			// boolean 사용할때는 getStatus() X isStatus O
			System.out.println(tmp.isTimeStatus());
		}
	}
	
	// 선택된 타임에 예약된 개인 고객들 리스트를 출력한다
	@Test
	public void testSelectUserByTime() throws Exception {
		List<UserVO> list = dao.selectUserByTime(4);
		for (UserVO tmp : list) {
			System.out.println(tmp.getUserName());
		}
	}

}
