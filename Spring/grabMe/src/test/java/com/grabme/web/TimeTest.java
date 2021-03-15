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
		// 예약 날짜/시간 등록하기
		dao.insertTime(4, "2021-03-09", "10:30");
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
		// 해당 날짜의 예약 시간들을 보여준다
		List<TimeVO> list = dao.selectDate(4, "2021-03-11");
		for (TimeVO tmp : list) {
			System.out.println(tmp.getTime());
		}
	}

//	@Test
//	public void testSlashJson() throws Exception {
//		String str = "u&6&10:30!x&5!d&8!i&2021-03-11&17:30!i&2021-03-11";
//		String arr[] = str.split("!");
//
//
//		for (int i = 0; i < arr.length; i++) {
//			
//			switch (arr[i].charAt(0)) {
//			case 'u':
//				System.out.println("update");
//				break;
//			case 'd':
//				System.out.println("delete");
//				break;
//			case 'i':
//				System.out.println("insert");
//				break;
//			case 'x':
//				System.out.println("xxx");
//				break;
//
//			}
//			
//		}
//
//	}

}
