package com.grabme.web;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.grabme.response.DefaultRes;
import com.grabme.response.ResponseMessage;
import com.grabme.response.StatusCode;
import com.grabme.service.JsonEcDcService;
import com.grabme.service.S3Service;
import com.grabme.service.ShopService;
import com.grabme.service.UserService;
import com.grabme.vo.SignResVO;
import com.grabme.vo.UserVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private JsonEcDcService jsonService;
	
	@Autowired
	private S3Service s3Service;

	@ApiOperation(value = "유저 삭제", notes = "해당 유저와 예약 내역을 삭제한다.")
	@DeleteMapping
	@ResponseBody
	public ResponseEntity userDelete(@ApiParam(value = "유저 번호", required = true) @RequestBody String str) {

		System.out.println("클라이언트 요청 : " + str);

		SignResVO srvo = SignResVO.getSignResVOObject();

		// json 파싱 후 반환
		JSONObject obj = jsonService.jsonDc(str);
		int userIdx = (int) (long) obj.get("userIdx");

		int result = userService.selectReturnIdx(userIdx);

		if (result == 0) {
			userService.deleteUser(userIdx);
		} else {
			shopService.deleteShop(result, userIdx);
		}

		srvo.setResult("ok");
		srvo.setCode("");

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.DELETE_USER, srvo), HttpStatus.OK);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity userUpdate(@RequestPart MultipartFile file, int userIdx) throws IOException {

		String filePath = userService.selectUserProfile(userIdx);
		System.out.println(filePath);
		
		SignResVO srvo = SignResVO.getSignResVOObject();

		// file 변경이 감지된다면
		if (!file.getOriginalFilename().equals("")) {
			filePath = s3Service.upload(file);
			System.out.println("s3에 저장되는 경로 : " + filePath);
		}
		
		userService.updateUser(filePath, userIdx);
		
		srvo.setResult("ok");
		srvo.setCode("");
		

		return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER, srvo), HttpStatus.OK);
	}

}
