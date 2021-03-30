package com.grabme.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grabme.service.UserService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;


@Api(tags = { "2. User" })
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService user_service;

}
