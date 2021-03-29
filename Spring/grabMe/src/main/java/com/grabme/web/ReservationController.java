package com.grabme.web;

import java.util.List;

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

	@ApiOperation(value = "예약 하기", notes = "예약하는 유저번호와 시각을 입력받아 데이터베이스에 저장한다")
	@PostMapping
	@ResponseBody
	public String reservationPost(@ApiParam(value = "예약 정보", required = true) @RequestBody ReservationVO rvo) {

		reservation_service.insertReservation(rvo.getUser_idx(), rvo.getTime_idx());

		JsonObject obj = new JsonObject();
		obj.addProperty("result", "ok");

		return obj.toString();
	}

	@ApiOperation(value = "예약 가져오기", notes = "유저 번호를 입력받아 예약 내역을 출력한다.")
	@GetMapping
	@ResponseBody
	public String reservationGet(@ApiParam(value = "유저번호", required = true) @RequestParam int user_idx) {

		UserVO uvo = user_service.selectUser(user_idx);

		JsonObject obj = new JsonObject();
		obj.addProperty("name", uvo.getName());
		obj.addProperty("phone", uvo.getPhone());
		obj.addProperty("profile_img", uvo.getProfile_img());

		List<ReservationListVO> list = reservation_service.selectReservationList(user_idx);

		return obj.toString() + new Gson().toJsonTree(list).toString();
	}

	@ApiOperation(value = "예약 삭제하기", notes = "예약번호를 가져와 예약을 삭제한다")
	@DeleteMapping
	@ResponseBody
	public String reservationDelete(@ApiParam(value = "예약 정보", required = true) @RequestBody ReservationVO rvo) {

		reservation_service.deleteReservation(rvo.getIdx());

		JsonObject obj = new JsonObject();
		obj.addProperty("result", "ok");

		return obj.toString();
	}
}
