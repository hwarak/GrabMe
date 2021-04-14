package com.grabme.web;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.grabme.response.DefaultRes;
import com.grabme.response.ResponseMessage;
import com.grabme.response.StatusCode;
import com.grabme.service.JsonEcDcService;
import com.grabme.service.ShopService;
import com.grabme.service.UserService;
import com.grabme.vo.SignResVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService user_service;
	
	@Autowired
	private ShopService shop_service;
	
	@Autowired
	private JsonEcDcService json_service;
	
	@ApiOperation(value = "유저 삭제", notes = "해당 유저와 예약 내역을 삭제한다.")
	@DeleteMapping
	@ResponseBody
	public ResponseEntity userDelete(@ApiParam(value = "유저 번호", required = true) @RequestBody String str) {

		System.out.println("클라이언트 요청 : " + str);

		SignResVO svo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = json_service.jsonDc(str);
		int userIdx = (int) (long) obj.get("userIdx");
		
		int result = user_service.selectReturnIdx(userIdx);
		
		if(result == 0) {
			user_service.deleteUser(userIdx);			
		}else {
			shop_service.deleteShop(result,userIdx);
		}

		svo.setResult("ok");
		svo.setCode("");

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_USER, svo), HttpStatus.OK);
	}

}
