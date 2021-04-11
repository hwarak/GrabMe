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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = { "3. Time" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/time")
public class TimeController {

	@Autowired
	private TimeService time_service;
	
	@Autowired
	private UserService user_service;

	@Autowired
	private JsonEcDcService json_service;

	// 시간 테이블 업데이트 하기

	// (1) 시간 추가
	@ApiOperation(value = "타임 추가", notes = "타임을 추가한다.")
	@PostMapping
	@ResponseBody
	public ResponseEntity timeInfoPost(@ApiParam(value = "시간 정보", required = true) @RequestBody String str) {

		System.out.println("클라이언트 요청 : " + str);

		SignResVO svo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = json_service.jsonDc(str);

		String date = (String) obj.get("date");
		String time = (String) obj.get("time");
		int shop_idx = (int) (long) obj.get("shop_idx");
		int people = (int) (long) obj.get("people");

		time_service.insertTime(shop_idx, date, time, people);

		svo.setResult("ok");
		svo.setCode("");

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATE_TIME, svo), HttpStatus.OK);
	}

	// (2) 시간 삭제
	@ApiOperation(value = "타임 삭제", notes = "해당 타임을 삭제한다.")
	@DeleteMapping
	@ResponseBody
	public ResponseEntity timeInfoDelete(@ApiParam(value = "시간 정보", required = true) @RequestBody String str) {

		System.out.println("클라이언트 요청 : " + str);

		SignResVO svo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = json_service.jsonDc(str);
		int idx = (int) (long) obj.get("idx");

		time_service.deleteTime(idx);

		svo.setResult("ok");
		svo.setCode("");

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_TIME, svo), HttpStatus.OK);
	}

	// (3) 시간 리스트 가져오기
	@ApiOperation(value = "날짜별 타임 리스트 보기", notes = "날짜를 받으면 예약타임들을 보여준다.")
	@GetMapping
	@ResponseBody
	public ResponseEntity timeInfoGet(@ApiParam(value = "가게 번호", required = true) @RequestParam int shop_idx,
			@ApiParam(value = "날짜", required = true) @RequestParam String date) {

		List<TimeVO> list = time_service.selectDate(shop_idx, date);
		
		if (list.isEmpty()) {
			// 리스트가 비어있을 때 에외처리
			return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NO_CONTENT, list), HttpStatus.OK);
		}

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SEND_LIST, list), HttpStatus.OK);
	}

	// (4) 예약된 유저 리스트 가져오기
	@ApiOperation(value = "예약한 유저 리스트 보기", notes = "타임을 선택하면 예약된 유저 리스트를 보여준다.")
	@GetMapping("/2")
	@ResponseBody
	public ResponseEntity userInfoGet(@ApiParam(value = "타임 번호", required = true) @RequestParam int time_idx) {

		List<UserVO> list = user_service.selectUserByTime(time_idx);
		
		if (list.isEmpty()) {
			// 리스트가 비어있을 때 에외처리
			return new ResponseEntity(DefaultRes.res(StatusCode.NO_CONTENT, ResponseMessage.NO_CONTENT, list), HttpStatus.OK);
		}

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SEND_LIST, list), HttpStatus.OK);
	}

	

}
