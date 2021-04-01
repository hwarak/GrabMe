package com.grabme.web;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grabme.response.DefaultRes;
import com.grabme.response.ResponseMessage;
import com.grabme.response.StatusCode;
import com.grabme.service.JsonEcDcService;
import com.grabme.service.MessageService;
import com.grabme.service.UserService;
import com.grabme.vo.SignResVO;
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
	private UserService user_service;

	@Autowired
	private MessageService message_service;

	@Autowired
	private JsonEcDcService json_service;


	@ApiOperation(value = "유저 번호 확인", notes = "유저 번호와 상태를 입력받아 중복 확인 후 인증번호를 발송한다.")
	@PostMapping(value = "/check")
	@ResponseBody
	public Map<String, Object> checkUser(@ApiParam(value = "유저 정보 체크", required = true) @RequestBody String str) {

		System.out.println("도연씨 요청 : " + str);
		
		SignResVO svo = SignResVO.getSignResVOObject();
		
		Map<String, Object> map = new HashMap<String, Object>();

		// json 파싱 후 반환
		JSONObject obj = json_service.jsonDc(str);

		String phone = (String) obj.get("phone");
		int status = (int) (long) obj.get("status");

		// 데이터베이스에 번호/상태 체크 후 존재하는 유저(1), 존재하지 않는 유저(0) 반환
		int result = user_service.checkUser(phone, status);
		
		// cn = 인증번호
		String cn = user_service.randomNumber();
		
		svo.setCode(cn); // 클라이언트단에도 인증번호 전송
		
		// 인증번호가 담긴 메세지를 보낸다
		// message_service.sendMessage(phone, cn);

		if (result == 0) {
			// 데이터베이스에 존재하지 않음, 가입 가능
			svo.setResult("ok");
		} else {
			// 데이터베이스에 이미 존재함, 가입 불가능
			svo.setResult("no");
		}
		
		map.put("statusCode", StatusCode.OK);
		map.put("responseMessage", ResponseMessage.SEND_CODE);
		map.put("data",svo);

		return map;
	}

	@ApiOperation(value = "회원가입", notes = "유저 정보를 입력받아 데이터베이스에 저장한다.")
	@PostMapping(value = "/up")
	@ResponseBody
	public Map<String, Object> signUp(@ApiParam(value = "유저 정보", required = true) @RequestBody String str) {

		System.out.println("도연씨 요청 : " + str);

		Map<String, Object> map = new HashMap<String, Object>();

		// json 파싱 후 반환
		JSONObject obj = json_service.jsonDc(str);

		String name = (String) obj.get("name");
		String phone = (String) obj.get("phone");
		int status = (int) (long) obj.get("status");

		// 데이터베이스에 저장
		user_service.insertUser(name, phone, status);
		
		SignResVO svo = SignResVO.getSignResVOObject();
		svo.setResult("ok");
		svo.setCode("");
		
		map.put("statusCode", StatusCode.OK);
		map.put("responseMessage", ResponseMessage.CREATED_USER);
		map.put("data",svo);

		return map;
	}

	

}
