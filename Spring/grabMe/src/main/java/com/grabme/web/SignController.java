package com.grabme.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grabme.service.MessageService;
import com.grabme.service.UserService;

import io.swagger.annotations.Api;

@RestController
@Api("SignController")
@RequestMapping("/sign")
public class SignController {

	@Autowired
	UserService user_service;

	@Autowired
	MessageService message_service;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String phoneNum(@PathVariable("number") String number) {

		//message_service.sendMessage(number);

		//return "{\"result\":\"ok\"}";
		

		return "{\"result\":\""+number+"}";

	}

//	@ApiOperation(value = "회원 등록", notes = "회원의 정보를 입력받아 등록한다.")
//	@RequestMapping(value = "/", method = RequestMethod.POST)
//	@ResponseStatus(value = HttpStatus.OK)
//	public String insertUser(HttpServletRequest request, @PathVariable("name") String name,
//			@PathVariable("phone") String phone, @PathVariable("status") int status) {
//
//		user_service.insertUser(name, phone, status);
//
//		return "{\"result\":\"ok\"}";
//
//	}
}
