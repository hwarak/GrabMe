package com.grabme.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.grabme.service.JsonEcDcService;
import com.grabme.service.ReservationService;
import com.grabme.service.UserService;
import com.grabme.vo.ReservationListVO;
import com.grabme.vo.ReservationVO;
import com.grabme.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = { "4. Reservation" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationService reservation_service;

	@Autowired
	private UserService user_service;

	@Autowired
	private JsonEcDcService json_service;

	@ApiOperation(value = "예약 하기", notes = "예약하는 유저번호와 시각을 입력받아 데이터베이스에 저장한다")
	@PostMapping
	@ResponseBody
	public Map<String, Object> reservationPost(@ApiParam(value = "예약 정보", required = true) @RequestBody String str) {

		System.out.println("도연씨 요청 : " + str);

		Map<String, Object> map = new HashMap<String, Object>();

		// json 파싱 후 반환
		JSONObject obj = json_service.jsonDc(str);

		int time_idx = (int) (long) obj.get("time_idx");
		int user_idx = (int) (long) obj.get("user_idx");

		reservation_service.insertReservation(user_idx, time_idx);

		map.put("result", "ok");

		return map;
	}

	@ApiOperation(value = "예약 가져오기", notes = "유저 번호를 입력받아 예약 내역을 출력한다.")
	@GetMapping
	@ResponseBody
	public Map<String, Object> reservationGet(@ApiParam(value = "유저번호", required = true) @RequestParam int user_idx) {

		Map<String, Object> map = new HashMap<String, Object>();

		UserVO uvo = user_service.selectUser(user_idx);
		map.put("userVO", uvo);

		// 앞으로 예약된 예약리스트를 가져온다.(과거는 가져오지 않음)
		List<ReservationListVO> list = reservation_service.selectReservationList(user_idx);
		map.put("reservationList", list);

		return map;
	}

	@ApiOperation(value = "예약 삭제하기", notes = "예약번호를 가져와 예약을 삭제한다")
	@DeleteMapping
	@ResponseBody
	public Map<String, Object> reservationDelete(@ApiParam(value = "예약 정보", required = true) @RequestBody String str) {

		Map<String, Object> map = new HashMap<String, Object>();

		// json 파싱 후 반환
		JSONObject obj = json_service.jsonDc(str);

		int idx = (int) (long) obj.get("idx");
		
		// 선택된 예약 idx 찾아서 삭제하기
		reservation_service.deleteReservation(idx);

		map.put("result", "ok");

		return map;
	}
}
