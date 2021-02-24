package com.grabme.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grabme.service.MessageService;
import com.grabme.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("SignController")
@RequestMapping("/sign")
public class SignController {

	@Autowired
	UserService user_service;

	@Autowired
	MessageService message_service;

	@ApiOperation(value = "유저 번호 확인", notes = "유저 번호를 입력받아 중복 확인 후 인증번호를 발송한다.")
	@RequestMapping(value = "/{phone}", method = RequestMethod.POST)
	// @ResponseStatus(value = HttpStatus.OK)
	public String checkPhone(@PathVariable("phone") String phone) {

		int result = user_service.checkPhone(phone); // 데이터베이스에 번호 체크 후 개수 반환

		if (result == 0) {// 데이터베이스에 존재하지 않음, 가입 가능
			message_service.sendMessage(phone); // 인증 메세지를 보낸다
		} else { // 데이터베이스에 이미 존재함, 가입 불가능
			return "{\"result\":\"no\"}";
		}

		return "{\"result\":\"ok\"}";

	}

	@ApiOperation(value = "유저 등록", notes = "유저 정보를 입력받아 데이터베이스에 저장한다.")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String insertUser(HttpServletRequest request, @PathVariable("name") String name,
			@PathVariable("phone") String phone, @PathVariable("status") int status) {

		user_service.insertUser(name, phone, status);

		return "{\"result\":\"ok\"}";

	}
}
