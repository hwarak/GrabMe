package com.grabme.web;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grabme.response.DefaultRes;
import com.grabme.response.ResponseMessage;
import com.grabme.response.StatusCode;
import com.grabme.service.JsonEcDcService;
import com.grabme.service.TimeService;
import com.grabme.service.UserService;
import com.grabme.vo.SignResVO;
import com.grabme.vo.TimeVO;
import com.grabme.vo.UserVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/time")
public class TimeController {

	@Autowired
	private TimeService timeService;
	
	@Autowired
	private JsonEcDcService jsonService;

	// 시간 테이블 업데이트 하기

	// 타임 등록
	@PostMapping
	@ResponseBody
	public ResponseEntity timeInfoPost(@RequestBody String str) {

		System.out.println("클라이언트 요청 : " + str);

		SignResVO srvo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = jsonService.jsonDc(str);

		int shopIdx = (int) (long) obj.get("shopIdx");
		String timeDate = (String) obj.get("timeDate");
		String timeAvailable = (String) obj.get("timeAvailable");
		int timePeople = (int) (long) obj.get("timePeople");

		// 타임 등록
		timeService.insertTime(shopIdx, timeDate, timeAvailable, timePeople);

		srvo.setResult("ok");
		srvo.setCode("");

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATE_TIME, srvo), HttpStatus.OK);
	}

	// 타임 삭제
	@DeleteMapping
	@ResponseBody
	public ResponseEntity timeInfoDelete(@RequestBody String str) {

		System.out.println("클라이언트 요청 : " + str);

		SignResVO srvo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = jsonService.jsonDc(str);
		int timeIdx = (int) (long) obj.get("timeIdx");

		// 타임 삭제
		timeService.deleteTime(timeIdx);

		srvo.setResult("ok");
		srvo.setCode("");

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_TIME, srvo), HttpStatus.OK);
	}

	// 비즈니즈스의 해당 날짜에 등록된 타임 리스트
	@GetMapping
	@ResponseBody
	public ResponseEntity timeInfoGet( @RequestParam int shopIdx,@RequestParam String date) {

		List<TimeVO> list = timeService.selectDate(shopIdx, date);
		
		if (list.isEmpty()) {
			// 리스트가 비어있을 때 에외처리
			return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NO_CONTENT, list), HttpStatus.OK);
		}

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SEND_LIST, list), HttpStatus.OK);
	}

	// 선택된 타임에 예약된 개인 고객들 리스트를 출력한다
	@GetMapping("/2")
	@ResponseBody
	public ResponseEntity userInfoGet(@RequestParam int timeIdx) {

		List<UserVO> list = timeService.selectUserByTime(timeIdx);
		
		if (list.isEmpty()) {
			// 리스트가 비어있을 때 에외처리
			return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NO_CONTENT, list), HttpStatus.OK);
		}

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SEND_LIST, list), HttpStatus.OK);
	}

	

}
