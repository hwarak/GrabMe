package com.grabme.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.grabme.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api("UserController API v1")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService user_service;

	@ApiOperation(value = "회원 등록", notes = "<strong>회원의 정보</strong>를 입력받아 등록한다.")
	@RequestMapping(value = "/test/{name}/{phone}/{status}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public String insertUser(HttpServletRequest request, @PathVariable("name") String name,
			@PathVariable("phone") String phone, @PathVariable("status") int status) {

		user_service.insertUser(name, phone, status);
		
		return "{\"result\":\"ok\"}";

	}

}
