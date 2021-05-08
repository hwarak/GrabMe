package com.grabme.web;

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

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sign")
public class SignController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private JsonEcDcService jsonService;

	@PostMapping(value = "/check")
	@ResponseBody
	public ResponseEntity checkUser(@RequestBody String str) {

		System.out.println("클라이언트 요청 : " + str);

		SignResVO srvo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = jsonService.jsonDc(str);

		String userPhone = (String) obj.get("userPhone");
		int userStatus = (int) (long) obj.get("userStatus");

//		if(userPhone.isEmpty()) {
//			return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.PHONE_NULL,srvo),HttpStatus.OK);
//		}

		// 데이터베이스에 번호/상태 체크 후 존재하는 유저(1), 존재하지 않는 유저(0) 반환
		int result = userService.checkUser(userPhone, userStatus);

		// cn = 인증번호
		String cn = userService.randomNumber();

		srvo.setCode(cn); // 클라이언트단에도 인증번호 전송

		// 인증번호가 담긴 메세지를 보낸다
		// messageService.sendMessage(userPhone, cn);

		if (result == 0) {
			// 데이터베이스에 존재하지 않음, 가입 가능
			srvo.setResult("ok");
		} else {
			// 데이터베이스에 이미 존재함, 가입 불가능
			srvo.setResult("no");
		}

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.SEND_CODE, srvo), HttpStatus.OK);
	}

	@PostMapping(value = "/up")
	@ResponseBody
	public ResponseEntity signUp(@RequestBody String str) {

		System.out.println("클라이언트 요청 : " + str);

		SignResVO srvo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = jsonService.jsonDc(str);

		String userName = (String) obj.get("userName");
		String userPhone = (String) obj.get("userPhone");
		int userStatus = (int) (long) obj.get("userStatus");

		if (userName.isEmpty()) {
			return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.NAME_NULL, srvo),
					HttpStatus.OK);
		}

		// 데이터베이스에 저장
		userService.insertUser(userName, userPhone, userStatus);

		srvo.setResult("ok");
		srvo.setCode("");

		return new ResponseEntity(DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATED_USER, srvo),
				HttpStatus.OK);

	}

}
