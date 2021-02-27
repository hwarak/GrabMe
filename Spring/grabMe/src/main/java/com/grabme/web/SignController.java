package com.grabme.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.grabme.service.MessageService;
import com.grabme.service.UserService;
import com.grabme.vo.CheckVO;
import com.grabme.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = { "1. Sign" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/sign")
public class SignController {

	@Autowired
	UserService user_service;

	@Autowired
	MessageService message_service;

	@ApiOperation(value = "유저 번호 확인", notes = "유저 번호와 상태를 입력받아 중복 확인 후 인증번호를 발송한다.")
	@PostMapping(value = "/check")
	@ResponseBody
	public String checkUser(@ApiParam(value = "유저 정보 체크", required = true) @RequestBody CheckVO cvo) {

		JsonObject obj = new JsonObject();

		int result = user_service.checkUser(cvo.getPhone(), cvo.getStatus());
		// 데이터베이스에 번호/상태 체크 후 존재하는 유저(1), 존재하지 않는 유저(0) 반환

		if (result == 0) {
			// 데이터베이스에 존재하지 않음, 가입 가능
			String cn = user_service.randomNumber(); // cn = 인증번호
			message_service.sendMessage(cvo.getPhone(), cn); // 인증번호가 담긴 메세지를 보낸다
			obj.addProperty("message", "ok");
			obj.addProperty("code", cn); // 클라이언트단에도 인증번호 전송
		} else {
			// 데이터베이스에 이미 존재함, 가입 불가능
			obj.addProperty("message", "no");
		}
		return obj.toString();
	}

	@ApiOperation(value = "회원가입", notes = "유저 정보를 입력받아 데이터베이스에 저장한다.")
	@PostMapping(value = "/up")
	@ResponseBody
	public String signUp(@ApiParam(value = "유저 정보", required = true) @RequestBody UserVO uvo) {

		user_service.insertUser(uvo.getName(), uvo.getPhone(), uvo.getStatus()); // 데이터베이스에 저장
		int userIdx = user_service.selectUserIdx(uvo.getPhone(), uvo.getStatus()); // 지금 가입한 유저 idx 전달

		JsonObject obj = new JsonObject();

		obj.addProperty("message", "ok");
		obj.addProperty("userIdx", userIdx);

		return obj.toString();

	}

	@ApiOperation(value = "로그인", notes = "유저 확인 후 로그인한다.")
	@PostMapping(value = "/in")
	@ResponseBody
	public String signIn(@ApiParam(value = "유저 정보 체크", required = true) @RequestBody CheckVO cvo) {

		JsonObject obj = new JsonObject();

		int result = user_service.checkUser(cvo.getPhone(), cvo.getStatus());
		// 데이터베이스에 번호/상태 체크 후 존재하는 유저(1), 존재하지 않는 유저(0) 반환

		if (result == 0) {
			// 데이터베이스에 존재하지 않음, 가입해야함
			obj.addProperty("message", "no");
		} else {
			// 데이터베이스에 존재함, 로그인 가능
			int userIdx = user_service.selectUserIdx(cvo.getPhone(), cvo.getStatus()); // 유저 idx
			String cn = user_service.randomNumber(); // cn = 인증번호

			message_service.sendMessage(cvo.getPhone(), cn); // 인증번호가 담긴 메세지를 보낸다

			obj.addProperty("message", "ok");
			obj.addProperty("code", cn); // 클라이언트단에도 인증번호 전송
			obj.addProperty("userIdx", userIdx);
		}
		return obj.toString();
	}

}
